package com.jp.insurance.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jp.insurance.entities.VehicleMaster;
import com.jp.insurance.exceptions.InsuranceException;
import com.jp.insurance.services.interfaces.IVehicleMasterService;

@RestController
public class VehicleMasterRestController {
	
	@Autowired
	@Qualifier("vehicleMasterService")
	private IVehicleMasterService vehicleMasterService;
	

	@RequestMapping(value = "/getAllVehicles", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<VehicleMaster> getAllVehicles() throws InsuranceException {
		return vehicleMasterService.getVehiclesList();
	}
	
	@RequestMapping(value = "/getVehicleMake", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<String> getVehicleMake() throws InsuranceException {
		return vehicleMasterService.getVehiclesMake();
	}


}
