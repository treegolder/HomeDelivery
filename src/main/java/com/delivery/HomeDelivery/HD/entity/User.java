package com.delivery.HomeDelivery.HD.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter@Setter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    //private String gender;
    @Column(unique = true)
    private Integer number;
    private String password;

    public enum Role {
        MEMBER("MEMBER"),DISTRIBUTOR("DISTRIBUTOR"),ADMIN("ADMIN");
        private String code;
        private Role(String code) {
            this.code = code;
        }
        @Override
        public String toString(){
            return code;
        }
        public static User.Role parseCode(String code){
            for(User.Role r: User.Role.values()){
                if(r.code.equalsIgnoreCase(code))return r;
            }
            return null;
        }
    }
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(columnDefinition = "timestamp default current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime insertTime;

}
