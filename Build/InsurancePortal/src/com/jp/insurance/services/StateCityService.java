package com.jp.insurance.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jp.insurance.daos.StateCityDao;
import com.jp.insurance.daos.interfaces.IStateCityDao;
import com.jp.insurance.entities.Cities;
import com.jp.insurance.entities.States;
import com.jp.insurance.exceptions.InsuranceException;
import com.jp.insurance.services.interfaces.IStateCityService;

@Service("stateCityService")
public class StateCityService implements IStateCityService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3902353929663663522L;

	private IStateCityDao stateCityDao;;

	public StateCityService() throws InsuranceException {

	}

	@Autowired
	public StateCityService(@Qualifier("stateCityDao") StateCityDao stateCityDao) throws InsuranceException {
		this.stateCityDao = stateCityDao;
	}

	@Override
	public List<String> getCityList() throws InsuranceException {
		return stateCityDao.getCityList();
	}

	@Override
	public List<String> getStateList() throws InsuranceException {
		return stateCityDao.getStateList();
	}

	@Override
	public List<Cities> getCityListByStateName(String stateName) throws InsuranceException {
		List<Cities> cityList = null;
		States state = stateCityDao.getStateId(stateName.toUpperCase());
		if (state != null) {
			cityList = stateCityDao.getCityListByStateId(state.getId());
		}
		return cityList;
	}

	@Override
	public List<String> getCityListbyStateName(String stateName) throws InsuranceException {
		return stateCityDao.getCityListbyStateName(stateName);
	}

}
