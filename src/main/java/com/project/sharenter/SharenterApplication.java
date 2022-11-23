package com.project.sharenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SharenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SharenterApplication.class, args);
	}
//	@Bean
//	public RestTemplate getRestTemplate(){
//		return new RestTemplate();
//	}
}
