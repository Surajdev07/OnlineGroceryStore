package com.groceryshopapi.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groceryshopapi.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	Category findByCategoryIdentifier(String categoryIdentifier);
}
