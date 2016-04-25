package com.wpl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wpl.dao.RideDAO;
import com.wpl.model.Ride;
import com.wpl.model.User;

@RestController
@RequestMapping("/ride")
public class RideService 
{
	@Autowired
	private RideDAO rideDAO;
	
	@Autowired
	private UserService userservice;
	
	@RequestMapping(value="/createRide",method=RequestMethod.POST)
	public Ride createRide(@RequestBody Ride ride)
	{
		String rideId = "R"+rideDAO.countRides();
		ride.setRideId(rideId);
		rideDAO.save(ride);
		return ride;
	}
	
	@RequestMapping(value="/saveRider",method=RequestMethod.GET)
	public void saveRider(@RequestParam("rideId") String rideId,@RequestParam("userId") String userId) {

		Ride ride = getRide(rideId);
		User user = userservice.getUser(userId);
		
		rideDAO.saveRider(ride, user);
	}
	
	@RequestMapping(value="/getRide",method=RequestMethod.GET)
	public Ride getRide(@RequestParam("rideId") String rideId) {
		Ride ride = rideDAO.findByRideId(rideId);
		return ride;
	}
}
