package com.wpl.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.wpl.model.Review;

@Component
public class ReviewMapper implements RowMapper<Review>
{

	@Override
	public Review mapRow(ResultSet rs, int arg1) throws SQLException 
	{
		Review review = new Review();
		review.setReviewId(rs.getString("review_id"));
		review.setComments(rs.getString("comments"));
		review.setStars(rs.getInt("stars"));
		return review;
	}
	
}
