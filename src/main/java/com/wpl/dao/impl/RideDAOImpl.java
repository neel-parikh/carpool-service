package com.wpl.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wpl.DBConnection;
import com.wpl.HibernateConfig;
import com.wpl.dao.RideDAO;
import com.wpl.model.Ride;
import com.wpl.model.User;
import com.wpl.model.User_Rides;

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

	public void saveRider(Ride ride, User user) 
	{
		User_Rides userRide = new User_Rides();

		userRide.setRide(ride);
		userRide.setUser(user);

		System.out.println(user.getUserId());
		System.out.println(ride.getRideId());
		
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
	public Ride findByRideId(String rideId) {
		// TODO : IMPLEMENT WITH HIBERNATE
		
		//List<User> users = dbase.getJdbcTemplateObject().query(SQL,params.toArray(),new UserMapper());
		List rides = template.getHibernateTemplate().findByNamedParam("from Ride r where r.rideId=:id","id",rideId);
		if(rides.size()>0)
			return (Ride) rides.get(0);
		else
			return null;
	}
	
	public int countRides()
	{
		String SQL = "select count(*) from RIDE";
		int count = dbase.getJdbcTemplateObject().queryForObject(SQL, Integer.class)+1;
		System.out.println("RIDE COUNT : "+count);
		return count;
	}

	@Override
	public void updateRideVacancy(Ride ride) {
		ride.setVacancy(ride.getVacancy()-1);
		template.getHibernateTemplate().update(ride);
	}
}