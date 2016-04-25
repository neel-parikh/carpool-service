package com.wpl.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wpl.dao.RideDAO;
import com.wpl.dao.UserDAO;
import com.wpl.model.Ride;
import com.wpl.model.User;

@RestController
@RequestMapping("/cart")
public class CartService {
	
	@Autowired
	private RideDAO rideDAO;
	
	@Autowired
	private UserDAO userDAO;

	@RequestMapping(value="/checkOut",method=RequestMethod.GET)
	public User checkOut(@RequestParam("userId") String userId,@RequestParam("rides") String rideIds)
	{
		System.out.println("RIDE OBJECT : "+rideIds);
		List<Ride> rides = new ArrayList<Ride>();
		String[] rideArr = rideIds.split(",");
		for(String rideId : rideArr) {
			rides.add(rideDAO.findByRideId(rideId.trim()));
		}
		User user = userDAO.findByUserId(userId);
		System.out.println(user.getEmailId() + "   " + user.getUserId());
		for(Ride ride : rides) {
			rideDAO.saveRider(ride, user);
			rideDAO.updateRideVacancy(ride);
		}
		return user;
	}

}
