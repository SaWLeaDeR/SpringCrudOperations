package com.mkoyuncuoglu.security.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
public class GuestServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuestServicesApplication.class, args);
	}
}
