package com.delivery.HomeDelivery.HD.service;

import com.delivery.HomeDelivery.HD.entity.Coupon;
import com.delivery.HomeDelivery.HD.entity.MemberShip;
import com.delivery.HomeDelivery.HD.repository.MemberShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberShipService {
    @Autowired
    private MemberShipRepository mr;
            /*会员的CRUD操作*/
    public MemberShip saveMemberShip(MemberShip memberShip) {
        return mr.save(memberShip);
    }
    public void deleteMemberShip(MemberShip memberShip) {
        mr.delete(memberShip);
    }
    public MemberShip deleteMemberShipById(int id) {
        MemberShip memberShip = findMemberShipById(id);
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
