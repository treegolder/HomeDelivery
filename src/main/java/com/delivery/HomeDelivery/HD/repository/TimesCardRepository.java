package com.delivery.HomeDelivery.HD.repository;

import com.delivery.HomeDelivery.HD.entity.TimesCard;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TimesCardRepository extends BaseRepository<TimesCard,Integer>{
    @Query("select t from TimesCard t where t.id=:id and t.coupon.status='ACTIVATION'")
    TimesCard findAcTimesCardById(int id);

    @Query("select t from TimesCard t where t.id=:id and t.coupon.status='UNACTIVATION'")
    TimesCard findUnAcTimesCardById(int id);

    @Query("select t from TimesCard t where t.id=:id and t.coupon.status='DELETED'")
    TimesCard findDelTimesCardById(int id);
}
