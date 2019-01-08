package com.jp.insurance.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
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
import com.jp.insurance.entities.Role;
import com.jp.insurance.entities.User;
import com.jp.insurance.exceptions.InsuranceException;
import com.jp.insurance.services.interfaces.IStateCityService;
import com.jp.insurance.services.interfaces.IUserService;
import com.jp.insurance.utilities.JsonUtilsJackson;
import com.jp.insurance.view.entities.ViewUser;

@RestController
public class LoginController {

	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	

	@Autowired
	@Qualifier("stateCityService")
	private IStateCityService stateCityService;

	@RequestMapping(value = "/authenticateUser", method = RequestMethod.POST, headers = "Accept=application/json")
	public ViewUser authenticateUser(@RequestBody String loginInput) {
		System.out.println("In authenticateUser().");
		System.out.println(loginInput);
		ViewUser viewUser = new ViewUser();
		try {

			HashMap<String, Object> inputMap = (HashMap<String, Object>) JsonUtilsJackson.jsonToMap(loginInput);
			String username = ((String) inputMap.get("username")).toUpperCase();
			String password = (String) inputMap.get("password");
			System.out.println(username + " " + password);
			User user = userService.authenticateUser(username, password);
			if (user != null) {
				String role = userService.getRoleById(user.getRoleId());
				Customer customer = userService.getCustomerByEmailId(username.toUpperCase());
				
				viewUser.setName(customer.getFirstName() + " " + customer.getLastName());
				viewUser.setUserId(user.getUserId());
				viewUser.setUsername(user.getUsername());
				viewUser.setPassword("");
				viewUser.setRoleName(role);
				viewUser.setLastSuccessfulLoginDate(user.getLastSuccessfulLoginDate());
				viewUser.setResponseText(user.getResponseText());
				viewUser.setSecurityAnswer(user.getSecurityAnswer());
				System.out.println(viewUser);
				
			}

		} catch (InsuranceException e) {
			e.printStackTrace();
		}
		return viewUser;

	}
	
	
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST, headers = "Accept=application/json")
	public String registerUser(@RequestBody String input) throws InsuranceException, ParseException {
		
		System.out.println("In registerUser().");
		System.out.println(input);
				
		HashMap<String, Object> inputMap = (HashMap<String, Object>) JsonUtilsJackson.jsonToMap(input);
	
		User user = new User();
		Customer customer = new Customer();
		
		
		user.setUsername(((String)inputMap.get("username")).toUpperCase());
		user.setPassword((String)inputMap.get("password"));
		user.setAccountLocked('N');
		user.setSecurityAnswer((String)inputMap.get("securityAnswer"));
		Integer id =Integer.parseInt((String)inputMap.get("questionId"));
		
		user.setQuestionId(id);
		String roleName = (String)inputMap.get("roleName");
		Role role = userService.getRoleByName(roleName);
		user.setRoleId(role.getRoleId());
		
		user.setCreationDate(new Date());
		user.setFailedLoginAttempt(0);
		
		customer.setEmailId(((String)inputMap.get("username")).toUpperCase());
		customer.setFirstName((String)inputMap.get("firstName"));
		customer.setLastName((String)inputMap.get("lastName"));
		customer.setCity((String)inputMap.get("city"));
		customer.setState((String)inputMap.get("state"));
		id =Integer.parseInt((String)inputMap.get("pincode"));
		System.out.println(id);
		customer.setPincode(id);
		customer.setGender((String)inputMap.get("gender"));
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse((String)inputMap.get("DOB"));
		System.out.println(date1);
		customer.setDOB(date1);
		customer.setAddress1((String)inputMap.get("address1"));
		customer.setAddress2((String)inputMap.get("address2"));
		customer.setAddress3((String)inputMap.get("address3"));
		customer.setMobileNo((String)inputMap.get("mobileNo"));
		
		user = userService.addUser(user,customer);
		
		if(user.getUserId()>0){
//			return "SUCCESS";
			return "{\"response\":\"SUCCESS\"}";
		}else{
//			return "FAILURE";
			return "{\"response\":\"FAILURE\"}";
		}
		
		
	}
	
	
	@RequestMapping(value = "/stateList", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<String> getStateList() throws InsuranceException {
		return stateCityService.getStateList();
		
	}
	
	
	@RequestMapping(value = "/cityList", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<String> getCityList(@PathParam("stateName") String stateName) throws InsuranceException {
		List<String> cityList = stateCityService.getCityListbyStateName(stateName);
		Collections.sort(cityList);
		
		return cityList;
		
	}
	
}
