package com.jp.insurance.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jp.insurance.entities.VehicleMaster;
import com.jp.insurance.exceptions.InsuranceException;
import com.jp.insurance.services.interfaces.IPolicyService;
import com.jp.insurance.services.interfaces.IStateCityService;
import com.jp.insurance.services.interfaces.IVehicleMasterService;

@Controller
public class PolicyController {

	@Autowired
	@Qualifier("policyService")
	private IPolicyService policyService;

	@Autowired
	@Qualifier("stateCityService")
	private IStateCityService stateCityService;

	@Autowired
	@Qualifier("vehicleMasterService")
	private IVehicleMasterService vehicleMasterService;

	@RequestMapping("registerVehicleDetails.pol")
	public String registerVehicleDetails(Model model) {
		System.out.println("in registerVehicleDetails()");
		try {
			List<VehicleMaster> vehicleMasterList = vehicleMasterService.getVehiclesList();
			model.addAttribute(vehicleMasterList);
		} catch (InsuranceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "policy/RegisterVehicleDetails";

	}

}
