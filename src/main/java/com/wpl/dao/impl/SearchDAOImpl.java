package com.wpl.dao.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wpl.DBConnection;
import com.wpl.HibernateConfig;
import com.wpl.dao.SearchDAO;
import com.wpl.mapper.ViewRideMapper;
import com.wpl.model.ViewRide;

@Component
public class SearchDAOImpl implements SearchDAO{
  
  @Autowired
  private DBConnection dbase;
  
  @Autowired
  private HibernateConfig template;
  
  
  public List search_Ride(String ride){
    List rides = template.getHibernateTemplate().findByNamedParam("from Ride where rideDate=:rideDate and vacancy > 0", "rideDate", ride);
    if(rides.size()>0)
      return rides;
    else
      return null;
  }
  
  public String countReviewsPerRide(String rideId)
  {
	  	String SQL = "select count(stars) from REVIEW where rideId='"+rideId+"'";
		int sum = dbase.getJdbcTemplateObject().queryForObject(SQL, Integer.class);
		String val;
		if(sum!=0)
		{
			String SQL1 = "select avg(stars) from REVIEW where rideId='"+rideId+"'";
			float avg = dbase.getJdbcTemplateObject().queryForObject(SQL1, Float.class);
			DecimalFormat df = new DecimalFormat("#.#");
			val = df.format(avg);
		}
		else
		{
			val = "0";
		}
		
		return val;
  }
  
  public List show_Ride(String userId){
    String SQL = "select rd.rideId,rd.rideStartLocation,rd.rideEndLocation,rd.rideDate,rw.stars,rd.rideTime"
           +" from ride rd inner join user_rides ur on ur.rideId = rd.rideId left join review rw on rd.rideId = rw.rideId"
           +" where ur.userId = ?";
/*    List ride = template.getHibernateTemplate().findByNamedParam(
        "select rd.rideId,rd.rideStartLocation,rd.rideEndLocation,rd.rideDate,rw.stars,rd.rideTime"+
        " from Ride rd,Review rw,User_Rides ur"+
        " where rd.rideId = rw.rideId and ur.userId = :uid and ur.rideId = rd.rideId"
        , "uid", userId);*/
    List params = new ArrayList();
    params.add(userId);
    List<ViewRide> rides = dbase.getJdbcTemplateObject().query(SQL,params.toArray(),new ViewRideMapper());
    List<ViewRide> newrides = new ArrayList<ViewRide>();
    for(ViewRide ride : rides)
    {
    	ride.setStars(Float.parseFloat(countReviewsPerRide(ride.getRideId())));
    	newrides.add(ride);
    }
    if(newrides.size()>0)
      return newrides;
    else
      return null;
  }
}