package com.api.restapi.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.api.restclient.RestClient;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;

public class GetUserTests {
	
	String baseURI="https://gorest.co.in";
	String basePath="public-api/users";
	String token="CPaucrl4oQlZFi-7oQ_BOlDXppDkYXLSfvFu";
	
	@Description("get all user lists api test...verify all users list from get call....")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 1)
	public void getUsersAllAPI()
	{
		Map<String, String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer " + token);
		Response response=RestClient.doGet("JSON", baseURI, basePath, authTokenMap, null, true);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
	
	@Description("get all user lists api test with query partams...verify all users list from get call....")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void getUsersWithParamAPI()
	{
		Map<String, String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer " + token);
		Map<String,String> paramsMap=new HashMap<String, String>();
		paramsMap.put("first_name", "john");
		paramsMap.put("gender", "male");
		Response response=RestClient.doGet("JSON", baseURI, basePath, authTokenMap, paramsMap, true);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
	

}
