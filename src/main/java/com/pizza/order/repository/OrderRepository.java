package com.pizza.order.repository;

import com.pizza.order.model.OrderDetails;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderDetails, Integer> {

}
