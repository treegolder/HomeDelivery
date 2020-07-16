package com.delivery.HomeDelivery.HD.repository;

import com.delivery.HomeDelivery.HD.entity.Admin;
import com.delivery.HomeDelivery.HD.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends BaseRepository<User,Integer> {
 User findUserByNumber(int number);


}
