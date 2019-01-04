package com.jp.insurance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jp.insurance.entities.CustomerVehicle;
import com.jp.insurance.exceptions.InsuranceException;
import com.jp.insurance.services.interfaces.IPolicyQuoteService;

@RestController
public class PolicyRestController {
	
	@Autowired
	@Qualifier("policyQuoteService")
	private IPolicyQuoteService policyQuoteService;

	
	@RequestMapping(value = "/getQuote", method = RequestMethod.POST, headers = "Accept=application/json")
	public Float getQuote(@RequestBody String Input) throws InsuranceException {
		
		CustomerVehicle customerVehicle = new CustomerVehicle();
		Float price = policyQuoteService.getPolicyPremium(customerVehicle);
		return price;
		
	}
}
