package com.oliver.ElectronicCommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ElectronicCommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectronicCommerceApplication.class, args);
	}
}
