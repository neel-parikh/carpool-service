package com.wpl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Service;

import com.wpl.DBConnection;
import com.wpl.HibernateConfig;
import com.wpl.dao.UserDAO;
import com.wpl.model.User;

@Service
@Transactional
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
/*		System.out.println(user.getUserId());
		user = findByUserId(user.getUserId());
		System.out.println(user.getUserId() + user.getEmailId() + user.getPassword());
		template.getHibernateTemplate().update(user);*/
		String SQL = "update user set firstName=?,lastName=?,emailId=?,phoneNo=? where userId=?";
		List params = new ArrayList<>();
		params.add(user.getFirstName());
		params.add(user.getLastName());
		params.add(user.getEmailId());
		params.add(user.getPhoneNo());
		params.add(user.getUserId());
		
		dbase.getJdbcTemplateObject().update(SQL, params.toArray());
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
		if(users.size()>0)
			return (User) users.get(0);
		else
			return null;
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

	@Override
	public int getIncorrectAttempts(String userId) {
		return DataAccessUtils.intResult(template.getHibernateTemplate().findByNamedParam
				("select loginAttempts from User where userId=:userId","userId",userId));
	}

	@Override
	public void updateIncorrectAttempts(String userId) {
		User user = findByUserId(userId);
		user.setLoginAttempts(getIncorrectAttempts(userId)+1);
		template.getHibernateTemplate().update(user);
	}
}