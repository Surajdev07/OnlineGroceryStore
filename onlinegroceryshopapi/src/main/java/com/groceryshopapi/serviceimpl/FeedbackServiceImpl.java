package com.groceryshopapi.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groceryshopapi.domain.Feedback;
import com.groceryshopapi.repository.FeedbackRepository;
import com.groceryshopapi.service.FeedbackService;
@Service
public class FeedbackServiceImpl implements FeedbackService {
	
	@Autowired
	private FeedbackRepository feedbackRepository;
	
	public Feedback saveOrUpdate(Feedback feedback) {
		// TODO Auto-generated method stub
		System.out.println(feedbackRepository);
		return feedbackRepository.save(feedback);
				
	}


	



	

}
