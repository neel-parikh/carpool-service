package com.wpl.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
	public void createRide(@RequestParam("userId") String userId,
			@RequestParam("rideDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date rideDate,
			@RequestParam("rideTime") @DateTimeFormat(pattern="HH:mm:ss") Date rideTime,
			@RequestParam("rideStartLocation") String rideStartLocation,
			@RequestParam("rideEndLocation") String rideEndLocation,
			@RequestParam("vacancy") int vacancy)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String rDate = dateFormat.format(rideDate);
		
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		String rTime = timeFormat.format(rideTime);
		String rideId = "R"+rideDAO.countRides();

		Ride ride = new Ride();
		ride.setRideDate(rDate);
		ride.setRideEndLocation(rideEndLocation);
		ride.setRideStartLocation(rideStartLocation);
		ride.setRideTime(rTime);
		ride.setVacancy(vacancy);
		ride.setRideId(rideId);
		
		rideDAO.save(ride);
		rideDAO.saveRider(rideId,userId);
		System.out.println("Ride saved");
	}
	
	@RequestMapping(value="/saveRider",method=RequestMethod.GET)
	public void saveRider(@RequestParam("rideId") String rideId,@RequestParam("userId") String userId) {
		rideDAO.saveRider(rideId, userId);
	}
	
	@RequestMapping(value="/getRide",method=RequestMethod.GET)
	public Ride getRide(@RequestParam("rideId") String rideId) {
		Ride ride = rideDAO.findByRideId(rideId);
		return ride;
	}
}
