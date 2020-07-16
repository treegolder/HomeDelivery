package com.delivery.HomeDelivery.HD.repository;

import com.delivery.HomeDelivery.HD.entity.MemberShip;
import com.delivery.HomeDelivery.HD.entity.PostOrder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PostOrderRepository extends BaseRepository<PostOrder,Integer>{
    PostOrder findPostOrderByNumber(String number);

    PostOrder findFirstByOrderByMemberShipDesc();
    PostOrder findFirstByOrderByIdDesc();

    List<PostOrder> findPostOrderByMemberShip(MemberShip membership);
}
