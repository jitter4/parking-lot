package com.example.parkinglot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BaseConfig {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

//	@Bean
//	public MessageConverter getMessageConvertor() {
//		return new Jackson2JsonMessageConverter();
//	}

	@Bean
	public PasswordEncoder getPassswordEndcoder() {
		return new BCryptPasswordEncoder();
	}

}
