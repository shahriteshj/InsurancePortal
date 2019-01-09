package com.jp.insurance.utilities;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtilsJackson {

	public static Map<String, Object> jsonToMap(String jsonString) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			ObjectMapper mapper = new ObjectMapper();
			map = mapper.readValue(jsonString, new TypeReference<Map<String, Object>>() {
			});

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

}
