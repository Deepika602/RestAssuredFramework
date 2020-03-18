package com.api.restapi.pojo;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.api.restapi.util.TestUtil;
import com.api.restclient.RestClient;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserResult {
	
	String baseURI="https://gorest.co.in";
	String basePath="public-api/users";
	String token="CPaucrl4oQlZFi-7oQ_BOlDXppDkYXLSfvFu";
	
	@Test
	public void createUserWithFullJSON() throws JsonProcessingException
	{
		Self sf = new Self("http://www.sf.com");
		Edit ed = new Edit("http://www.ed.com");
		Avatar av = new Avatar("http://www.av.com");
		
		Links links=new Links(sf, ed, av);
		
		UserJson user=new UserJson("Tom", "Peter", "male", "09-09-1998", "tom17@gmail.com", "89898899",
				"http://www.tom.com", "test address", "active", links);

		
		String json=TestUtil.getSerializedObject(user);
		Map<String, String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer " + token);
		
//		Response response=RestClient.doPost("JSON", "https://gorest.co.in", "/public-api/users", authTokenMap, null, true, ison);
//		System.out.println(response.getStatusCode());
//		System.out.println(response.prettyPrint());
		
		RestAssured.baseURI = "https://gorest.co.in";
		given().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer _FWTKt73f0EeVrfWj7d3sKoLMnw_9dqVcs0k")
		.body(json)
			.post("/public-api/users")
		.then()
			.assertThat()
				.contentType(ContentType.JSON);
				

		

		
		
		
	}

}
