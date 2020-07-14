package com.delivery.HomeDelivery.HD.controller;

import com.delivery.HomeDelivery.HD.component.SnowFlake;
import com.delivery.HomeDelivery.HD.entity.*;
import com.delivery.HomeDelivery.HD.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.Time;
import java.util.List;

@Controller
@Transactional
@RequestMapping("/user")
public class UserController {
    @Autowired
    private PostOrderService ps;
    @Autowired
    private MemberShipService ms;
    @Autowired
    private CouponService cs;
    @Autowired
    private TimesCardService ts;
    @Autowired
    private RechargeCardService rs;
    @Autowired
    private SnowFlake snowFlake;


    @PostMapping("/toBuy")
    public String toBuy(String name,Double price,HttpSession session, Model m) {
        Integer userid = (int)session.getAttribute("userid");
        List<Coupon> coupons =ms.findAcCouponsByMemberId(userid);
        m.addAttribute("cardsList",coupons);
        m.addAttribute("name", name);
        m.addAttribute("price",price);
        session.setAttribute("price", price);
        session.setAttribute("name", name);
        return "userIndex1";
    }

    @PostMapping("/pay")
    public String pay (HttpSession session, String name, String tel,  String country, String province, String city,
                          Integer cardNumber, String address,Model m) {
        Integer userid = (int)session.getAttribute("userid");
        MemberShip memberShip = ms.findMemberShipById(userid);
        PostOrder postOrder = new PostOrder();
        postOrder.setAddress(address);
        postOrder.setName(name);
        postOrder.setCity(city);
        postOrder.setCountry(country);
        postOrder.setTel(tel);
        postOrder.setProvince(province);
        Coupon coupon = cs.findCouponByNumber(cardNumber);
        postOrder.setCoupon(coupon);
        postOrder.setNumber(String.valueOf(snowFlake.getNextId()));
        postOrder.setStatus(PostOrder.Status.PROCESSING);
        postOrder.setMemberShip(memberShip);
        ps.savePostorder(postOrder);
        List<Coupon> coupons = memberShip.getCoupons();
        if (String.valueOf(coupon.getSpecies()).equals("RechargeCard")) {
            for (int i = 0; i < coupons.size(); i ++) {
                int number = coupons.get(i).getNumber();
                int cardId = coupons.get(i).getId();
                if (number == cardNumber) {
                    RechargeCard memberCoupon = rs.findAcRechargeCardById(cardId);
                    double price = memberCoupon.getBalance() - (double) session.getAttribute("price");
                    if (price >= 0.0) {
                        memberCoupon.setBalance(price);
                        rs.updateRechargeCard(memberCoupon);
                        m.addAttribute("rmsg1","购买成功");
                        if (memberCoupon.getBalance() == 0.0) {
                            //逻辑删除
                            rs.logicDelete(memberCoupon);
                            m.addAttribute("rmsg2","卡片余额为0，已自动删除");
                            break;
                        }
                        break;
                    }
                    else {
                        m.addAttribute("rmsg3", "该卡片当前余额为" + memberCoupon.getBalance() + "余额不足，请充值");
                        break;
                    }
                }
            }
        }
        if (String.valueOf(coupon.getSpecies()).equals("TimesCard")){
            for (Coupon value : coupons) {
                int number = value.getNumber();
                int cardId = value.getId();
                if (number == cardNumber) {
                    TimesCard memberCoupon = ts.findAcTimesCardById(cardId);
                    if (memberCoupon.getRemainingTimes() > 0) {
                        memberCoupon.setRemainingTimes(memberCoupon.getRemainingTimes() - 1);
                        ts.updateTimesCard(memberCoupon);
                        m.addAttribute("tmsg1", "购买成功");
                        break;
                    } else {
                        //逻辑删除
                        ts.logicDelete(memberCoupon);
                        m.addAttribute("tmsg2", "该卡片可用次数已耗尽，已自动删除");
                        break;
                    }
                }
            }
        }
        return "redirect:/user/toUserIndex1";
    }
}
