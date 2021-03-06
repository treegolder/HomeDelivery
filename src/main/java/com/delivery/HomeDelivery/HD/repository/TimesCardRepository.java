package com.delivery.HomeDelivery.HD.repository;

import com.delivery.HomeDelivery.HD.entity.Coupon;
import com.delivery.HomeDelivery.HD.entity.TimesCard;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimesCardRepository extends BaseRepository<TimesCard,Integer>{
    @Query("select t from TimesCard t where t.id=:id and t.coupon.status='ACTIVATION'")
    TimesCard findAcTimesCardById(int id);

    @Query("select t from TimesCard t where t.id=:id and t.coupon.status='UNACTIVATION'")
    TimesCard findUnAcTimesCardById(int id);

    @Query("select t from TimesCard t where t.id=:id and t.coupon.status='DELETED'")
    TimesCard findDelTimesCardById(int id);

    @Query("from TimesCard t where t.coupon.status='UNACTIVATION'")
    List<TimesCard> findUnAcCards();
    @Query("from TimesCard t where t.coupon.status='ACTIVATION'")
    List<TimesCard> findAcCards();
    @Query("from TimesCard t where t.coupon.status='DELETED'")
    List<TimesCard> findDelCards();

    void deleteByCoupon(Coupon coupon);

    TimesCard findByCoupon(Coupon coupon);
}
