package com.springmqdemo.springmvc.dao;

import java.util.Map;

import com.springmqdemo.springmvc.model.Order;

public interface OrderRepository {

	public void saveOrder(Order order);
	
	public Order getOrder(String orderId);
	
	public Map<String, Order> getAllOrders();

	void updateOrder(Order order);
}
