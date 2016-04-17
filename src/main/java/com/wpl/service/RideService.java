package com.wpl.service;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wpl.dao.RideDAO;
import com.wpl.model.Ride;

@RestController
@RequestMapping("/ride")
public class RideService 
{
	@Autowired
	private RideDAO rideDAO;
	
	@RequestMapping(value="/createRide",method=RequestMethod.GET)
	public void createRide(@RequestParam("rideDate") String rideDate,
			@RequestParam("rideTime") String rideTime,
			@RequestParam("rideStartLocation") String rideStartLocation,
			@RequestParam("rideEndLocation") String rideEndLocation,
			@RequestParam("vacancy") int vacancy)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		rideDate = dateFormat.format(rideDate);
		
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		rideTime = timeFormat.format(rideTime);
		Ride ride = new Ride();
		ride.setRideDate(rideDate);
		ride.setRideEndLocation(rideEndLocation);
		ride.setRideStartLocation(rideStartLocation);
		ride.setRideTime(rideTime);
		ride.setVacancy(vacancy);
		
		rideDAO.save(ride);
	}
	
	@RequestMapping(value="/getRide",method=RequestMethod.GET)
	public Ride getRide(@RequestParam("rideId") String rideId) {
		Ride ride = rideDAO.findByRideId(rideId);
		return ride;
	}
}
