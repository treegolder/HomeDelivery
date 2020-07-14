package com.delivery.HomeDelivery.HD.service;

import com.delivery.HomeDelivery.HD.entity.Admin;
import com.delivery.HomeDelivery.HD.repository.AdminRepository;
import com.delivery.HomeDelivery.HD.repository.CommodityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository ar;
    @Autowired
    private CommodityRepository cr;

    public Admin saveAdmin(Admin admin) {
        return ar.save(admin);
    }
    public void deleteAdmin(Admin admin) {
        ar.delete(admin);
    }
    public Admin deleteAdminById(int id) {
        Admin admin = findAdminById(id);
        deleteAdmin(admin);
        return admin;
    }
    public Admin findAdminById(int id) {
        return ar.findById(id).get();
    }
    public Admin updateAdmin(Admin admin) {
       // return ar.save(admin);
        return saveAdmin(admin);
    }


}
