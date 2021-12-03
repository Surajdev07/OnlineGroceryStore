package com.groceryshopapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groceryshopapi.domain.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

	Offer findByOfferId(String offerId);
}
