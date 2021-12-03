package com.groceryshopapi.service;

import com.groceryshopapi.domain.Category;
import com.groceryshopapi.domain.Grocery;

public interface CategoryService {

	public Category save(Category category);
	public Iterable<Category> getCategories();
	public Category findCategoryByCategoryIdentifier(String categoryIdentifier);
	public void deleteCategoryByCategoryIdentifier(String categoryIdentifier);
}
