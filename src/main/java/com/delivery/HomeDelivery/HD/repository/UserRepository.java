package com.delivery.HomeDelivery.HD.repository;

import com.delivery.HomeDelivery.HD.entity.Admin;
import com.delivery.HomeDelivery.HD.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User,Integer> {
 User findUserByNumber(int number);


}
