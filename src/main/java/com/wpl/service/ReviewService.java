package com.wpl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wpl.dao.impl.ReviewDAOImpl;
import com.wpl.model.Review;

@RestController
@RequestMapping("/review")
public class ReviewService 
{
	@Autowired
	private ReviewDAOImpl reviewDAO;
	
	@RequestMapping(value="/createReview",method=RequestMethod.GET)
	public void createReview(@RequestParam("stars") int stars,
			@RequestParam("comments") String comments)
	{
		Review review = new Review();
		review.setStars(stars);
		review.setComments(comments);
		reviewDAO.save(review);
	}
	
	@RequestMapping(value="/getReview",method=RequestMethod.GET)
	public Review getReview(@RequestParam("reviewId") String reviewId) {
		Review review = reviewDAO.findByReviewId(reviewId);
		return review;
	}
}
