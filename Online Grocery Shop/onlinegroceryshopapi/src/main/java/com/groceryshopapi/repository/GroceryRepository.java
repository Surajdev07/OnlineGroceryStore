package com.groceryshopapi.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groceryshopapi.domain.Grocery;

@Repository
public interface GroceryRepository extends JpaRepository<Grocery, Long> {
		
 	Grocery findByGroceryIdentifier(String groceryIdentifier);
 	Grocery findByCategoryIdentifier(String categoryIdentifier);
}
