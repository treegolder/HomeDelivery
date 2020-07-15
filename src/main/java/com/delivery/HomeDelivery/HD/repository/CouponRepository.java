package com.delivery.HomeDelivery.HD.repository;

import com.delivery.HomeDelivery.HD.entity.Coupon;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends BaseRepository<Coupon,Integer> {
    Coupon findCouponByNumber(int number);
    @Query("from Coupon c where c.status='ACTIVATION'")
    List<Coupon> findAcCards();

    List<Coupon> findCouponByMemberShipIsNull();
}
