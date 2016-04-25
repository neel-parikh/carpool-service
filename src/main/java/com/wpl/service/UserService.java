package com.wpl.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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
	public boolean checkUser(@RequestBody User user,HttpServletRequest req)
	{
		User userCheck = userDAO.findByUserId(user.getUserId());
		if(userCheck!=null)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String lastLogin = sdf.format(new Date());
			userCheck.setLastLogin(lastLogin);
			int result = userDAO.checkUserInDB(user.getUserId(), user.getPassword());
			System.out.println("Service called");
			if(result==0)
			{
				//userDAO.updateIncorrectAttempts(user.getUserId());
				userCheck.setLoginAttempts(userCheck.getLoginAttempts()+1);
				userDAO.update(userCheck);
				return false;
			}
			else { 
				System.out.println(userCheck.getUserId() + " >>> " + userCheck.getLastLogin());
				userDAO.update(userCheck);
				return true;
			}
		}
		else
		{
			return false;
		}
	}
	
	@RequestMapping(value="/createUser",method=RequestMethod.POST)
	public Boolean createUser(@RequestBody User user)
	{
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		//String lastLogin = sdf.format(new Date());
		System.out.println("web server2");
		userDAO.save(user);
		return true;
	}
	
	@RequestMapping(value="/getUser",method=RequestMethod.GET)
	public User getUser(@RequestParam("userId") String userId) {
		User user = userDAO.findByUserId(userId);
		//user.setPassword(null);
		return user;
	}
	
	@RequestMapping(value="/updateUserProfile",method=RequestMethod.POST)
	public boolean updateProfile(@RequestBody User user)
	{
		User puser = userDAO.findByUserId(user.getUserId());
		puser.setEmailId(user.getEmailId());
		puser.setFirstName(user.getFirstName());
		puser.setLastName(user.getLastName());
		puser.setPhoneNo(user.getPhoneNo());
		System.out.println(user.getUserId() + user.getEmailId() + user.getPassword());
		
		userDAO.update(puser);
		System.out.println("Called service");
		return true;
	}
}