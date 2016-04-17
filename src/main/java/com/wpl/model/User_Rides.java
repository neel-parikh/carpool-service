package com.wpl.model;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class User_Rides 
{
	@EmbeddedId
	private User_Rides_PK pkey;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="userId",referencedColumnName = "userId", insertable=false, updatable=false)
	private User user;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="rideId",referencedColumnName = "rideId", insertable=false, updatable=false)
	private Ride ride;
	private int riderNo;
	
	public User_Rides_PK getPkey() {
		return pkey;
	}

	public void setPkey(User_Rides_PK pkey) {
		this.pkey = pkey;
	}

	public int getRiderNo() {
		return riderNo;
	}
	public void setRiderNo(int riderNo) {
		this.riderNo = riderNo;
	}

	public User getUserId() {
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
}
