package com.bootcamp.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class YahooFinanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(YahooFinanceApplication.class, args);
	}

}
