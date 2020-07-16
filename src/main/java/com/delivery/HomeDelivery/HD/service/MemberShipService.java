package com.delivery.HomeDelivery.HD.service;

import com.delivery.HomeDelivery.HD.entity.*;
import com.delivery.HomeDelivery.HD.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberShipService {
    @Autowired
    private MemberShipRepository mr;
    @Autowired
    private CouponRepository cr;
    @Autowired
    private RechargeCardRepository rr;
    @Autowired
    private TimesCardRepository tr;
    @Autowired
    private PostOrderRepository pr;
            /*会员的CRUD操作*/
    public MemberShip saveMemberShip(MemberShip memberShip) {
        return mr.save(memberShip);
    }
    public void deleteMemberShip(MemberShip memberShip) {
        memberShip.setPostOrders(null);
        saveMemberShip(memberShip);
        mr.delete(memberShip);
    }
    @Transactional
    public MemberShip deleteMemberShipById(int id) {
        MemberShip memberShip = findMemberShipById(id);
        List<PostOrder> orders = pr.findPostOrderByMemberShip(memberShip);
        for (PostOrder p : orders) {
            p.setMemberShip(null);
            p.setCoupon(null);
            p.setCommodity(null);
            pr.save(p);
        }
        pr.deleteAll(orders);
        List<Coupon> coupons = cr.findByMemberShip(memberShip);
        List<RechargeCard> r = new ArrayList<>();
        List<TimesCard> t = new ArrayList<>();
        for (Coupon c : coupons) {
            RechargeCard RCoupon = rr.findByCoupon(c);
            if (RCoupon != null)
                r.add(RCoupon);
            TimesCard TCoupon = tr.findByCoupon(c);
            if (TCoupon != null)
                t.add(TCoupon);
        }
        rr.deleteAll(r);
        tr.deleteAll(t);

        deleteMemberShip(memberShip);

        return memberShip;
    }
    public MemberShip findMemberShipById(int id) {
        return mr.findById(id).get();
    }
    public MemberShip updateMemberShip(MemberShip memberShip) {
        return saveMemberShip(memberShip);
    }
    public List<Coupon> findAcCouponsByMemberId(int id) {
        return mr.findAcCoupon(id);
    }
    public List<MemberShip> findAll() {
        return mr.findAll();
    }

}
