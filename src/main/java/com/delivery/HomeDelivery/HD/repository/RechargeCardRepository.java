package com.delivery.HomeDelivery.HD.repository;

import com.delivery.HomeDelivery.HD.entity.RechargeCard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RechargeCardRepository extends BaseRepository<RechargeCard,Integer>{
    @Query("from RechargeCard r where r.id=:id and r.coupon.status='ACTIVATION'")
    RechargeCard findAcRechargeCardById(int id);

    @Query("from RechargeCard r where r.id=:id and r.coupon.status='UNACTIVATION'")
    RechargeCard findUnAcRechargeCardById(int id);

    @Query("from RechargeCard r where r.id=:id and r.coupon.status='DELETED'")
    RechargeCard findDelRechargeCardById(int id);
}
