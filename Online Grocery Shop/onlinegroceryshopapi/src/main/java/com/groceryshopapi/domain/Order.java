package com.groceryshopapi.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="Orders") 
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message="customerIdentfier is required")
	@Size(min = 4, max = 6 , message ="Size must be between 4 to 6 characters")
	private String customerIdentifier;
	
	@Column(columnDefinition = "text")
	@NotBlank(message="paymentMethod is required")
	private String paymentMethod;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "customer_id", updatable=false, nullable=false)
	@JsonIgnore
	private Customer customer;
	

	@Column(columnDefinition = "text")
	@NotBlank(message="orderId is required")
	@Size(min = 4, max = 6 , message ="Size must be between 4 to 6 characters")
	private String orderId;
	
	@NotBlank(message="deliveryAddress is required")
	private String deliveryAddress;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date orderDate;
	
	@NotBlank(message="groceryIdentifier is required")
	private String groceryIdentifier;

	@Column(columnDefinition = "text")
	@NotBlank(message="orderDetails is required")
	private String orderDetails;

	private double orderPrice; 
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "fb_id", nullable = false)
	private Feedback feedback;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="orders_grocery",
			   joinColumns = {
					   		@JoinColumn(name = "order_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
			   inverseJoinColumns = {
					   		@JoinColumn(name = "grocery_id", referencedColumnName = "groceryId",
                            nullable = false, updatable = false)})
	@JsonIgnore
	private List<Grocery> groceries  = new ArrayList<>();	
	
	
	
	
	public String getGroceryIdentifier() {
		return groceryIdentifier;
	}


	public void setGroceryIdentifier(String groceryIdentifier) {
		this.groceryIdentifier = groceryIdentifier;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Order() {
		super();
	}

	
	public String getCustomerIdentifier() {
		return customerIdentifier;
	}


	public void setCustomerIdentifier(String customerIdentifier) {
		this.customerIdentifier = customerIdentifier;
	}


	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}


	public String getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(String orderDetails) {
		this.orderDetails = orderDetails;
	}





	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}
	

	public List<Grocery> getGrocery() {
		return groceries;
	}


	public void setGrocery(List<Grocery> groceries) {
		this.groceries = groceries;
	}


	@PrePersist
	public void onOrder() {
		this.orderDate = new Date();
	}

}
