package com.bootcamp.bc_forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BcForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(BcForumApplication.class, args);
	}

}
