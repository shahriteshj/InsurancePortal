package com.jp.insurance.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	

	@RequestMapping(value = "/authenticateUserTest", method = RequestMethod.POST, headers = "Accept=application/json")
	public String authenticateUserTest(@RequestBody String loginInput) {
		System.out.println("In authenticateUserTest().");
		return "Success";
	}

	@RequestMapping(value = "/authenticateUser", method = RequestMethod.POST, headers = "Accept=application/json")
	public ViewUser authenticateUser(@RequestBody String loginInput) {
		System.out.println("In authenticateUser().");
		System.out.println(loginInput);
		ViewUser viewUser = new ViewUser();
		try {

			HashMap<String, Object> inputMap = (HashMap<String, Object>) JsonUtilsJackson.jsonToMap(loginInput);
			String username = (String) inputMap.get("username");
			String password = (String) inputMap.get("password");

			User user = userService.authenticateUser(username, password);
			if (user != null) {
				String role = userService.getRoleById(user.getRoleId());
				
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

}
