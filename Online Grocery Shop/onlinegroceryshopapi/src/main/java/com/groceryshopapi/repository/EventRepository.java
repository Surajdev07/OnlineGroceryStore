package com.groceryshopapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groceryshopapi.domain.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>{
	
}
