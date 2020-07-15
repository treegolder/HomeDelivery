package com.delivery.HomeDelivery.HD.service;

import com.delivery.HomeDelivery.HD.entity.Coupon;
import com.delivery.HomeDelivery.HD.entity.RechargeCard;
import com.delivery.HomeDelivery.HD.entity.TimesCard;
import com.delivery.HomeDelivery.HD.repository.RechargeCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RechargeCardService {
    @Autowired
    private RechargeCardRepository rr;

    public RechargeCard saveRechargeCard(RechargeCard rechargeCard) {
        return rr.save(rechargeCard);
    }
    public void deleteRechargeCard(RechargeCard rechargeCard) {
        rr.delete(rechargeCard);
    }
    public RechargeCard deleteRechargeCardById(int id) {
        RechargeCard rechargeCard = findRechargeCardById(id);
        deleteRechargeCard(rechargeCard);
        return rechargeCard;
    }
    public RechargeCard findRechargeCardById(int id) {
        return rr.findById(id).get();
    }
    public void updateRechargeCard(RechargeCard rechargeCard) {
        saveRechargeCard(rechargeCard);
    }
    public RechargeCard findAcRechargeCardById(int id) {
        return rr.findAcRechargeCardById(id);
    }
    public RechargeCard findUnAcRechargeCardById(int id) {
        return rr.findUnAcRechargeCardById(id);
    }
    public RechargeCard findDelRechargeCardById(int id) {
        return rr.findDelRechargeCardById(id);
    }
    public void logicDelete(RechargeCard rechargeCard) {
        if (rechargeCard.getBalance() == 0)
            rechargeCard.getCoupon().setStatus(Coupon.Status.DELETED);
    }
    public List<RechargeCard> findAll() {
        return rr.findAll();
    }
    public List<RechargeCard> findUnAcCards() {
        return rr.findUnAcCards();
    }
    public List<RechargeCard> findAcCards() {
        return rr.findAcCards();
    }
}
