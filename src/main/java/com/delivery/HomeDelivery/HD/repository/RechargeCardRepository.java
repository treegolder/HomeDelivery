package com.delivery.HomeDelivery.HD.repository;

import com.delivery.HomeDelivery.HD.entity.Coupon;
import com.delivery.HomeDelivery.HD.entity.RechargeCard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface RechargeCardRepository extends BaseRepository<RechargeCard,Integer>{
    @Query("from RechargeCard r where r.id=:id and r.coupon.status='ACTIVATION'")
    RechargeCard findAcRechargeCardById(int id);

    @Query("from RechargeCard r where r.id=:id and r.coupon.status='UNACTIVATION'")
    RechargeCard findUnAcRechargeCardById(int id);

    @Query("from RechargeCard r where r.id=:id and r.coupon.status='DELETED'")
    RechargeCard findDelRechargeCardById(int id);
    @Query("from RechargeCard r where r.coupon.status='UNACTIVATION'")
    List<RechargeCard> findUnAcCards();
    @Query("from RechargeCard r where r.coupon.status='ACTIVATION'")
    List<RechargeCard> findAcCards();
    @Query("from RechargeCard r where r.coupon.status='DELETED'")
    List<RechargeCard> findDelCards();

    void deleteByCoupon(Coupon coupon);

    RechargeCard findByCoupon(Coupon coupon);
}
