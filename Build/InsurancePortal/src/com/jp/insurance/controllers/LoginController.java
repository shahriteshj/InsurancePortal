package com.jp.insurance.controllers;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jp.insurance.entities.Customer;
import com.jp.insurance.entities.User;
import com.jp.insurance.exceptions.InsuranceException;
import com.jp.insurance.services.interfaces.IUserService;
import com.jp.insurance.utilities.JsonUtilsJackson;
import com.jp.insurance.view.entities.ViewUser;

@RestController
public class LoginController {

	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	



	@RequestMapping(value = "/authenticateUser", method = RequestMethod.POST, headers = "Accept=application/json")
	public ViewUser authenticateUser(@RequestBody String loginInput) {
		System.out.println("In authenticateUser().");
		System.out.println(loginInput);
		ViewUser viewUser = new ViewUser();
		try {

			HashMap<String, Object> inputMap = (HashMap<String, Object>) JsonUtilsJackson.jsonToMap(loginInput);
			String username = ((String) inputMap.get("username")).toUpperCase();
			String password = (String) inputMap.get("password");
			
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
	public String registerUser(@RequestBody String input) throws InsuranceException {
		
		System.out.println("In registerUser().");
		System.out.println(input);
				
		HashMap<String, Object> inputMap = (HashMap<String, Object>) JsonUtilsJackson.jsonToMap(input);
	
		User user = new User();
		Customer customer = new Customer();
		
		
		user.setUsername(((String)inputMap.get("username")).toUpperCase());
		user.setPassword((String)inputMap.get("password"));
		user.setAccountLocked('N');
		user.setSecurityAnswer((String)inputMap.get("securityAnswer"));
		user.setQuestionId((Integer)inputMap.get("questionId"));
		user.setRoleId((Integer)inputMap.get("roleId"));
		
		user.setCreationDate(new Date());
		user.setFailedLoginAttempt(0);
		
		customer.setEmailId((String)inputMap.get("username"));
		customer.setFirstName((String)inputMap.get("firstName"));
		customer.setLastName((String)inputMap.get("lastName"));
		customer.setCity((String)inputMap.get("city"));
		customer.setState((String)inputMap.get("state"));
		customer.setPincode((Integer)inputMap.get("pincode"));
		customer.setGender((String)inputMap.get("gender"));
		customer.setDOB((Date)inputMap.get("dob"));
		customer.setAddress1((String)inputMap.get("address1"));
		customer.setAddress2((String)inputMap.get("address2"));
		customer.setAddress3((String)inputMap.get("address3"));
		
		user = userService.addUser(user,customer);
		
		if(user.getUserId()>0){
			return "Success";
		}else{
			return "Failure";
		}
		
		
	}
	

}
