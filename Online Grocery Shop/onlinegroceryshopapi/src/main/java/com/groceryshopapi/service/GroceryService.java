 package com.groceryshopapi.service; 


import com.groceryshopapi.domain.Grocery;
import com.groceryshopapi.exception.GroceryIDException;

public interface GroceryService {

	public Grocery save(Grocery grocery);
	public Iterable<Grocery> getGrocery();
	public void deleteGroceryByGroceryIdentifier(String groceryIdentifier);
	public Iterable<Grocery> findGroceryByCategoryIdentifier(String categoryIdentifier);
}
