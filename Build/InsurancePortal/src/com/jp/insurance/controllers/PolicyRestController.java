package com.jp.insurance.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jp.insurance.entities.CustomerVehicle;
import com.jp.insurance.entities.Payment;
import com.jp.insurance.entities.Policy;
import com.jp.insurance.exceptions.InsuranceException;
import com.jp.insurance.services.interfaces.IPolicyQuoteService;
import com.jp.insurance.services.interfaces.IPolicyService;
import com.jp.insurance.utilities.JsonUtilsJackson;

@RestController
public class PolicyRestController {
	
	@Autowired
	@Qualifier("policyQuoteService")
	private IPolicyQuoteService policyQuoteService;
	
	@Autowired
	@Qualifier("policyService")
	private IPolicyService policyService;

	

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
	
	
	@RequestMapping(value = "/savePolicy", method = RequestMethod.POST, headers = "Accept=application/json")
	public Long savePolicy(@RequestBody String input) throws InsuranceException {
		System.out.println(input);
		CustomerVehicle customerVehicle = new CustomerVehicle();
		Policy policy = new Policy();
		Payment payment = new Payment();
		
		HashMap<String, Object> inputMap = (HashMap<String, Object>) JsonUtilsJackson.jsonToMap(input);
		
		//load customer details
		String custEmail = (String) inputMap.get("username");
		
		//load customerVehicle data
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
		
		//load policy data
		//SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    //Date date = new Date();  
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		policy.setPolicyStartDate(c.getTime());
		c.add(Calendar.YEAR, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		policy.setPolicyEndDate(c.getTime());
		
		
		//load payment data
		payment.setCardExpirtDate((String) inputMap.get("cardExpirtDate"));
		payment.setCardNo((String) inputMap.get("cardNo"));
		payment.setNameOnCard((String) inputMap.get("nameOnCard"));
		payment.setPaymentDate(new Date());
		payment.setPolicyAmount((Float)inputMap.get("policyAmount"));
		
		Policy newPolicy =policyService.addNewPolicy(custEmail, policy, customerVehicle, payment);
		
		return newPolicy.getPolicyId();
		
	}
	
	@RequestMapping(value = "/getPolicyList", method = RequestMethod.POST, headers = "Accept=application/json")
	public List<Policy> getPolicyList(@RequestBody String input) throws InsuranceException{
			
		List<Policy> policyList=null;
		HashMap<String, Object> inputMap = (HashMap<String, Object>) JsonUtilsJackson.jsonToMap(input);
		
		//load user details
		String username = (String) inputMap.get("username");
		String role = (String)inputMap.get("role");
		
		if(role.equalsIgnoreCase("CUSTOMER")){
			policyList = policyService.getPolicyList(username);
		}else if(role.equalsIgnoreCase("MANAGER") || role.equalsIgnoreCase("OPERATIONS")){
			policyList = policyService.getPolicyList();
		}
		
		return policyList;
	}
	
	
}
