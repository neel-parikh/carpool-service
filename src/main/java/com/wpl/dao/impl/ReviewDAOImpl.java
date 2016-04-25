package com.wpl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpl.DBConnection;
import com.wpl.HibernateConfig;
import com.wpl.dao.ReviewDAO;
import com.wpl.mapper.ReviewMapper;
import com.wpl.model.Review;

/*public class ReviewDAOImpl extends HibernateDaoSupport implements ReviewDAO {*/

@Service
@Transactional
public class ReviewDAOImpl implements ReviewDAO {

	@Autowired
	private DBConnection dbase;
	
	@Autowired
	private HibernateConfig template;
	
	@Override
	public void save(Review review) {
		String reviewId = "RW"+countReviews();
		//String SQL = "insert into REVIEW values()";
		review.setReviewId(reviewId);
		//dbase.getJdbcTemplateObject().update(SQL,review);
		template.getHibernateTemplate().save(review);
	}

	@Override
	public void update(Review review) {
		template.getHibernateTemplate().update(review);
		
	}

	@Override
	public void delete(Review review) {
		// TODO Auto-generated method stub
		
	}
	
	public Review findByExistingUserAndRide(String userId,String rideId) {
		String SQL = "select * from REVIEW where userId = '"+ userId +"' and rideId='"+ rideId +"'";
		List<Review> reviews = dbase.getJdbcTemplateObject().query(SQL,new ReviewMapper());
		if(reviews.size()>0)
			return reviews.get(0);
		else
			return null;
	}

	@Override
	public Review findByReviewId(String reviewId) {
		//TODO : IMPLEMENT WITH HIBERNATE
		String SQL = "select * from carpool.Review where Review_id = ?";
		List params = new ArrayList();
		params.add(reviewId);
		List<Review> reviews = dbase.getJdbcTemplateObject().query(SQL,params.toArray(),new ReviewMapper());
		return reviews.get(0);
	}
	
	private int countReviews()
	{
		String SQL = "select count(*) from REVIEW";
		return dbase.getJdbcTemplateObject().queryForObject(SQL, Integer.class)+1;
	}
}