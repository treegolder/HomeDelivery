package com.delivery.HomeDelivery.HD.repository;

import com.delivery.HomeDelivery.HD.entity.PostOrder;
import org.springframework.stereotype.Repository;

@Repository
public interface PostOrderRepository extends BaseRepository<PostOrder,Integer>{
    PostOrder findPostOrderByNumber(String number);
}
