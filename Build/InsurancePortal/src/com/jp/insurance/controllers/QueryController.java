package com.jp.insurance.controllers;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jp.insurance.entities.Query;
import com.jp.insurance.exceptions.InsuranceException;
import com.jp.insurance.services.interfaces.IQueryService;



/*http://localhost:8081/InsurancePortal/policy/homePage.hr*/
@Controller
public class QueryController {
	
	@Autowired
	@Qualifier("queryService")
	private IQueryService queryService;
	

	@Autowired
	private Validator validator;
	
	
	@InitBinder
	private void validatorBinder() {
		// This is Java Service Request JSR 303 validator
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();      
        System.out.println("Validator is set."+validator.hashCode());
	}

	
	@RequestMapping("showQueryStatus.qry")
	public String checkStatus() {	
	return "query/CheckQueryStatus";		
	
	}
	
	
	@RequestMapping("queryList.qry")
	public ModelAndView getQueryList(HttpSession session) {
		List<Query> queryList = null;
		System.out.println("In getQueryList()");
		ModelAndView mAndV = new ModelAndView();
		String emailId = (String) session.getAttribute("username");
		String role = (String) session.getAttribute("roles");
		  System.out.println(emailId);
		  System.out.println(role);
		  
		try {		
			
			switch(role) {
				case "OPERATIONS" :
					System.out.println("In OPERATIONS CASE");
					queryList = queryService.getQueryByRole(role);
					break;
				
				case "CUSTOMER" :
					System.out.println("In CUSTOMER CASE");
					queryList = queryService.getQueryByEmailId(emailId);
					break;
					
				case "MANAGER" :
					System.out.println("In MANAGER CASE");
					queryList = queryService.getQueryList();
					break;
			}
			
			System.out.println(queryList);
			mAndV.addObject("queryList",queryList);			
			mAndV.setViewName("query/QueryList");
       /*     session.removeAttribute("username");   
            session.removeAttribute("roles");   */
            //session.invalidate();

			
		} catch (InsuranceException e) {			
			e.printStackTrace();
		}
		return mAndV;		
	}	
	
	
	@RequestMapping("queryDetails.qry")
	public ModelAndView getQueryDetails(@RequestParam(value="queryId") Long queryId, Model model) {
		System.out.println("In getQueryDetails()");
		System.out.println(queryId);
		ModelAndView mAndV = new ModelAndView();
		
		try {			 
			Query query = queryService.getQueryById(queryId);		
			System.out.println(query);
			List<String> roleNameList = queryService.getRoleNameList();
			mAndV.addObject("roleNameList",roleNameList);
			mAndV.addObject("queryDetails",query);
			model.addAttribute("queryDetails", query);
			mAndV.setViewName("query/QueryDetails");
		}  catch(InsuranceException e) {
			e.printStackTrace();
		}
		return mAndV;
	}

	
/*	@RequestMapping("queryForm.qry")
	//@ModelAttribute("query")
	public String getQueryForm(Model model) {
			// Define Commond Object
		System.out.println("In getQueryForm");
		Query query = new Query();
		model.addAttribute("query",query);
		return "query/QueryPage";		
	}*/
	
	@RequestMapping("queryForm.qry")
	//@ModelAttribute("query")
	public String getQueryForm(HttpServletRequest request) {
			// Define Commond Object
		System.out.println("In getQueryForm");
		Query query = new Query();
		request.setAttribute("query", query);
		return "query/QueryPage";		
	}

	@RequestMapping("submitQueryForm.qry")
	public String submitQueryForm(@ModelAttribute("command") Query queryObj,  BindingResult result, Model model) {
		System.out.println(queryObj);		
		// We have apply Validation here		
		Set<ConstraintViolation<Query>> violations = validator.validate(queryObj);
		
		for (ConstraintViolation<Query> violation : violations)
        {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            // Add JSR-303 errors to BindingResult. This allows Spring to display them in view via a FieldError
            FieldError error = new FieldError("command",propertyPath,
                    "Invalid "+ propertyPath + "(" + message + ")");
            result.addError(error);
        }		
		
    	if (result.hasErrors()) {
    		model.addAttribute("msg", "Error in raising a Query.");    		
            return "query/QueryPage";
        } else {
        	try {
        		queryObj.setStatus("IN PROGRESS");
        		queryObj.setAssignedTo("OPERATIONS");
        		queryObj.setCreationDate(Calendar.getInstance().getTime());
        		queryService.addNewQuery(queryObj);        		
    			return "query/QuerySuccess";
    		} catch (InsuranceException e) {
    			model.addAttribute("msg", "Error in raising a Query." + e.getMessage());
    			return "query/QueryPage";
    		}
        }
		
	}
	
	
	@RequestMapping("updateQueryForm.qry")
	public String updateQueryForm(@ModelAttribute("queryDetails") Query queryObj, Model model) {
		System.out.println(queryObj);	

        	try {
	        	queryService.updateExistingQuery(queryObj);
	        	model.addAttribute("msg", "Query updated sucessfully!");
	        	return "redirect:queryList.qry";
	    		
    		} catch (InsuranceException e) {
    			model.addAttribute("msg", "Error in raising a Query." + e.getMessage());
    			return "query/QueryList";
    		}
        }

}
