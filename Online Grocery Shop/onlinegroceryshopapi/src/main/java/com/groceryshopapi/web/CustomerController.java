package com.groceryshopapi.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.groceryshopapi.domain.Customer;
import com.groceryshopapi.exception.CustomerIdException;
import com.groceryshopapi.repository.CustomerRepository;
import com.groceryshopapi.service.CustomerService;
import com.groceryshopapi.serviceimpl.MapValidationErrorService;


@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	Logger logger = LoggerFactory.getLogger(CustomerController.class);
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	
	@PostMapping("/add")
	public ResponseEntity<?> createCustomer(@Valid @RequestBody Customer customer, BindingResult result) {
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if (errorMap != null)
			return errorMap;
		Customer createdCustomer= customerService.saveOrUpdate(customer);
		return new ResponseEntity<Customer>(createdCustomer, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> getCustomerByCustomerId(@PathVariable String customerId) {
		Customer customer = customerService.findCustomerByCustomerId(customerId);
		if(customer==null)
		{
			throw new CustomerIdException("CustomerId "+customerId.toUpperCase() +" does not exist");
		}
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public Iterable<Customer> getAllCustomers(){
		return customerService.findAllCustomers();
	}
	
	@DeleteMapping("/{customerId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable String customerId){
		customerService.deleteCustomerByCustomerId(customerId);
		return new ResponseEntity<String>("Customer With id : "+customerId.toUpperCase()+" is deleted successfully",HttpStatus.OK);
	}
	
	@PatchMapping("/{customerId}/{isloggedIn}")
	public ResponseEntity<?> updateCustomer(@Valid @PathVariable String customerId,@PathVariable int isloggedIn) {
		customerService.updateByCustomerId(customerId, isloggedIn);
		Customer customer = customerService.findCustomerByCustomerId(customerId);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		
	}
	@CrossOrigin("http://localhost:4200")
	@PostMapping("/signin")
	public String signin(@RequestParam String id) {
//		customerService.updateByCustomerId(customerId, isloggedIn);
//		Customer customer = customerService.findCustomerByCustomerId(customerId);
		System.out.println(id);
		String email=id.split("-")[0];
		String password=id.split("-")[1];
		System.out.println(email);
		Customer c = new Customer();
		c.setCustomerEmail(email);
		
		Customer clist =  customerRepository.findByCustomerId(email);
		System.out.println((clist.getCustomerPassword().trim()).equals(password.trim()));
		if((clist.getCustomerPassword().trim()).equals(password.trim())) {
			System.out.println("password matched");
		}
		else {
			System.out.println("pass not match");
		}
		return id;
		
		
	}
	
	@CrossOrigin("http://localhost:4200")
	@PostMapping("/signup")
	public String signup(@RequestParam String id) {
//		System.out.println(id);
		String email=id.split("-")[1];
		String password=id.split("-")[2];
		String username=id.split("-")[0];
//		System.out.println(email);
//		System.out.println(password);
//		System.out.println(username);
		Customer customer = new Customer();
		customer.setCustomerEmail(email);
		customer.setCustomerId(username);
		customer.setCustomerName(username);
		customer.setCustomerPassword(password);
		customerRepository.save(customer);
		
		return id;
		}
}
