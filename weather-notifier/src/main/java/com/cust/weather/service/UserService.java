package com.cust.weather.service;

import java.util.List;

import com.cust.weather.entity.Weather;

public interface UserService {
	
	
	public void activateUser(String userId);

    public void deactivateUser(String userId);

    public String getUser(String username);
    
    public List<Weather> getHistoryByUser(String user);
    
}
