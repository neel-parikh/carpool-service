package com.wpl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.wpl.DBConnection;
import com.wpl.HibernateConfig;
import com.wpl.dao.UserDAO;
import com.wpl.model.User;

@Service
public class UserDAOImpl implements UserDAO {

	@Autowired
	private DBConnection dbase;
	
	@Autowired
	private HibernateConfig template;
	
	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		//dbase.getHibernateTemplate().save(user);
		template.getHibernateTemplate().save(user);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User findByUserId(String userId) {
		//String SQL = "select * from carpool.USER where user_id = ?";
		List params = new ArrayList();
		params.add(userId);
		//List<User> users = dbase.getJdbcTemplateObject().query(SQL,params.toArray(),new UserMapper());
		List users = template.getHibernateTemplate().findByNamedParam("from User u where u.userId=:id","id",userId);
		return (User) users.get(0);
	}
	
	@Cacheable
	public int checkUserInDB(String userId,String password)
	{
		/*String SQL;
		SQL = "select count(*) from carpool.user where user_id=? and password=?";
		*/List params = new ArrayList();
		params.add(userId);
		params.add(password);
		String str[] = {"userId","password"};
		//int result = dbase.getJdbcTemplateObject().queryForObject(SQL, params.toArray(),Integer.class);
		List users = template.getHibernateTemplate().findByNamedParam("from User where userId=:userId and password=:password",
				str,params.toArray());
		return users.size();
		
		//return result;
	}
}