package com.groceryshopapi.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groceryshopapi.domain.Category;
import com.groceryshopapi.exception.GroceryIDException;
import com.groceryshopapi.service.CategoryService;
import com.groceryshopapi.serviceimpl.MapValidationErrorService;

@RestController

@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/Categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@CrossOrigin("http://localhost:4200")
	@GetMapping("/list")
	public Iterable<Category> getGrocery(){
		return categoryService.getCategories();
	}
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@CrossOrigin("http://localhost:4200")
	@PostMapping("/add")
	public ResponseEntity<?> createCategory(@Valid @RequestBody Category category,BindingResult bindingResult)
	{
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(bindingResult);
		if (errorMap != null)
			return errorMap;
		Category category2 = categoryService.save(category);
		return new ResponseEntity<Category>(category2, HttpStatus.CREATED); 
	}
	
	@CrossOrigin("http://localhost:4200")
	@GetMapping("/{categoryIdentifier}")
	public ResponseEntity<?> getCategoryByCategoryIdentifier(@PathVariable String categoryIdentifier){
		
		Category category= categoryService.findCategoryByCategoryIdentifier(categoryIdentifier);
		if(category==null) {
			throw new GroceryIDException("Category " +categoryIdentifier+" does not exist"); 
		}
		return new ResponseEntity<Category>(category,HttpStatus.OK);
		
	}
	
	@CrossOrigin("http://localhost:4200")
	@DeleteMapping("/{categoryIdentifier}")
	public ResponseEntity<?> deleteCategory(@PathVariable String categoryIdentifier){
		categoryService.deleteCategoryByCategoryIdentifier(categoryIdentifier);
		return new ResponseEntity<String>(categoryIdentifier+" deleted sucessfully",HttpStatus.OK);
		
	}
	
	
}
