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
	
	@JoinColumn(name="userId",referencedColumnName = "userId",insertable=false, updatable=false)
	@ManyToOne(cascade=CascadeType.ALL)
	private User user;
	
	@JoinColumn(name="rideId",referencedColumnName = "rideId",insertable=false, updatable=false)
	@ManyToOne(cascade=CascadeType.ALL)
	private Ride ride;
	
	
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
	private String comments;
	private int stars;
	public String getReviewId() {
		return reviewId;
	}
	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
}
