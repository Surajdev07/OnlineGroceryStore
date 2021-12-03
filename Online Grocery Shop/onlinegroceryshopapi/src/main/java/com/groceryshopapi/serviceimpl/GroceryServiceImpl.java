package com.groceryshopapi.serviceimpl;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groceryshopapi.domain.Category;
import com.groceryshopapi.domain.Offer;
import com.groceryshopapi.domain.Grocery;
import com.groceryshopapi.exception.GroceryIDException;
import com.groceryshopapi.repository.CategoryRepository;
import com.groceryshopapi.repository.OfferRepository;
import com.groceryshopapi.repository.GroceryRepository;
import com.groceryshopapi.service.GroceryService;

@Service
public class GroceryServiceImpl implements GroceryService {

	@Autowired
	private GroceryRepository groceryRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private OfferRepository offerRepository;
	
	@Override
	public Grocery save(Grocery grocery){
		// TODO Auto-generated method stub
		grocery.setGroceryIdentifier(grocery.getGroceryIdentifier().toUpperCase()); 
		try { 
			String categoryIdentifier = grocery.getCategoryIdentifier();
			String offerIdentifier = grocery.getOfferIdentifier();
			Category category = categoryRepository.findByCategoryIdentifier(categoryIdentifier);
			Offer offer = offerRepository.findByOfferId(offerIdentifier);
			double price = grocery.getPrice();
			double discount = offer.getOfferDiscount();
			double discountPrice = (discount/100)*price;
			double netPrice = price- discountPrice;
			grocery.setNetPrice(netPrice);
			grocery.setCategory(category);
			grocery.setOffer(offer);
			
			return groceryRepository.save(grocery); 
		}
		catch(Exception e)
		{
			throw new GroceryIDException("Grocery"+ grocery.getGroceryIdentifier() + " cannot be entered.");
			
		}
	}

	@Override
	public Iterable<Grocery> getGrocery() {
		// TODO Auto-generated method stub
		return groceryRepository.findAll();
	}

	@Override
	public Iterable<Grocery> findGroceryByCategoryIdentifier(String categoryIdentifier) {
		List<Grocery> groceries = groceryRepository.findAll();
		List<Grocery> output = new ArrayList<>();
		Grocery groc  = new Grocery(); 
		for(Grocery grocery:groceries) {
			
			if(grocery.getCategoryIdentifier().equals(categoryIdentifier)) {
				System.out.println(grocery.getCategoryIdentifier());
				System.out.println(grocery.getGroceryName()); 
				groc.setGroceryId(grocery.getGroceryId());
				groc.setGroceryIdentifier(grocery.getGroceryIdentifier());
				groc.setGroceryName(grocery.getGroceryName());
				groc.setCategoryIdentifier(grocery.getCategoryIdentifier());
				groc.setImageLink(grocery.getImageLink());
				output.add(grocery);
			}
		}
		for(Grocery gro:output) {
//		System.out.println(gro);
		}
		return output;
		
		
		
	}

	@Override
	public void deleteGroceryByGroceryIdentifier(String groceryIdentifier) {
		// TODO Auto-generated method stub
		Grocery grocery = groceryRepository.findByGroceryIdentifier(groceryIdentifier.toUpperCase());
		if(grocery==null)
		{
			throw new GroceryIDException(groceryIdentifier +" This grocery does not exist");
		}
		groceryRepository.delete(grocery);
		
	}





	

}
