package com.skcc.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
@EnableJpaAuditing
@SpringBootApplication
public class AuthSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthSampleApplication.class, args);
	}

}
