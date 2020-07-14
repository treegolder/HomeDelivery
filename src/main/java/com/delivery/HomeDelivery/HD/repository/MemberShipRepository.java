package com.delivery.HomeDelivery.HD.repository;

import com.delivery.HomeDelivery.HD.entity.Coupon;
import com.delivery.HomeDelivery.HD.entity.MemberShip;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberShipRepository extends BaseRepository<MemberShip,Integer>{
    @Query("select c from Coupon c where c.status='ACTIVATION' and c.memberShip.id=:id")
    List<Coupon> findAcCoupon(int id);
}
