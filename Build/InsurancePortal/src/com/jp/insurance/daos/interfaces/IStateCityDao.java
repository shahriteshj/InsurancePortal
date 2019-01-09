package com.jp.insurance.daos.interfaces;

import java.util.List;

import com.jp.insurance.entities.Cities;
import com.jp.insurance.entities.States;
import com.jp.insurance.exceptions.InsuranceException;

public interface IStateCityDao {

	public List<String> getCityList() throws InsuranceException;

	public List<String> getStateList() throws InsuranceException;

	public List<String> getCityListbyStateName(String stateName) throws InsuranceException;

	public States getStateId(String stateName) throws InsuranceException;

	public List<Cities> getCityListByStateId(Integer stateId) throws InsuranceException;

}
