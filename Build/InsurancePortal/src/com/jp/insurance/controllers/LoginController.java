package com.jp.insurance.controllers;

import java.util.Collections;
import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	
	@RequestMapping(value = "/authenticateUser", method = RequestMethod.POST, headers = "Accept=application/json")
	public HashMap<String, String> authenticateUser(@RequestBody String user) {
		System.out.println("In authenticateUser().");
		System.out.println(user);
		
		Collections.singletonMap("response", "your string value");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("name", "ritesh");
		
		return map;
		//return "{name:Ritesh,username:ritesh,password:ritesh,id:1}";
	}

	
}
