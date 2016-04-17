package com.wpl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Component;

import com.wpl.DBConnection;
import com.wpl.HibernateConfig;
import com.wpl.dao.RideDAO;
import com.wpl.mapper.RideMapper;
import com.wpl.model.Ride;
import com.wpl.model.User_Rides;
import com.wpl.model.User_Rides_PK;

/*public class RideDAOImpl extends HibernateDaoSupport implements RideDAO {*/

@Component
@Transactional
public class RideDAOImpl implements RideDAO {

	@Autowired
	private DBConnection dbase;
	
	@Autowired
	private HibernateConfig template;
	
	@Override
	public void save(Ride ride) {
		//String SQL = "insert into RIDE values()";
		//dbase.getJdbcTemplateObject().update(SQL,ride);
		template.getHibernateTemplate().save(ride);
	}

	public void saveRider(String userId,String rideId) 
	{
		int riderNo = DataAccessUtils.intResult(template.getHibernateTemplate().findByNamedParam
				("select count(*) from User_Rides where rideId=:rideId","rideId",rideId));
		User_Rides userRide = new User_Rides();
		User_Rides_PK userRidePk = new User_Rides_PK();
		userRidePk.setRideId(rideId);
		userRidePk.setUserId(userId);
		userRide.setPkey(userRidePk);
		userRide.setRiderNo(riderNo+1);
		template.getHibernateTemplate().save(userRide);
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
	
	public int countRides()
	{
		String SQL = "select count(*) from RIDE";
		return dbase.getJdbcTemplateObject().queryForObject(SQL, Integer.class)+1;
	}
}