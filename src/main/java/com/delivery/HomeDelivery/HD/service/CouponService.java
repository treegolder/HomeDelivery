package com.delivery.HomeDelivery.HD.service;

import com.delivery.HomeDelivery.HD.entity.Coupon;
import com.delivery.HomeDelivery.HD.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponService {
    @Autowired
    private CouponRepository cr;

    public Coupon findCouponByNumber(int number) {
        return cr.findCouponByNumber(number);
    }

    public List<Coupon> findAll() {
        return cr.findAll();
    }
}
