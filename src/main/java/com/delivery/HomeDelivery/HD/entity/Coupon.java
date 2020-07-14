package com.delivery.HomeDelivery.HD.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter@Setter
@NoArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String couponName;
    //卡号
    @Column(unique = true)
    private int number;
    //激活状态
    public enum Status{
        ACTIVATION("ACTIVATION"),UNACTIVATION("UNACTIVATION"),DELETED("DELETED");
        private String code;
       private Status(String code) {
            this.code = code;
        }
        @Override
        public String toString(){
            return code;
        }
        public static Status parseCode(String code){
            for(Status s:Status.values()){
                if(s.code.equalsIgnoreCase(code))return s;
            }
            return null;
        }
    }
    //卡卷的种类
    public enum Species{
        //充值卡、次数卡
        RechargeCard("RechargeCard"),TimesCard("TimesCard");
        private String code;
        private Species(String code) {
            this.code = code;
        }
        @Override
        public String toString(){
            return code;
        }
        public static Status parseCode(String code){
            for(Status s:Status.values()){
                if(s.code.equalsIgnoreCase(code))return s;
            }
            return null;
        }
    }
    @Enumerated(EnumType.STRING)
    private Species species;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    private Admin admin;
    @ManyToOne
    private MemberShip memberShip;

    @Column(columnDefinition = "timestamp default current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime insertTime;
}
