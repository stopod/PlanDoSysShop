package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class PlanDoSysShopApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PlanDoSysShopApplication.class, args);
	}

}
