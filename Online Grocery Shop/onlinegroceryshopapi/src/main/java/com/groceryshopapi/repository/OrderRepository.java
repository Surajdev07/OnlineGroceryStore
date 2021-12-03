package com.groceryshopapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groceryshopapi.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	public Order findByOrderId(String orderId);
}
