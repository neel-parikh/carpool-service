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
		user.setUserId(rs.getString("user_id"));
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		user.setLastLogin(rs.getString("last_login"));
		user.setEmailId(rs.getString("email_id"));
		user.setBirthDate(rs.getString("birth_date"));
		user.setNoOfFailedAttempts(rs.getInt("login_attempts"));
		return user;
	}
	
}
