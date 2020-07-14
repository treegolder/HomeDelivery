package com.delivery.HomeDelivery.HD.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter@Getter
@NoArgsConstructor
public class PostOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //订单编号
    private String number;
    //收货人
    private String name;
    @ManyToOne
    private MemberShip memberShip;
    private String tel;
    private String country;
    private String province;
    private String city;
    private String address;
    public enum Status{
        PROCESSING("待发货"),SHIPPED("已发货"),SHIPPING("配送中"),RECEIVED("已签收");
        private String code;
        private Status(String code) {
            this.code = code;
        }
        @Override
        public String toString(){
            return code;
        }
        public static Status parseCode(String code){
            for(Status s: Status.values()){
                if(s.code.equalsIgnoreCase(code))return s;
            }
            return null;
        }
    }
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToOne
    private Coupon coupon;
    @Column(columnDefinition = "timestamp default current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime insertTime;

    private int count = 0;

}
