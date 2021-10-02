package com.pizza.order;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


import com.pizza.order.model.OrderDetails;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = PizzaOrderApplication.class)
class PizzaOrderApplicationTests {

	@Test
	void testGenerateOrderService() {
		RestTemplate rstTemplate = new RestTemplate();
		URI uri = null;
		try {
			uri = new URI("http://localhost:8080/order");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		OrderDetails ordDtls = new OrderDetails();
		RequestEntity<OrderDetails> reqEnty = new RequestEntity<>(ordDtls, HttpMethod.POST, uri);
		
		ResponseEntity<OrderDetails> respEnty= rstTemplate.exchange(reqEnty, OrderDetails.class);
		
		assertThat(respEnty.getBody().getId());
		
	}

}
