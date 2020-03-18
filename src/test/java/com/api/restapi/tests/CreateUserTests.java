package com.api.restapi.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.path.json.JsonPath;
import io.restassured.response.*;

import com.api.restapi.pojo.User;
import com.api.restapi.util.ExcelUtil;
import com.api.restclient.RestClient;
import com.fasterxml.jackson.core.JsonProcessingException;

public class CreateUserTests {
	
	String baseURI="https://gorest.co.in";
	String basePath="public-api/users";
	String token="CPaucrl4oQlZFi-7oQ_BOlDXppDkYXLSfvFu";
	

	@DataProvider
	public Object[][] getdata() throws InvalidFormatException, IOException
	{
		Object data[][]=ExcelUtil.getData("userdata");
		return data;
	}
	
	
	@Test
	public void postUsersAPI() throws JsonProcessingException
	{
		Map<String, String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer " + token);
		
		User user=new User("raghu", "mothn", "male", "1978-01-07", "daksha@gmail.com", "551.323.2052 x8824", "http://www.naveenautomationlabs.com", "4996 cappy", "active");
		Response response=RestClient.doPOST("JSON", baseURI, basePath,authTokenMap,user,true,null);
		
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		
		JsonPath json=response.jsonPath();
	
	}
	
	@Description("create a user api test...verify create user from post call....")
	@Severity(SeverityLevel.BLOCKER)
	@Test(dataProvider ="getdata")
	public void postUsersDataProviderAPI(String firstname,String lastname,String gender,String dob,String email,String phonenumber,String website,String address,String status) throws JsonProcessingException
	{
		Map<String, String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer " + token);
		User user=new User(firstname,lastname,gender,dob,email,phonenumber,website,address,status);
		Response response=RestClient.doPOST("JSON", baseURI, basePath, authTokenMap,user,true,null);

		
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	
		System.out.println("*********************");
	
	}
	

}
