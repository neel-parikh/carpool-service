package com.wpl.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wpl.dao.UserDAO;
import com.wpl.model.User;

@RestController
@RequestMapping("/user")
public class UserService 
{
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(value="/checkUser",method=RequestMethod.POST)
	public boolean checkUser(@RequestBody User user)
	{
		User userCheck = userDAO.findByUserId(user.getUserId());
		if(userCheck!=null)
		{
			String lastLogin = new Date().toString();
			user.setLastLogin(lastLogin);
			int result = userDAO.checkUserInDB(user.getUserId(), user.getPassword());
			System.out.println("Service called");
			if(result==0)
			{
				userDAO.updateIncorrectAttempts(user.getUserId());
				return false;
			}
			else 
				return true;
		}
		else
		{
			return false;
		}
	}
	
	@RequestMapping(value="/createUser")
	public void createUser(@RequestParam("userId") String userId,
			@RequestParam("password") String password,
			@RequestParam("emailId") String emailId,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("phoneNo") String phoneNo)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		String lastLogin = sdf.format(new Date());
		
		User user = new User();
		user.setUserId(userId);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastLogin(lastLogin);
		user.setLastName(lastName);
		user.setEmailId(emailId);
		user.setPhoneNo(phoneNo);
		userDAO.save(user);
	}
	
	@RequestMapping(value="/getUser",method=RequestMethod.GET)
	public User getUser(@RequestParam("userId") String userId) {
		User user = userDAO.findByUserId(userId);
		user.setPassword(null);
		return user;
	}
}