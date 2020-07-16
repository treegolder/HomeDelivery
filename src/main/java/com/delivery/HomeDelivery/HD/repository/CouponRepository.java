package com.delivery.HomeDelivery.HD.repository;

import com.delivery.HomeDelivery.HD.entity.Coupon;
import com.delivery.HomeDelivery.HD.entity.MemberShip;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CouponRepository extends BaseRepository<Coupon,Integer> {
    Coupon findCouponByNumber(int number);
    @Query("from Coupon c where c.status='ACTIVATION'")
    List<Coupon> findAcCards();

    List<Coupon> findCouponByMemberShipIsNull();

    List<Coupon> findByMemberShip(MemberShip membership);

}
