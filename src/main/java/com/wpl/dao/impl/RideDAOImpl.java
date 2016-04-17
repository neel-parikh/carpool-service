package com.wpl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.wpl.DBConnection;
import com.wpl.dao.RideDAO;
import com.wpl.mapper.RideMapper;
import com.wpl.model.Ride;

/*public class RideDAOImpl extends HibernateDaoSupport implements RideDAO {*/

@Component
public class RideDAOImpl implements RideDAO {

	@Autowired
	private DBConnection dbase;
	
	@Override
	public void save(Ride ride) {
		String rideId = "RV"+countRides();
		String SQL = "insert into RIDE values()";
		ride.setRideId(rideId);
		dbase.getJdbcTemplateObject().update(SQL,ride);
	}

	@Override
	public void update(Ride ride) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Ride ride) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Cacheable
	public Ride findByRideId(String rideId) {
		// TODO : IMPLEMENT WITH HIBERNATE
		String SQL = "select * from carpool.RIDE where ride_id = ?";
		List params = new ArrayList();
		params.add(rideId);
		List<Ride> rides = dbase.getJdbcTemplateObject().query(SQL,params.toArray(),new RideMapper());
		return rides.get(0);
	}
	
	private int countRides()
	{
		String SQL = "select count(*) from RIDE";
		return dbase.getJdbcTemplateObject().queryForObject(SQL, Integer.class)+1;
	}
}