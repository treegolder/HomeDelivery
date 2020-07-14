package com.delivery.HomeDelivery.HD.repository;

import com.delivery.HomeDelivery.HD.entity.Coupon;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends BaseRepository<Coupon,Integer> {
    Coupon findCouponByNumber(int number);
}
