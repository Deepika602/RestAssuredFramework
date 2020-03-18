package com.api.restclient;

import java.util.Map;

import org.mozilla.javascript.ast.SwitchCase;

import com.api.restapi.pojo.User;
import com.api.restapi.util.TestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.restassured.response.*;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RestClient {
	
	/**
	 * This method is used to call GET API
	 * 
	 * @param contentType
	 * @param baseURI
	 * @param basePath
	 * @param token
	 * @param paramsMap
	 * @param log
	 * @return this method is returning response from the GET call
	 */

	public static Response doGet(String contentType,String baseURI,String basePath,Map<String, String> token,Map<String,String> params,boolean log)
	{
		if(setBaseURI(baseURI))
		{
		RequestSpecification request=createRequest(contentType, token, params, log);
		Response response =getResponse("GET", request, basePath);
		return response;
	}
		return null;
	}

	/**
	 * This method is used to call POST API
	 * 
	 * @param contentType
	 * @param baseURI
	 * @param basePath
	 * @param token
	 * @param paramsMap
	 * @param log
	 * @param obj
	 * @return this method is returning response from the POST call
	 */

	@Step("post call with {0} , {1}, {2}, {3}, {4}")
	public static Response doPOST(String contentType,String baseURI,String basePath,Map<String, String> token,Object obj,boolean log,Map<String,String> params) throws JsonProcessingException
	{
		if(setBaseURI(baseURI))
		{
		RequestSpecification request=createRequest(contentType, token, params, log);
		addRequestPayLoad(request,obj);
		Response response =getResponse("POST", request, basePath);
		return response;
	}
		return null;
	}
	
	public static void addRequestPayLoad(RequestSpecification request,Object obj) throws JsonProcessingException
	{
		String jsonBody=TestUtil.getSerializedObject(obj);
	    request.body(jsonBody);
	}

	private static Boolean setBaseURI(String baseURI)
	{
		if(baseURI ==null || baseURI.isEmpty())
		{
			return false;
		}
		try
		{
		RestAssured.baseURI=baseURI;
		return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("enter correct path");
			return false;
		}
		
	}
	
	private static RequestSpecification createRequest(String contentType,Map<String, String> token,Map<String,String> paramsMap,boolean log)
	{
		RequestSpecification request;
		if(log)
		{
			request=RestAssured.given().log().all();
		}
		else
		{
			request=RestAssured.given();
		}
		
		if(token!=null)
		{
			request.header("Authorization","Bearer "+token);
		}
		if(!(paramsMap==null))
		{
			request.queryParams(paramsMap);
		}
		if(contentType!=null)
		{
		if(contentType.equalsIgnoreCase("JSON"))
		{
			request.contentType(ContentType.JSON);
		}
		else if(contentType.equalsIgnoreCase("XML"))
		{
			request.contentType(ContentType.XML);
		}
		else
		{request.contentType(ContentType.TEXT);
		}
		}
		return request;
		
	}
	
	private static Response getResponse(String httpMethod,RequestSpecification request,String basePath)
	{
		Response response=executeAPI(httpMethod, request, basePath);
		return response;
	}
	
	private static Response executeAPI(String httpMethod,RequestSpecification request,String basePath)
	{
	Response response=null;
	switch(httpMethod) {
	case "GET":
	    response=request.get(basePath);
	    break;
	case "POST":
	    response=request.post(basePath);
	    break;
	case "PUT":
	    response=request.put(basePath);   
	    break;
	case "DELETE":
		 response=request.delete(basePath);    
		 break;
	default :
		System.out.println("no method");
		break;
		 
	}
	return response;
	}

	

	
}
