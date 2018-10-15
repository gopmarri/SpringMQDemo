package com.springmqdemo.springmvc.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmqdemo.springmvc.dao.OrderRepository;
import com.springmqdemo.springmvc.exception.DemoException;
import com.springmqdemo.springmvc.messaging.MessageSender;
import com.springmqdemo.springmvc.model.InventoryResponse;
import com.springmqdemo.springmvc.model.Order;
import com.springmqdemo.springmvc.model.OrderStatus;
import com.springmqdemo.springmvc.util.BasicUtil;

@Service("orderService")
public class OrderServiceImpl implements OrderService{

	static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	MessageSender messageSender;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public void sendOrder(Order order) {
		try {
			order.setOrderId(BasicUtil.getUniqueId());
			order.setStatus(OrderStatus.CREATED);
			orderRepository.saveOrder(order);
			LOG.info("Application : sending order request {}", order);
			messageSender.sendMessage(order);
		} catch(Exception e) {
			throw new DemoException(e);
		}
	}

	@Override
	public void updateOrder(InventoryResponse response) {
		try {
			Order order = orderRepository.getOrder(response.getOrderId());
			if(response.getReturnCode()==200){
				order.setStatus(OrderStatus.CONFIRMED);
			}else if(response.getReturnCode()==300){
				order.setStatus(OrderStatus.FAILED);
			}else{
				order.setStatus(OrderStatus.PENDING);
			}
			orderRepository.updateOrder(order);
		} catch(Exception e) {
			throw new DemoException(e);
		}
	}
	
	public Map<String, Order> getAllOrders() {
		try {
			return orderRepository.getAllOrders();
		} catch(Exception e) {
			throw new DemoException(e);
		}
	}

}
