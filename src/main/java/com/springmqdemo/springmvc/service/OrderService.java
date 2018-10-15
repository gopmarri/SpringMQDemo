package com.springmqdemo.springmvc.service;

import java.util.Map;

import com.springmqdemo.springmvc.model.InventoryResponse;
import com.springmqdemo.springmvc.model.Order;

public interface OrderService {
	public void sendOrder(Order order);
	
	public void updateOrder(InventoryResponse response);
	
	public Map<String, Order> getAllOrders();
}
