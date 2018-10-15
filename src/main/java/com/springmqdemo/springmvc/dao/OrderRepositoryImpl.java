package com.springmqdemo.springmvc.dao;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springmqdemo.springmvc.model.Order;

@Repository("orderRepository")
public class OrderRepositoryImpl implements OrderRepository{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void saveOrder(Order order) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(order);
	}
	
	@Override
	public void updateOrder(Order order) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(order);
	}

	@Override
	public Order getOrder(String orderId) {
		Session session = this.sessionFactory.getCurrentSession();		
		Order o = (Order) session.load(Order.class, new Integer(orderId));
		return o;
	}

	public Map<String, Order> getAllOrders(){
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Order> orders = session.createQuery("from Order").list();
		return orders.stream().collect(Collectors.toMap(Order::getOrderId, Function.identity()));
	}
}
