package com.pizza.order.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Beans {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
