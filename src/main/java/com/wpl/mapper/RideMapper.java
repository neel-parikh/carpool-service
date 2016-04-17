package com.wpl.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.wpl.model.Ride;

@Component
public class RideMapper implements RowMapper<Ride>
{

	@Override
	public Ride mapRow(ResultSet rs, int arg1) throws SQLException 
	{
		Ride ride = new Ride();
		ride.setRideId(rs.getString("ride_id"));
		ride.setRideDate(rs.getString("ride_date"));
		ride.setRideTime(rs.getString("ride_time"));
		ride.setRideStartLocation(rs.getString("ride_start_location"));
		ride.setRideEndLocation(rs.getString("ride_end_location"));
		ride.setVacancy(rs.getInt("vacancy"));
		return ride;
	}
	
}
