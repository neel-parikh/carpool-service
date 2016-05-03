package com.wpl.service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wpl.dao.UserDAO;
import com.wpl.model.User;

import net.spy.memcached.MemcachedClient;

@RestController
@RequestMapping("/user")
public class UserService 
{
	@Autowired
	private UserDAO userDAO;
	
	MemcachedClient client;
	
	@RequestMapping(value="/checkUser",method=RequestMethod.POST)
	public boolean checkUser(@RequestBody User user,HttpServletRequest req)
	{
		try{
			client = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
			if(client.get("userId") != null && client.get("password") != null && client.get("userId").equals(user.getUserId()) && client.get("password").equals(user.getPassword())) {
				System.out.println("CACHE HIT");
				return true;
			}
			else{
				System.out.println("CACHE MISS");
				User userCheck = userDAO.findByUserId(user.getUserId());
				if(userCheck!=null)
				{
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
						client.set("userId", 3600, user.getUserId());
						client.set("password", 3600, user.getPassword());
						return true;
					}
				}
				else
				{
					return false;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
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
	
	@RequestMapping(value="/setLastLoginTime")
	public void setLastLoginTime(@RequestParam("userId") String userId,@RequestParam("lastLogin") String lastLogin) {
		User user = this.getUser(userId);
		user.setLastLogin(lastLogin);
		System.out.println(user.getEmailId() + "    " + user.getUserId() + "   " + user.getFirstName());
		userDAO.update(user);
	}
}