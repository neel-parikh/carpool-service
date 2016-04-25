package com.wpl.dao;



import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface SearchDAO {
  public List search_Ride(String rdate);
  public List show_Ride(String userId);
}