package com.wpl.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.wpl.model.Review;
import com.wpl.model.User;

@Component
public class ReviewMapper implements RowMapper<Review>
{

	@Override
	public Review mapRow(ResultSet rs, int arg1) throws SQLException 
	{
		Review review = new Review();
		//User user = new User();
		//review.setUser(user);
		review.setReviewId(rs.getString("reviewId"));
		review.setStars(rs.getInt("stars"));
		return review;
	}
	
}
