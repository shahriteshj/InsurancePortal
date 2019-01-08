package com.jp.insurance.services.interfaces;

import java.util.List;

import com.jp.insurance.entities.Cities;
import com.jp.insurance.exceptions.InsuranceException;

public interface IStateCityService {

	public List<String> getCityList() throws InsuranceException;
	
	public List<String> getStateList() throws InsuranceException;
	
	public List<String> getCityListbyStateName(String stateName) throws InsuranceException;
	
	public List<Cities> getCityListByStateName(String stateName) throws InsuranceException;
	
}
