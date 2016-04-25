package com.wpl.model;

public class ViewRide {
  private String rideId;
  private String rideDate;
  private String rideTime;
  private String rideStartLocation;
  private String rideEndLocation;
  private String comments;
  private float stars;
  
  public String getComments() {
    return comments;
  }
  public void setComments(String comments) {
    this.comments = comments;
  }
  public float getStars() {
    return stars;
  }
  public void setStars(float stars) {
    this.stars = stars;
  }
  
  
  
  
  public String getRideId() {
    return rideId;
  }
  public void setRideId(String rideId) {
    this.rideId = rideId;
  }
  public String getRideDate() {
    return rideDate;
  }
  public void setRideDate(String rideDate) {
    this.rideDate = rideDate;
  }
  public String getRideTime() {
    return rideTime;
  }
  public void setRideTime(String rideTime) {
    this.rideTime = rideTime;
  }
  public String getRideStartLocation() {
    return rideStartLocation;
  }
  public void setRideStartLocation(String rideStartLocation) {
    this.rideStartLocation = rideStartLocation;
  }
  public String getRideEndLocation() {
    return rideEndLocation;
  }
  public void setRideEndLocation(String rideEndLocation) {
    this.rideEndLocation = rideEndLocation;
  }

}