package com.wpl.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.wpl.model.User;

@Component
public class UserMapper implements RowMapper<User>
{

	@Override
	public User mapRow(ResultSet rs, int arg1) throws SQLException 
	{
		User user = new User();
		user.setUserId(rs.getString("userId"));
		user.setFirstName(rs.getString("firstName"));
		user.setLastName(rs.getString("lastName"));
		user.setLastLogin(rs.getString("lastLogin"));
		user.setEmailId(rs.getString("emailId"));
		user.setPhoneNo(rs.getString("phoneNo"));
		user.setLoginAttempts(rs.getInt("loginAttempts"));
		return user;
	}
	
}
