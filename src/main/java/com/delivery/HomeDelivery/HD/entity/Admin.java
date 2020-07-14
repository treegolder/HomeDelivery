package com.delivery.HomeDelivery.HD.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter@Setter
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @MapsId
    private User user;
    @OneToMany(mappedBy = "admin")
    private List<MemberShip> memberShips;
    @OneToMany(mappedBy = "admin")
    private List<Distributor> distributors;
    @OneToMany(mappedBy = "admin")
    private List<Coupon> coupons;
}
