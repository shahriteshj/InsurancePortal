package com.jp.insurance.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jp.insurance.entities.Customer;
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
	public Float getQuote(@RequestBody String input) throws InsuranceException, ParseException {
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
		customerVehicle.setManufacturingYear((Integer) inputMap.get("manufacturingYear"));
		
		Date regDate = new SimpleDateFormat("yyyy-MM-dd").parse((String) inputMap.get("registrationDate"));
		
		customerVehicle.setRegistrationDate(regDate);
		customerVehicle.setVehicleRegCity((String) inputMap.get("vehicleRegCity"));
		customerVehicle.setVehicleRegNo((String) inputMap.get("vehicleRegNo"));
		System.out.println(customerVehicle);
		Float price = policyQuoteService.getPolicyPremium(customerVehicle);
		return price;

	}

	@RequestMapping(value = "/savePolicy", method = RequestMethod.POST, headers = "Accept=application/json")
	public Long savePolicy(@RequestBody String input) throws InsuranceException, ParseException {
		System.out.println(input);
		CustomerVehicle customerVehicle = new CustomerVehicle();
		Policy policy = new Policy();
		Payment payment = new Payment();

		HashMap<String, Object> inputMap = (HashMap<String, Object>) JsonUtilsJackson.jsonToMap(input);

		// load customer details
		String custEmail = (String) inputMap.get("username");

		// load customerVehicle data
		customerVehicle.setCc((String) inputMap.get("cc"));
		customerVehicle.setMake((String) inputMap.get("make"));
		customerVehicle.setModel((String) inputMap.get("model"));
		customerVehicle.setSubmodel((String) inputMap.get("submodel"));
		customerVehicle.setChasisNo((String) inputMap.get("chasisNo"));
		customerVehicle.setEngineNo((String) inputMap.get("engineNo"));
		customerVehicle.setFuelType((String) inputMap.get("fuelType"));
		customerVehicle.setManufacturingYear((Integer) inputMap.get("manufacturingYear"));
		Date regDate = new SimpleDateFormat("yyyy-MM-dd").parse((String) inputMap.get("registrationDate"));
		customerVehicle.setRegistrationDate(regDate);
		customerVehicle.setVehicleRegCity((String) inputMap.get("vehicleRegCity"));
		customerVehicle.setVehicleRegNo((String) inputMap.get("vehicleRegNo"));
		System.out.println(customerVehicle);

		// load policy data
		// SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy
		// HH:mm:ss");
		// Date date = new Date();

		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		policy.setPolicyStartDate(c.getTime());
		c.add(Calendar.YEAR, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		policy.setPolicyEndDate(c.getTime());
		policy.setStatus("NEW");

		// load payment data

		payment.setCardNo((String) inputMap.get("cardNo"));
		payment.setNameOnCard((String) inputMap.get("nameOnCard")) ;
		payment.setCardExpiryDate ((String) inputMap.get("cardExpiryMonth") + "/" + (String) inputMap.get("cardExpiryYear"));

		payment.setPaymentDate(new Date());
		
		Double amount =(Double) inputMap.get("policyAmount");
		
		payment.setPolicyAmount(amount.floatValue());

		Policy newPolicy = policyService.addNewPolicy(custEmail, policy, customerVehicle, payment);

		return newPolicy.getPolicyId();

	}
	
	
	@RequestMapping(value = "/renewPolicy", method = RequestMethod.POST, headers = "Accept=application/json")
	public Long renewPolicy(@RequestBody String input) throws InsuranceException, ParseException {
		
		System.out.println(input);

		Policy policy = new Policy();
		Payment payment = new Payment();

		HashMap<String, Object> inputMap = (HashMap<String, Object>) JsonUtilsJackson.jsonToMap(input);

		// load customer details
		String custEmail = (String) inputMap.get("username");
		Integer intPolicyId = (Integer)inputMap.get("policyId");
		Long currentPolicyId = intPolicyId.longValue(); 
		
		//load new Policy data
		policy.setStatus("NEW");
		
		// load payment data
		payment.setCardExpiryDate((String) inputMap.get("cardExpiryMonth") + "/" + (String) inputMap.get("cardExpiryYear"));
		payment.setCardNo((String) inputMap.get("cardNo"));
		payment.setNameOnCard((String) inputMap.get("nameOnCard"));
		

		payment.setPaymentDate(new Date());
		
		Double amount =(Double) inputMap.get("policyAmount");
		
		payment.setPolicyAmount(amount.floatValue());

		Policy newPolicy = policyService.renewPolicy(custEmail, currentPolicyId,policy, payment);
		
		return newPolicy.getPolicyId();
	}

	@RequestMapping(value = "/getPolicyList", method = RequestMethod.POST, headers = "Accept=application/json")
	public List<Policy> getPolicyList(@RequestBody String input) throws InsuranceException {

		List<Policy> policyList = null;
		HashMap<String, Object> inputMap = (HashMap<String, Object>) JsonUtilsJackson.jsonToMap(input);

		// load user details
		String username = (String) inputMap.get("username");
		String role = (String) inputMap.get("role");

		if (role.equalsIgnoreCase("CUSTOMER")) {
			policyList = policyService.getPolicyList(username);
		} else if (role.equalsIgnoreCase("MANAGER") || role.equalsIgnoreCase("OPERATIONS")) {
			policyList = policyService.getPolicyList();
		}

		return policyList;
	}
	
	
	@RequestMapping(value = "/getRenewPolicyList", method = RequestMethod.POST, headers = "Accept=application/json")
	public List<Policy> getRenewPolicyList(@RequestBody String input) throws InsuranceException {

		List<Policy> policyList = null;
		List<Policy> renewPolicyList = new ArrayList<Policy>();
		HashMap<String, Object> inputMap = (HashMap<String, Object>) JsonUtilsJackson.jsonToMap(input);

		// load user details
		String username = (String) inputMap.get("username");
		

	
			policyList = policyService.getPolicyList(username);
			System.out.println(policyList);
			Date today = Calendar.getInstance().getTime();
			for( Policy policy:policyList){
				System.out.println(policy);
				if((policy.getStatus().equalsIgnoreCase("NEW")) && ( today.after(policy.getPolicyStartDate())) && (today.before(policy.getPolicyEndDate()))){
					renewPolicyList.add(policy);
				}
				
			}
			System.out.println(renewPolicyList);
		return renewPolicyList;
	}

	@RequestMapping(value = "/getCustomerVehicleDetails", method = RequestMethod.GET, headers = "Accept=application/json")
	public CustomerVehicle getCustomerVehicleDetails(@PathParam("policyId") Long policyId) throws InsuranceException {
		Policy policy = policyService.getPolicyDetails(policyId);
		return policyService.getCustomerVehicleDetails(policy.getCustomerId());
	}

	@RequestMapping(value = "/getPolicyPaymentDetails", method = RequestMethod.GET, headers = "Accept=application/json")
	public Payment getPolicyPaymentDetails(@PathParam("policyId") Long policyId) throws InsuranceException {
		return policyService.getPolicyPaymentDetails(policyId);
	}

	@RequestMapping(value = "/getCustomerDetails", method = RequestMethod.GET, headers = "Accept=application/json")
	public Customer getCustomerDetails(@PathParam("policyId") Long policyId) throws InsuranceException {
		Policy policy = policyService.getPolicyDetails(policyId);
		return policyService.getCustomerDetails(policy.getCustomerId());

	}
	
	@RequestMapping(value = "/getRenewPolicyQuote", method = RequestMethod.GET, headers = "Accept=application/json")
	public Float getRenewPolicyQuote(@PathParam("policyId") Long policyId) throws InsuranceException {
		System.out.println(policyId);
		Policy policy = policyService.getPolicyDetails(policyId);
		Float price=0f;
		
		if(policy!=null){
			CustomerVehicle customerVehicle = policyService.getCustomerVehicleDetailsByVehicleId(policy.getVehicleId());
			System.out.println(customerVehicle);
			 price = policyQuoteService.getPolicyPremium(customerVehicle);
		}
		return price;

	}

}
