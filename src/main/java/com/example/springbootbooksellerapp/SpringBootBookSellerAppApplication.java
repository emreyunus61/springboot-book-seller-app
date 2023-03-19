package com.example.springbootbooksellerapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application-${spring.profiles.active:default}.properties")
public class SpringBootBookSellerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBookSellerAppApplication.class, args);
	}

}
