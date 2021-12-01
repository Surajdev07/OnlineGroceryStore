package com.groceryshopapi.serviceimpl;

import java.util.List;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groceryshopapi.domain.Customer;
import com.groceryshopapi.domain.Feedback;
import com.groceryshopapi.domain.Order;
import com.groceryshopapi.domain.Grocery;
import com.groceryshopapi.exception.OrderIdException;
import com.groceryshopapi.repository.CustomerRepository;
import com.groceryshopapi.repository.OrderRepository;
import com.groceryshopapi.repository.GroceryRepository;
import com.groceryshopapi.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private GroceryRepository groceryRepository;

	@Autowired
	private CustomerRepository customerRepository;
	
	public Order saveOrUpdate(Order order) throws OrderIdException{
		// TODO Auto-generated method stub
		
		try {
		if(order.getId() == null) {
			
			String groceryIdentifier = order.getGroceryIdentifier();
			Grocery grocery = groceryRepository.findByGroceryIdentifier(groceryIdentifier);
			String customerIdentifier = order.getCustomerIdentifier();
			Customer customer = customerRepository.findByCustomerId(customerIdentifier);
			Feedback feedback = new Feedback();
			List<Grocery> groceries = order.getGrocery();
			groceries.add(grocery);
			double orderPrice = grocery.getNetPrice();
			order.setGrocery(groceries);
			order.setFeedback(feedback);
			order.setOrderPrice(orderPrice);
			order.setOrderId(order.getOrderId().toUpperCase());
			order.setCustomer(customer);
		}
		
		return orderRepository.save(order);
		}
		catch(Exception e)
		{
			throw new OrderIdException("Order Id" + order.getOrderId()+ " is already available");
		}
		
	}

	@Override
	public Iterable<Order> findAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public Order findOrderByOrderId(String orderId) {
		Order order = orderRepository.findByOrderId(orderId);
		return order;

	}
	
	@Override
	public void deleteOrderByOrderId(String orderId) {
		Order order= orderRepository.findByOrderId(orderId.toUpperCase());
		if(order==null) {
			throw new OrderIdException("Can not delete order with order id "+orderId.toUpperCase()+". This id does not exist");
		}
		
		orderRepository.delete(order);
		
	}
	



	

}
