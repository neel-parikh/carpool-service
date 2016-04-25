package com.wpl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wpl.model.ViewRide;

public class ViewRideMapper implements RowMapper<ViewRide>
{

  @Override
  public ViewRide mapRow(ResultSet rs, int arg1) throws SQLException 
  {
    ViewRide userride = new ViewRide();
    userride.setRideId(rs.getString("rideId"));
    userride.setRideDate(rs.getString("rideDate"));
    userride.setStars(rs.getFloat("stars"));
    userride.setRideStartLocation(rs.getString("rideStartLocation"));
    userride.setRideEndLocation(rs.getString("rideEndLocation"));
    userride.setRideTime(rs.getString("rideTime"));
    return userride;
  } 

}