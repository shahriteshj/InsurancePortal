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
		String emailId = "smith.john@gmail.com";
		session.setAttribute("roles", role);
		session.setAttribute("username", emailId);
		
		System.out.println("In Home Page");
		return "customer/Menubar";
	}
	
	@RequestMapping("logout.po")
	public String logout(HttpSession session) {
		System.out.println("In Logout");
		session.removeAttribute("roles");
		session.removeAttribute("username");
		session.invalidate();
		
		return "query/Logout";
	}
}
