package com.wpl.model;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Review 
{
	@Id
	private String reviewId;
	
	@JoinColumn(name="userId")
	@ManyToOne(cascade=CascadeType.ALL)
	private User user;
	
	@JoinColumn(name="rideId")
	@ManyToOne(cascade=CascadeType.ALL)
	private Ride ride;
	
	private int stars;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Ride getRide() {
		return ride;
	}
	public void setRide(Ride ride) {
		this.ride = ride;
	}
	public String getReviewId() {
		return reviewId;
	}
	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}
	
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
}
