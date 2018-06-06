package com.tghd.automaticmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AutomaticmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomaticmanagerApplication.class, args);
	}
}
