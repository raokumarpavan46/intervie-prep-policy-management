package com.policymanagement.spring.jpa.h2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.policymanageement.spring.jpa.h2.controller.PolicyController;

@SpringBootApplication
@ComponentScan(basePackageClasses = PolicyController.class)
public class SpringBootJpaH2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaH2Application.class, args);
	}

}
