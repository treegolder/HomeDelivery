package com.delivery.HomeDelivery.HD.service;

import com.delivery.HomeDelivery.HD.entity.Coupon;
import com.delivery.HomeDelivery.HD.entity.TimesCard;
import com.delivery.HomeDelivery.HD.repository.CouponRepository;
import com.delivery.HomeDelivery.HD.repository.TimesCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimesCardService {
    @Autowired
    private TimesCardRepository tr;
    @Autowired
    private CouponRepository cr;

    public TimesCard saveTimesCard(TimesCard timesCard) {
        return tr.save(timesCard);
    }
    public void deleteTimesCard(TimesCard timesCard) {
        tr.delete(timesCard);
    }
    public TimesCard deleteTimesCardById(int id) {
        TimesCard timesCard = findTimesCardById(id);
        deleteTimesCard(timesCard);
        return timesCard;
    }
    public TimesCard findTimesCardById(int id) {
        return tr.findById(id).get();
    }
    public void updateTimesCard(TimesCard timesCard) {
        saveTimesCard(timesCard);
    }
    public TimesCard findAcTimesCardById(int id) {
        return tr.findAcTimesCardById(id);
    }
    public TimesCard findUnAcTimesCardById(int id) {
        return tr.findUnAcTimesCardById(id);
    }
    public TimesCard findDelTimesCardById(int id) {
        return tr.findDelTimesCardById(id);
    }
    public void logicDelete(TimesCard timesCard) {
        if (timesCard.getRemainingTimes() <= 0)
            timesCard.getCoupon().setStatus(Coupon.Status.DELETED);
        updateTimesCard(timesCard);
        cr.save(timesCard.getCoupon());

    }
    public List<TimesCard> findAll() {
        return tr.findAll();
    }
    public List<TimesCard> findUnAcCards() {
        return tr.findUnAcCards();
    }
    public List<TimesCard> findAcCards() {
        return tr.findAcCards();
    }

}
