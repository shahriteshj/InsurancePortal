package com.jp.insurance.services;

import com.jp.insurance.entities.CustomerVehicle;
import com.jp.insurance.exceptions.InsuranceException;

public interface IPolicyQuoteService {
	
	public Float getPolicyPremium(CustomerVehicle customerVehicle) throws InsuranceException;

	
}
