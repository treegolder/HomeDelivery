package com.delivery.HomeDelivery.HD.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter@Setter
@NoArgsConstructor
public class MemberShip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @MapsId
    private User user;
    @ManyToOne
    Admin admin;
    @OneToMany(mappedBy = "memberShip")
    private List<Coupon> coupons;
    @OneToMany(mappedBy = "memberShip")
    private List<PostOrder> postOrders;

}
