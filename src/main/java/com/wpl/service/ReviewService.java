package com.wpl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wpl.dao.ReviewDAO;
import com.wpl.model.Review;
import com.wpl.model.Ride;
import com.wpl.model.User;

@RestController
@RequestMapping("/review")
public class ReviewService 
{
	@Autowired
	private ReviewDAO reviewDAO;
	
	@RequestMapping(value="/createReview",method=RequestMethod.GET)
	public void createReview(@RequestParam("rideId") String rideId,
			@RequestParam("stars") int stars,
			@RequestParam("comments") String comments)
	{
		Ride ride = new Ride();
		ride.setRideId(rideId);
		User user = new User();
		user.setUserId("neel1");
		Review review = new Review();
		review.setRide(ride);
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
