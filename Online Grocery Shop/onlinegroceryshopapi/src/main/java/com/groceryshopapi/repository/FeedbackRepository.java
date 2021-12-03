package com.groceryshopapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groceryshopapi.domain.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long>{
	Feedback findById(long id); 
	
}
