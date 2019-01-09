package com.jp.insurance.services.interfaces;

import com.jp.insurance.entities.CustomerVehicle;
import com.jp.insurance.exceptions.InsuranceException;

public interface IPolicyQuoteService {

	public Float getPolicyPremium(CustomerVehicle customerVehicle) throws InsuranceException;

}
