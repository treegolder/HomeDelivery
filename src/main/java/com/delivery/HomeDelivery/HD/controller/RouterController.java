package com.delivery.HomeDelivery.HD.controller;

import com.delivery.HomeDelivery.HD.entity.Commodity;
import com.delivery.HomeDelivery.HD.entity.Coupon;
import com.delivery.HomeDelivery.HD.entity.MemberShip;
import com.delivery.HomeDelivery.HD.entity.User;
import com.delivery.HomeDelivery.HD.service.AdminService;
import com.delivery.HomeDelivery.HD.service.CouponService;
import com.delivery.HomeDelivery.HD.service.MemberShipService;
import com.delivery.HomeDelivery.HD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class RouterController {
  @Autowired private UserService us;
  @Autowired private PasswordEncoder encoder;
  @Autowired private MemberShipService ms;
  @Autowired private CouponService cs;
  @Autowired private AdminService as;

  @GetMapping("/index")
  public String index() {
    return "login";
  }

  @PostMapping("/toLogin")
  public String login(
      @RequestParam("number") Integer number,
      @RequestParam("password") String password,
      HttpSession session,
      Model m,
      RedirectAttributes redirectAttributes) {
    if (number == null || password == null) {
      m.addAttribute("msg", "请输入用户名或密码");
      return "login";
    }

    User user =
        Optional.ofNullable(us.findUserByNumber(number))
            .filter(u -> encoder.matches(password, u.getPassword()))
            .orElse(null);
    //                .orElseThrow(() -> new
    // ResponseStatusException(HttpStatus.UNAUTHORIZED,"用户名或密码错误"));
    if (user == null) {
      m.addAttribute("msg", "用户名或密码错误");
    } else {
      session.setAttribute("userid", user.getId());

      /*  Integer userid = (int)session.getAttribute("userid");
      List<Coupon> coupons =ms.findAcCouponsByMemberId(userid);
      m.addAttribute("cardsList",coupons);*/
      if (String.valueOf(user.getRole()).equals("ADMIN")) {
        List<MemberShip> allMember = ms.findAll();
        List<Coupon> allCoupon = cs.findAcCards();
        m.addAttribute("allMember", allMember);
        m.addAttribute("allCoupon", allCoupon);
        return "adminIndex";
      }

      if (String.valueOf(user.getRole()).equals("MEMBER")) {
        List<Commodity> commodityList = us.commodities();
        m.addAttribute("commodities", commodityList);
        return "grid-shop";
      }
    }
    return "login";
  }

  @PostMapping("/toRegister")
  public String toRegister(
      String registerUsername, Integer registerNumber, String registerPassword, Model m) {
    List<User> userAll = us.findAll();
    for (User u : userAll) {
      if (Objects.equals(u.getNumber(), registerNumber)) {
        m.addAttribute("registermsg", "该账号已被注册");
        return "register";
      }
    }
    User user = new User();
    user.setRole(User.Role.MEMBER);
    user.setName(registerUsername);
    user.setNumber(registerNumber);
    user.setPassword(encoder.encode(registerPassword));
    MemberShip memberShip = new MemberShip();
    memberShip.setUser(user);
    memberShip.setAdmin(as.findAdminById(1));
    ms.saveMemberShip(memberShip);
    m.addAttribute("sucmsg", "注册成功");
    return "login";
  }

  @GetMapping("/toUserIndex1")
  public String toUserindex1(Model m, HttpSession session) {
    List<Commodity> commodityList = us.commodities();
    m.addAttribute("commodities", commodityList);
    if (us.findUserById((int) session.getAttribute("userid")).getRole().equals(User.Role.MEMBER))
      return "grid-shop";

    return "login";
  }

  @GetMapping("/adminIndex")
  public String toAdminPage(Model m, HttpSession session) {
    List<MemberShip> allMember = ms.findAll();
    List<Coupon> allCoupon = cs.findAcCards();
    m.addAttribute("allMember", allMember);
    m.addAttribute("allCoupon", allCoupon);
    int userid = (int) session.getAttribute("userid");
    if (us.findUserById(userid).getRole().equals(User.Role.ADMIN)) return "adminIndex";
    return "login";
  }

  @GetMapping("/register")
  public String registerPage() {
    return "register";
  }

  @GetMapping("/toLogout")
  public String logout(HttpSession session) {
    session.removeAttribute("userid");
    return "login";
  }

  @GetMapping("/delete")
  public String delMember(@RequestParam("id") int id) {
    ms.deleteMemberShipById(id);
    return "redirect:/user/adminIndex";
  }

  @GetMapping("/toAdd")
  public String toAddCards(HttpSession session) {
    int userid = (int) session.getAttribute("userid");
    if (us.findUserById(userid).getRole().equals(User.Role.ADMIN)) return "forms";

    return "login";
  }
}
