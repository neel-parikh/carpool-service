package com.wpl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wpl.dao.ReviewDAO;
import com.wpl.dao.RideDAO;
import com.wpl.dao.UserDAO;
import com.wpl.model.Review;
import com.wpl.model.Ride;
import com.wpl.model.User;

@RestController
@RequestMapping("/review")
public class ReviewService 
{
	@Autowired
	private ReviewDAO reviewDAO;
	
	@Autowired
	private RideDAO rideDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(value="/addReview",method=RequestMethod.GET)
	public void addReview(@RequestParam("userId") String userId,@RequestParam("rideId") String rideId,
			@RequestParam("stars") String stars)
	{
		Ride ride = rideDAO.findByRideId(rideId);
		User user = userDAO.findByUserId(userId);
		Review review = new Review();
		review.setUser(user);
		review.setStars(Integer.parseInt(stars));
		review.setRide(ride);
		//review.setComments(comments);
		
		Review rv = reviewDAO.findByExistingUserAndRide(userId, rideId);
		if(rv!=null)
		{
			rv.setUser(user);
			rv.setRide(ride);
			rv.setStars(Integer.parseInt(stars));
			reviewDAO.update(rv);
		}
		else{
		reviewDAO.save(review);
		}
	}
	
	@RequestMapping(value="/getReview",method=RequestMethod.GET)
	public Review getReview(@RequestParam("reviewId") String reviewId) {
		Review review = reviewDAO.findByReviewId(reviewId);
		return review;
	}
}