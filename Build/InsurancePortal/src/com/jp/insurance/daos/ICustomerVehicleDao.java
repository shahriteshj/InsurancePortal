package com.jp.insurance.daos;

import java.util.List;

import com.jp.insurance.entities.CustomerVehicle;
import com.jp.insurance.exceptions.InsuranceException;

public interface ICustomerVehicleDao {

	public List<CustomerVehicle> getCustomerVehicleList() throws InsuranceException;

	public CustomerVehicle addCustomerVehicle(CustomerVehicle customerVehicle) throws InsuranceException;

}
