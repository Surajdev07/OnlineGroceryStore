package com.groceryshopapi.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.events.EventException;

import com.groceryshopapi.domain.Customer;
import com.groceryshopapi.domain.Event;
import com.groceryshopapi.repository.CustomerRepository;
import com.groceryshopapi.repository.EventRepository;
import com.groceryshopapi.service.EventService;

@Service
public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventRepository EventRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Event saveOrUpdate(Event event) throws EventException {
		// TODO Auto-generated method stub
		String customerIdentifier = event.getCustomerIdentifier(); 
		Customer customer = customerRepository.findByCustomerId(customerIdentifier);
		System.out.println(EventRepository);
		event.setCustomer(customer);
		return EventRepository.save(event);
		
		
	}
}

