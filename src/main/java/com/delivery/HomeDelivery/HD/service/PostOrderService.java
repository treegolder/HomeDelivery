package com.delivery.HomeDelivery.HD.service;

import com.delivery.HomeDelivery.HD.entity.PostOrder;
import com.delivery.HomeDelivery.HD.repository.PostOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;

@Service
public class PostOrderService {
  @Autowired private PostOrderRepository pr;

  public PostOrder savePostorder(PostOrder postOrder) {
    return pr.save(postOrder);
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

  public PostOrder updatePostOrder(PostOrder postOrder) {
    return savePostorder(postOrder);
  }

  public PostOrder findPostOrderByNumber(String number) {
    return pr.findPostOrderByNumber(number);
  }
}
