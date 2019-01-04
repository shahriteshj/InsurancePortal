package com.jp.insurance.controllers;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jp.insurance.entities.CustomerVehicle;
import com.jp.insurance.exceptions.InsuranceException;
import com.jp.insurance.services.interfaces.IPolicyQuoteService;
import com.jp.insurance.utilities.JsonUtilsJackson;

@RestController
public class PolicyRestController {
	
	@Autowired
	@Qualifier("policyQuoteService")
	private IPolicyQuoteService policyQuoteService;

	

	@RequestMapping(value = "/getQuote", method = RequestMethod.POST, headers = "Accept=application/json")
	public Float getQuote(@RequestBody String input) throws InsuranceException {
		System.out.println(input);
		CustomerVehicle customerVehicle = new CustomerVehicle();
		HashMap<String, Object> inputMap = (HashMap<String, Object>) JsonUtilsJackson.jsonToMap(input);
		
		customerVehicle.setCc((String) inputMap.get("cc"));
		customerVehicle.setMake((String) inputMap.get("make"));
		customerVehicle.setModel((String) inputMap.get("model"));
		customerVehicle.setSubmodel((String) inputMap.get("submodel"));
		customerVehicle.setChasisNo((String) inputMap.get("chasisNo"));
		customerVehicle.setEngineNo((String) inputMap.get("engineNo"));
		customerVehicle.setFuelType((String) inputMap.get("fuelType"));
		customerVehicle.setManufacturingYear((Integer)inputMap.get("manufacturingYear"));
		customerVehicle.setRegistrationDate(new Date((String)inputMap.get("registrationDate")));
		customerVehicle.setVehicleRegCity((String) inputMap.get("vehicleRegCity"));
		customerVehicle.setVehicleRegNo((String) inputMap.get("vehicleRegNo"));
		System.out.println(customerVehicle);
		Float price = policyQuoteService.getPolicyPremium(customerVehicle);
		return price;
		
	}
	
	/*@RequestMapping(value = "/getQuote", method = RequestMethod.POST, headers = "Accept=application/json")
	public Float getQuote(@RequestBody CustomerVehicle customerVehicle) throws InsuranceException {
		System.out.println(customerVehicle);
		//CustomerVehicle customerVehicle = new CustomerVehicle();
		Float price = policyQuoteService.getPolicyPremium(customerVehicle);
		return price;
		
	}*/
}
