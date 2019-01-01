package com.jp.insurance.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerController {
	
	
	
	@RequestMapping("homePage.po")
	public String homePage() {
		System.out.println("In Home Page");
		return "customer/Menubar";
	}

}
