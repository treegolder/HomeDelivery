package com.delivery.HomeDelivery.HD.service;

import com.delivery.HomeDelivery.HD.entity.Admin;
import com.delivery.HomeDelivery.HD.entity.Commodity;
import com.delivery.HomeDelivery.HD.entity.User;
import com.delivery.HomeDelivery.HD.repository.CommodityRepository;
import com.delivery.HomeDelivery.HD.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository ur;
    @Autowired
    private CommodityRepository cr;

    public User saveUser(User user) {
        return ur.save(user);
    }
    public User findUserByNumber(int number) {
        return ur.findUserByNumber(number);
    }
    public User findUserById(int id) {
        return ur.findById(id).get();
    }
    public List<Commodity> commodities() {
        return cr.findAll();
    }

}
