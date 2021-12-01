package com.groceryshopapi.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Grocery {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long groceryId;
	@NotBlank(message="Grocery name is required")	
	private String groceryName;
	@NotBlank(message="GroceryIdentifier is required")
	@Column(unique=true,updatable = false)
	private String groceryIdentifier;
	@NotBlank(message="categoryIdentifier is required")
	@Size(min = 4, max = 6 , message ="Size must be between 4 to 6 characters")
	private String categoryIdentifier;
	@NotBlank(message="offerIdentifier is required")
	@Size(min = 4, max = 6 , message ="Size must be between 4 to 6 characters")
	private String offerIdentifier;
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,mappedBy = "groceries")
	private List<Order> orders = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "category_id", updatable=false, nullable=false)
	@JsonIgnore
	private Category category;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "offer_id", updatable=false, nullable=false)
	@JsonIgnore
	private Offer offer;
	private int quantityAvailable;
	
	private double price;
	
	private double netPrice;
	@NotBlank(message="imageLink is required")
	private String imageLink;
	
	
	public String getImageLink() {
		return imageLink;
	}



	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}



	public Grocery() {
		super();
		
	}
	
	
	
	public List<Order> getOrders() {
		return orders;
	}



	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}



	public Long getGroceryId() {
		return groceryId;
	}
	public void setGroceryId(Long groceryId) {
		this.groceryId = groceryId;
	}
	public String getGroceryName() {
		return groceryName;
	}
	public void setGroceryName(String groceryName) {
		this.groceryName = groceryName;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public int getQuantityAvailable() {
		return quantityAvailable;
	}
	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategoryIdentifier() {
		return categoryIdentifier;
	}
	public void setCategoryIdentifier(String categoryIdentifier) {
		this.categoryIdentifier = categoryIdentifier;
	}
		public Offer getOffer() {
		return offer;
	}
	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	public double getNetPrice() {
		return netPrice;
	}
	public void setNetPrice(double netPrice) {
		this.netPrice = netPrice;
	}
	public String getGroceryIdentifier() {
		return groceryIdentifier;
	}
	public void setGroceryIdentifier(String groceryIdentifier) {
		this.groceryIdentifier = groceryIdentifier;
	}
	public String getOfferIdentifier() {
		return offerIdentifier;
	}
	public void setOfferIdentifier(String offerIdentifier) {
		this.offerIdentifier = offerIdentifier;
	}
	
	
	
	
	
}
