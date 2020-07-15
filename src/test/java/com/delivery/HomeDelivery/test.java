package com.delivery.HomeDelivery;


import com.delivery.HomeDelivery.HD.controller.PostOrderController;
import com.delivery.HomeDelivery.HD.repository.PostOrderRepository;
import com.delivery.HomeDelivery.HD.service.PostOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class test {
  @Autowired PostOrderRepository pr;
  @Autowired PostOrderService ps;
  @Autowired PostOrderController pc;
  @Test
  public void dishi() {

  System.out.println(pr.findFirstByOrderByIdDesc().getId());
  }
}
