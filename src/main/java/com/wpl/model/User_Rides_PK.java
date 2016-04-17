package com.wpl.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class User_Rides_PK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String userId;
	private String rideId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRideId() {
		return rideId;
	}
	public void setRideId(String rideId) {
		this.rideId = rideId;
	}	
}
