package com.groceryshopapi.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GroceryIDException extends RuntimeException {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GroceryIDException()
	 {
		 super();
	 }
	 
	 public GroceryIDException(String errMsg) {
		 super(errMsg);
		 
	 }

}
