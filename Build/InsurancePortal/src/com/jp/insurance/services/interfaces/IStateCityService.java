package com.jp.insurance.services.interfaces;

import java.util.List;

import com.jp.insurance.entities.Cities;
import com.jp.insurance.entities.States;
import com.jp.insurance.exceptions.InsuranceException;

public interface IStateCityService {

	public List<Cities> getCityList() throws InsuranceException;
	
	public List<States> getStateList() throws InsuranceException;
	
	public List<Cities> getCityListByStateName(String stateName) throws InsuranceException;
	
}
