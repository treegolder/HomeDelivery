package com.delivery.HomeDelivery.HD.component;

import com.delivery.HomeDelivery.HD.entity.*;
import com.delivery.HomeDelivery.HD.repository.CouponRepository;
import com.delivery.HomeDelivery.HD.service.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class InitComponent implements InitializingBean {
    @Autowired
    private UserService us;
    @Autowired
    private AdminService as;
    @Autowired
    private MemberShipService ms;
    @Autowired
    private CouponRepository cr;
    @Autowired
    private TimesCardService ts;
    @Autowired
    private RechargeCardService rs;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void afterPropertiesSet() throws Exception {
        int number = 1001;
        User user = us.findUserByNumber(number);
        if (user == null) {
            User u = new User();
            u.setName("ssj");
            u.setNumber(number);
            u.setRole(User.Role.ADMIN);
            u.setPassword(encoder.encode(String.valueOf(number)));
            Admin a = new Admin();
            a.setUser(u);
            as.saveAdmin(a);

        }
        int number2 = 1002;
        User user2 = us.findUserByNumber(number2);
        if (user2 == null) {
            User u = new User();
            u.setNumber(number2);
            u.setName("gdj");
            u.setPassword(encoder.encode(String.valueOf(number2)));
            u.setRole(User.Role.MEMBER);
            MemberShip m = new MemberShip();
            m.setUser(u);
            m.setAdmin(as.findAdminById(1));
            ms.saveMemberShip(m);
        }
        int number3 = 1003;
        Coupon coupon = cr.findCouponByNumber(number3);
        if(coupon == null) {
            Coupon c = new Coupon();
            c.setAdmin(as.findAdminById(1));
            c.setCouponName("套餐卡");
            c.setMemberShip(ms.findMemberShipById(2));
            c.setSpecies(Coupon.Species.TimesCard);
            c.setStatus(Coupon.Status.ACTIVATION);
            c.setNumber(number3);
            TimesCard timesCard = new TimesCard();
            timesCard.setCoupon(c);
            timesCard.setRemainingTimes(10);
            timesCard.setTotalTimes(10);
            ts.saveTimesCard(timesCard);
        }
        int number4 = 1004;
        Coupon coupon4 = cr.findCouponByNumber(number4);
        if(coupon4 == null) {
            Coupon c = new Coupon();
            c.setAdmin(as.findAdminById(1));
            c.setCouponName("余额卡");
            c.setMemberShip(ms.findMemberShipById(2));
            c.setSpecies(Coupon.Species.RechargeCard);
            c.setStatus(Coupon.Status.ACTIVATION);
            c.setNumber(number4);
            RechargeCard rechargeCard = new RechargeCard();
            rechargeCard.setCoupon(c);
            rechargeCard.setTotalAmount(1000);
            rechargeCard.setBalance(850);
            rs.saveRechargeCard(rechargeCard);
        }
        int number5 = 1005;
        Coupon coupon5 = cr.findCouponByNumber(number5);
        if(coupon5 == null) {
            Coupon c = new Coupon();
            c.setAdmin(as.findAdminById(1));
            c.setCouponName("余额卡");
            c.setMemberShip(ms.findMemberShipById(2));
            c.setSpecies(Coupon.Species.RechargeCard);
            c.setStatus(Coupon.Status.UNACTIVATION);
            c.setNumber(number5);
            RechargeCard rechargeCard = new RechargeCard();
            rechargeCard.setCoupon(c);
            rechargeCard.setTotalAmount(1000);
            rechargeCard.setBalance(1000);
            rs.saveRechargeCard(rechargeCard);
        }


    }
}
