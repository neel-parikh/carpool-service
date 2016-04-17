package com.wpl.dao;

import org.springframework.stereotype.Component;

import com.wpl.model.Ride;

@Component
public interface RideDAO {
	
	void save(Ride ride);
	void update(Ride ride);
	void delete(Ride ride);
	Ride findByRideId(String rideId);
	void saveRider(String rideId,String userId);
	int countRides();
}