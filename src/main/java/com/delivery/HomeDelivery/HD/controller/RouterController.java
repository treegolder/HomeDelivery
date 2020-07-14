package com.delivery.HomeDelivery.HD.controller;

import com.delivery.HomeDelivery.HD.component.EncryptComponent;
import com.delivery.HomeDelivery.HD.component.MyToken;
import com.delivery.HomeDelivery.HD.entity.Commodity;
import com.delivery.HomeDelivery.HD.entity.Coupon;
import com.delivery.HomeDelivery.HD.entity.MemberShip;
import com.delivery.HomeDelivery.HD.entity.User;
import com.delivery.HomeDelivery.HD.service.MemberShipService;
import com.delivery.HomeDelivery.HD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class RouterController {
    @Autowired
    private UserService us;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private MemberShipService ms;
    @Autowired
    private EncryptComponent encrypt;
    @GetMapping("/index")
    public String index() {
        return "login";
    }

    @PostMapping("/toLogin")
    public String login(@RequestParam("number") Integer number, @RequestParam("password") String password, HttpSession session,Model m) {
        if (number == null || password == null)  {
            m.addAttribute("msg","请输入用户名或密码");
            return "login";
        }

        User user = Optional.ofNullable(us.findUserByNumber(number))
                .filter(u -> encoder.matches(password,u.getPassword()))
                .orElse(null);
        //                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED,"用户名或密码错误"));
        if (user == null){
            m.addAttribute("msg","用户名或密码错误");
        } else {
            session.setAttribute("userid", user.getId());
            List<Commodity> commodityList = us.commodities();
            m.addAttribute("commodities",commodityList);
          /*  Integer userid = (int)session.getAttribute("userid");
            List<Coupon> coupons =ms.findAcCouponsByMemberId(userid);
            m.addAttribute("cardsList",coupons);*/
            if (String.valueOf(user.getRole()).equals("ADMIN"))
                return "adminIndex";
            if (String.valueOf(user.getRole()).equals("MEMBER"))
                return "grid-shop";
        }
        //return "userIndex1";
        return "login";
    }
    @GetMapping("/toUserIndex1")
    public String toUserindex1(Model m) {
        List<Commodity> commodityList = us.commodities();
        m.addAttribute("commodities",commodityList);
        return "grid-shop";
    }


}
