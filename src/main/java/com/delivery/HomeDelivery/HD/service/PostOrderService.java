package com.delivery.HomeDelivery.HD.service;

import com.delivery.HomeDelivery.HD.component.SnowFlake;
import com.delivery.HomeDelivery.HD.component.Util;
import com.delivery.HomeDelivery.HD.entity.*;
import com.delivery.HomeDelivery.HD.repository.CommodityRepository;
import com.delivery.HomeDelivery.HD.repository.CouponRepository;
import com.delivery.HomeDelivery.HD.repository.PostOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;

@Service
public class PostOrderService {
  @Autowired
  private PostOrderRepository pr;
  @Autowired
  private CommodityRepository commodityRepositoryr;
  @Autowired
  private CouponRepository cr;
  @Autowired
  private SnowFlake sf;
  @Autowired
  private Util util;
  @Autowired
  private  TimesCardService ts;
  public String savePostorder(PostOrder postOrder) {
    String number = pr.save(postOrder).getNumber();
    return number;
  }

  public void deletePostOrder(PostOrder postOrder) {
    pr.delete(postOrder);
  }

  public PostOrder deletePostOrderById(int id) {
    PostOrder postOrder = findPostOrderById(id);
    deletePostOrder(postOrder);
    return postOrder;
  }

  public PostOrder findPostOrderById(int id) {
    return pr.findById(id).get();
  }

  public void updatePostOrder(PostOrder postOrder) {
    savePostorder(postOrder);
  }

  public PostOrder findPostOrderByNumber(String number) {
    return pr.findPostOrderByNumber(number);
  }
  public Commodity findCommodityById(int id) {
    return commodityRepositoryr.findById(id).get();
  }
  @Scheduled(fixedDelay = 5000)
  public void ScheduledDelivery() {
    while (util.getI() == 0) {

    }

    //PostOrder postOrder = pr.findFirstByOrderByIdDesc();
    PostOrder postOrder = pr.findPostOrderByNumber(util.getNumber());
    Integer id = postOrder.getCoupon().getId();
    Coupon coupon = cr.findById(id).get();
    Commodity commodity = postOrder.getCommodity();
    String province = postOrder.getProvince();
    String tel = postOrder.getTel();
    String city = postOrder.getCity();
    String address = postOrder.getAddress();
    String country = postOrder.getCountry();
    String name = postOrder.getName();
    MemberShip memberShip = postOrder.getMemberShip();
    PostOrder postOrder1 = new PostOrder();
    postOrder1.setCommodity(commodity);
    postOrder1.setCount(0);
    postOrder1.setStatus(PostOrder.Status.PROCESSING);
    postOrder1.setNumber(String.valueOf(sf.getNextId()));
    postOrder1.setProvince(province);
    postOrder1.setTel(tel);
    postOrder1.setCountry(country);
    postOrder1.setCity(city);
    postOrder1.setAddress(address);
    postOrder1.setMemberShip(memberShip);
    postOrder1.setName(name);
    TimesCard timesCardById = ts.findTimesCardById(coupon.getId());
    timesCardById.setRemainingTimes(timesCardById.getRemainingTimes() - 1);

    ts.updateTimesCard(timesCardById);
    postOrder1.setCoupon(coupon);

    savePostorder(postOrder1);
    if (timesCardById.getRemainingTimes() <= 0){
      util.setI(0);
      ts.logicDelete(timesCardById);
    }



  }
}
