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

import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Category {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int categoryId;
	@NotBlank(message="categoryIdentifier is required")
	@Column(unique=true,updatable = false)
	@Size(min = 4, max = 6 , message ="Size must be between 4 to 6 characters")
	private String categoryIdentifier;
	@NotBlank(message="categoryName is required")
	private String categoryName;
	@NotBlank(message="routerLink is required")
	private String routerLink;
	@NotBlank(message="imageLink is required")
	private String imageLink;
	
	public String getImageLink() {
		return imageLink;
	}
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "category")
	@JsonIgnore
	private List<Grocery> groceries= new ArrayList<Grocery>();
	
	
	
	public Category() {	
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCategoryIdentifier() {
		return categoryIdentifier;
	}
	public void setCategoryIdentifier(String categoryIdentifier) {
		this.categoryIdentifier = categoryIdentifier;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getRouterLink() {
		return routerLink;
	}
	public void setRouterLink(String routerLink) {
		this.routerLink = routerLink;
	}
	public List<Grocery> getGrocery() {
		return groceries;
	}
	public void setGrocery(List<Grocery> groceries) {
		this.groceries = groceries;
	}
	
	
}
