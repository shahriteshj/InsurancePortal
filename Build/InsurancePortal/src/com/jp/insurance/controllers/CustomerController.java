package com.jp.insurance.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerController {
	
	
	/*
	@RequestMapping("homePage.po")
	public String homePage() {
		System.out.println("In Home Page");
		return "customer/Menubar";
	}*/

	@RequestMapping("homePage.po")
	public String homePage(HttpSession session) {
		
		String role = "CUSTOMER";
		String emailId = "darshan.chaudhari10@gmail.com";
		session.setAttribute("role", role);
		session.setAttribute("username", emailId);
		
		System.out.println("In Home Page");
		return "customer/Menubar";
	}
	
}
