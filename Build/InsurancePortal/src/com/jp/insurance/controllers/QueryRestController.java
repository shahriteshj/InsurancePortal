package com.jp.insurance.controllers;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jp.insurance.entities.Query;
import com.jp.insurance.exceptions.InsuranceException;
import com.jp.insurance.services.interfaces.IQueryService;
import com.jp.insurance.utilities.JsonUtilsJackson;

@RestController
public class QueryRestController {

	@Autowired
	@Qualifier("queryService")
	private IQueryService queryService;

	@RequestMapping(value = "/getQueryList", method = RequestMethod.POST, headers = "Accept=application/json")
	public List<Query> getQueryList(@RequestBody String queryInput) {
		System.out.println("In getQueryList() method");
		System.out.println(queryInput);
		List<Query> queryList = null;
		HashMap<String, Object> inputMap = (HashMap<String, Object>) JsonUtilsJackson.jsonToMap(queryInput);
		String emailId = (String) inputMap.get("username");
		String role = (String) inputMap.get("roles");
		System.out.println(emailId);
		System.out.println(role);
		try {
			switch (role) {
			case "OPERATIONS":
				System.out.println("In OPERATIONS CASE");
				queryList = (List<Query>) queryService.getQueryByRole(role);
				break;

			case "CUSTOMER":
				System.out.println("In CUSTOMER CASE");
				queryList = (List<Query>) queryService.getQueryByEmailId(emailId);
				break;

			case "MANAGER":
				System.out.println("In MANAGER CASE");
				queryList = (List<Query>) queryService.getQueryList();
				break;
			}
		} catch (InsuranceException e) {
			e.printStackTrace();
		}

		return queryList;

	}

	@RequestMapping(value = "/submitQuery", method = RequestMethod.POST, headers = "Accept=application/json")
	public Query submitQueryForm(@RequestBody String input) {
		System.out.println(input);
		Query query = new Query();
		try {
			HashMap<String, Object> inputMap = (HashMap<String, Object>) JsonUtilsJackson.jsonToMap(input);
			query.setQueryType((String) inputMap.get("queryType"));
			query.setEmailId((String) inputMap.get("emailId"));
			query.setQueryDescription((String) inputMap.get("queryDescription"));
			query.setStatus("IN PROGRESS");
			query.setAssignedTo("OPERATIONS");
			query.setCreationDate(Calendar.getInstance().getTime());
			System.out.println("Query : " + query);
			queryService.addNewQuery(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return query;
	}

	@RequestMapping(value = "/getQueryDetails", method = RequestMethod.GET, headers = "Accept=application/json")
	public Query getQueryDetails(@PathParam("queryId") Long queryId) throws InsuranceException {
		System.err.println("In Query Details controllers " + queryId);
		Query query = queryService.getQueryById(queryId);
		System.out.println(query);
		return query;
	}

	@RequestMapping(value = "/updateQuery", method = RequestMethod.POST, headers = "Accept=application/json")
	public String updateQueryForm(@RequestBody String input) throws InsuranceException {
		System.out.println("In Controller update query " + input);
		Query query = new Query();
		// JSONObject jsonObj = null;

		HashMap<String, Object> inputMap = (HashMap<String, Object>) JsonUtilsJackson.jsonToMap(input);
		Long lobj = new Long((Integer) inputMap.get("queryId"));
		query = queryService.getQueryById(lobj);

		if (query != null) {
			// query.setQueryId(lobj);
			query.setAssignedTo((String) inputMap.get("assignedTo"));
			// query.setQueryDescription((String)
			// inputMap.get("queryDescription"));
			// query.setEmailId((String) inputMap.get("emailId"));
			query.setQueryResponse((String) inputMap.get("queryResponse"));
			// query.setQueryType((String) inputMap.get("queryType"));
			query.setStatus((String) inputMap.get("status"));

			queryService.updateExistingQuery(query);
			/*
			 * jsonObj = new JSONObject("{\"response\":\"Success\"}");
			 * System.out.println(jsonObj); return jsonObj;
			 */
			return "{\"response\":\"Success\"}";
		} else {
			/*
			 * jsonObj = new JSONObject("{\"response\":\"Failure\"}");
			 * System.out.println(jsonObj); return jsonObj;
			 */ return "{\"response\":\"Failure\"}";

		}
	}

}
