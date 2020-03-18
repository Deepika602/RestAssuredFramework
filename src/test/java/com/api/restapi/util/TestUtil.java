package com.api.restapi.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {
	
	

	public static String getSerializedObject(Object obj) throws JsonProcessingException {
		ObjectMapper mapper=new ObjectMapper();
		String jsonBody=mapper.writeValueAsString(obj);
		return jsonBody;
		
	}

}
