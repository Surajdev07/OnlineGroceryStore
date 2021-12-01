package com.groceryshopapi.exception;

public class GroceryIDExceptionResponse {
	
	private String groceryIdentifer;
	
	public GroceryIDExceptionResponse(String groceryIdentifer) {
		super();
		this.groceryIdentifer = groceryIdentifer;
	}

	public String getgroceryIdentifer() {
		return groceryIdentifer;
	}

	public void setgroceryIdentifer(String groceryIdentifer) {
		this.groceryIdentifer = groceryIdentifer;
	}
	
	
}
