package com.delivery.HomeDelivery;

import com.delivery.HomeDelivery.HD.repository.impl.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
@EnableScheduling
//@MapperScan("com.delivery.HomeDelivery.HD.mapper")
public class HomeDeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeDeliveryApplication.class, args);
	}
	@Bean//直接将passwordEncoder组件注入容器，容器中大家一起用
	public PasswordEncoder getPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}


	/*@Bean
	public HttpFirewall allowUrlSemicolonHttpFirewall() {
		StrictHttpFirewall firewall = new StrictHttpFirewall();
		firewall.setAllowSemicolon(true);
		return firewall;
	}*/

}
