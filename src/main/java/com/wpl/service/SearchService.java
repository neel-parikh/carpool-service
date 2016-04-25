package com.wpl.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wpl.dao.SearchDAO;
import com.wpl.model.ViewRide;

@RestController
@RequestMapping("/search")
public class SearchService {
  
  @Autowired
  private SearchDAO searchDAO;
  
  
  @RequestMapping(value="/searchRide",method=RequestMethod.GET)
  public List searchRide(@RequestParam("rideDate") String rdate) {
    List rides = searchDAO.search_Ride(rdate);
    
    return rides;
  }
  @RequestMapping(value="/getRide",method=RequestMethod.GET)
  public List showRide(@RequestParam("userId") String userId)
  {
    List<ViewRide> rides = searchDAO.show_Ride(userId);
    return rides;
  }
}