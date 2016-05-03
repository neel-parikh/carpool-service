package com.wpl.dao.impl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.wpl.DBConnection;
import com.wpl.HibernateConfig;
import com.wpl.dao.UserDAO;
import com.wpl.model.User;

import net.spy.memcached.MemcachedClient;

@Service
@EnableTransactionManagement
public class UserDAOImpl implements UserDAO {

	private static Logger LOGGER = Logger.getLogger(UserDAOImpl.class);
	
	
	@Autowired
	private DBConnection dbase;
	
	@Autowired
	private HibernateConfig template;
	
	@Transactional
	public void save(User user) {
		// TODO Auto-generated method stub
		//dbase.getHibernateTemplate().save(user);
		template.getHibernateTemplate().save(user);
	}

	@Transactional
	public void update(User user) {
		// TODO Auto-generated method stub
		template.getHibernateTemplate().update(user);
	/*	String SQL = "update user set userId=?,password=?,lastLogin=?,loginAttempts=?,"
				+ "firstName=?,lastName=?,emailId=?,phoneNo=? where userId=?";
		List params = new ArrayList<>();
		params.add(user.getUserId());
		params.add(user.getPassword());
		params.add(user.getLastLogin());
		params.add(user.getLoginAttempts());
		params.add(user.getFirstName());
		params.add(user.getLastName());
		params.add(user.getEmailId());
		params.add(user.getPhoneNo());
		params.add(user.getUserId());
		
		dbase.getJdbcTemplateObject().update(SQL, params.toArray());*/
	}

	public void delete(User user) {
		// TODO Auto-generated method stub
		
	}

	//@Cacheable("mycache")
	public User findByUserId(String userId) {
		//String SQL = "select * from carpool.USER where user_id = ?";
		List params = new ArrayList();
		params.add(userId);
		//List<User> users = dbase.getJdbcTemplateObject().query(SQL,params.toArray(),new UserMapper());
		List users = template.getHibernateTemplate().findByNamedParam("from User u where u.userId=:id","id",userId);
		if(users.size()>0)
			return (User) users.get(0);
		else
			return null;
	}
	
	//@Cacheable("mycache")
	public int checkUserInDB(String userId,String password)
	{
		List users = null;
		System.out.println("CACHE MISS");
		List params = new ArrayList();
		params.add(userId);
		params.add(password);
		String str[] = {"userId","password"};
		//int result = dbase.getJdbcTemplateObject().queryForObject(SQL, params.toArray(),Integer.class);
		users = template.getHibernateTemplate().findByNamedParam("from User where userId=:userId and password=:password",
				str,params.toArray());
		return users.size();
	}
	
	public int getIncorrectAttempts(String userId) {
		LOGGER.info("CALCULATING INCORRECT ATTEMPTS");
		return DataAccessUtils.intResult(template.getHibernateTemplate().findByNamedParam
				("select loginAttempts from User where userId=:userId","userId",userId));
	}

	public void updateIncorrectAttempts(String userId) {
		LOGGER.info("UPDATING INCORRECT ATTEMPTS");
		User user = findByUserId(userId);
		user.setLoginAttempts(getIncorrectAttempts(userId)+1);
		template.getHibernateTemplate().update(user);
	}
}