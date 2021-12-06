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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.groceryshopapi.domain.Category;
import com.groceryshopapi.domain.Grocery;
import com.groceryshopapi.exception.GroceryIDException;
import com.groceryshopapi.service.GroceryService;
import com.groceryshopapi.serviceimpl.MapValidationErrorService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/grocery")
public class GroceryContoller {
	
	@Autowired
	private GroceryService groceryService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
		
	@CrossOrigin("http://localhost:4200")
	@GetMapping("/list")
	public Iterable<Grocery> getGrocery(){
		return groceryService.getGrocery();
	}

//	@CrossOrigin("http://localhost:4200")
//	@PostMapping("/add")
//	public ResponseEntity<?> createGrocery(@Valid @RequestBody Grocery grocery, BindingResult result){
//		
//		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
//		if(errorMap!=null) return errorMap;
//		
//		Grocery grocery2 = groceryService.save(grocery);
//		return new ResponseEntity<Grocery>(grocery2, HttpStatus.CREATED);
//	}
	
	
	@CrossOrigin("http://localhost:4200")
	@PostMapping("/add")
	public String addgrocery(@RequestParam String id) {
		System.out.println(id); 
		String categoryIdentifier1=id.split("-")[0];
		String groceryidentifier=id.split("-")[1];
		String groceryName=id.split("-")[2];
		String imageLink1=id.split("-")[3];
		String netPrice=id.split("-")[4];
		String offerIdentifier=id.split("-")[5];
		String price=id.split("-")[6];
		String quantityAvailable=id.split("-")[7];
		System.out.println(categoryIdentifier1+":"+groceryidentifier+":"+groceryName+":");
//		String routerLink=id.split("-")[3];
		System.out.println("success");
		Grocery grocery = new Grocery();
		grocery.setCategoryIdentifier(categoryIdentifier1);
		grocery.setGroceryIdentifier(groceryidentifier);
		grocery.setGroceryName(groceryName);
		grocery.setImageLink(imageLink1);
		grocery.setNetPrice(0);
		grocery.setOfferIdentifier(offerIdentifier);
		grocery.setPrice(0);
		grocery.setQuantityAvailable(0);
		
		groceryService.save(grocery);
		System.out.println("success");
		return id;
		}

//	@CrossOrigin("http://localhost:4200")
//	@GetMapping("/{categoryIdentifier}")
//	public ResponseEntity<?> getGroceryByGroceryIdentifier(@PathVariable String categoryIdentifier){
//		
//		Grocery grocery= groceryService.findGroceryByCategoryIdentifier(categoryIdentifier);
//		if(grocery==null) {
//			throw new GroceryIDException("Grocery Identifier " +categoryIdentifier.toUpperCase()+" does not exist"); 
//		}
//		return new ResponseEntity<Grocery>(grocery,HttpStatus.OK);
//		
//	}
	
	@CrossOrigin("http://localhost:4200")
	@GetMapping("/{categoryIdentifier}")
	public Iterable<Grocery> getGroceryByGroceryIdentifier(@PathVariable String categoryIdentifier){
		return groceryService.findGroceryByCategoryIdentifier(categoryIdentifier) ;
	}
	
	@CrossOrigin("http://localhost:4200")
	@DeleteMapping("/{groceryIdentifier}")
	public ResponseEntity<?> deleteGrocery(@PathVariable String groceryIdentifier){
		groceryService.deleteGroceryByGroceryIdentifier(groceryIdentifier);
		return new ResponseEntity<String>(groceryIdentifier.toUpperCase()+" deleted sucessfully",HttpStatus.OK);
	}
	

}
