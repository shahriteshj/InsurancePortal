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

@RestController
public class LoginController {

	@Autowired
	@Qualifier("userService")
	private IUserService userService;

	@RequestMapping(value = "/authenticateUser", method = RequestMethod.POST, headers = "Accept=application/json")
	public HashMap<String, Object> authenticateUser(@RequestBody String loginInput) {
		System.out.println("In authenticateUser().");
		System.out.println(loginInput);
		HashMap<String, Object> responseMap = new HashMap<String, Object>();
		try {

			HashMap<String, Object> inputMap = (HashMap<String, Object>) JsonUtilsJackson.jsonToMap(loginInput);
			String username = (String) inputMap.get("username");
			String password = (String) inputMap.get("password");

			User user = userService.authenticateUser(username, password);
			if (user != null) {
				//responseMap.put("responseText", user.getResponseText());
				responseMap.put("user", user);
			}

		} catch (InsuranceException e) {
			e.printStackTrace();
		}
		return responseMap;

	}

}
