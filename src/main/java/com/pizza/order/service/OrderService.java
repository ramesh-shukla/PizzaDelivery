package com.pizza.order.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.pizza.order.model.OrderDetails;
import com.pizza.order.repository.OrderRepository;
import org.slf4j.Logger;

@RestController
public class OrderService {

	@Autowired
	RestTemplate rstTmplt;

	@Autowired
	OrderRepository ordRepo;
	
	private static final Logger LOG = LoggerFactory.getLogger(OrderService.class);

	/***
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/order")
	public ResponseEntity<OrderDetails> generateOrder() {
		
		LOG.info("Order generating");

		OrderDetails ord = new OrderDetails();
		ord.setName("Pizza Name");
		ord.setPrice(22.5);
		ord.setQuantity(10);

		URI uri = null;
		try {
			uri = new URI("http://localhost:8080/order");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		LOG.info("Order Service call");
		RequestEntity<OrderDetails> reqEnty = new RequestEntity<>(ord, HttpMethod.POST, uri);
		LOG.info("Response received");
		return rstTmplt.exchange(reqEnty, OrderDetails.class);

	}

	@RequestMapping(method = RequestMethod.POST, path = "/order")
	public OrderDetails saveOrder(@RequestBody OrderDetails ord) {
		LOG.info("Order received :"+ord);
		return ordRepo.save(ord);

	}

	@RequestMapping(method = RequestMethod.GET, path = "/orders")
	public List<OrderDetails> getOrders() {
		
		List<OrderDetails> lst = new ArrayList<>();
		ordRepo.findAll().forEach((s) -> {
			lst.add(s);
		});
		return lst;

	}

}
