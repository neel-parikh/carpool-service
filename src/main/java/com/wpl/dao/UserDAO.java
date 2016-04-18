package com.wpl.dao;

import org.springframework.stereotype.Component;

import com.wpl.model.User;

@Component
public interface UserDAO {
	
	void save(User user);
	void update(User user);
	void delete(User user);
	User findByUserId(String userId);
	int checkUserInDB(String userId,String password);
	int getIncorrectAttempts(String userId);
	void updateIncorrectAttempts(String userId);
}
