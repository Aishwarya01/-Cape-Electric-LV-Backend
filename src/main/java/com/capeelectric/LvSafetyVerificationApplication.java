package com.capeelectric;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.capeelectric.repository.UserRepository;

@SpringBootApplication
public class LvSafetyVerificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(LvSafetyVerificationApplication.class, args);
	}

}
