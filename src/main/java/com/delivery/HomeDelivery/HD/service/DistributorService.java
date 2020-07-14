package com.delivery.HomeDelivery.HD.service;

import com.delivery.HomeDelivery.HD.entity.Distributor;
import com.delivery.HomeDelivery.HD.repository.DistributorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistributorService {
    @Autowired
    private DistributorRepository dr;

    public Distributor saveDistributor(Distributor distributor) {
        return dr.save(distributor);
    }
    public void deleteDistributor(Distributor distributor) {
        dr.delete(distributor);
    }
    public Distributor deleteDistributorById(int id) {
        Distributor distributor = findDistributorById(id);
        deleteDistributor(distributor);
        return distributor;
    }
    public Distributor findDistributorById(int id) {
        return dr.findById(id).get();
    }
    public Distributor updateDistributor(Distributor distributor) {
       return  saveDistributor(distributor);
    }
}
