package com.qa.test;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.Testbase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostApiTest extends Testbase {

	Testbase testbase;
	 
	 String baseurl;
	 String endpoint;
	 String FullUrl_Str;
	 RestClient restclient;
	 CloseableHttpResponse CloseableHttpResponse_obj;
	
	@BeforeMethod
	
	public void setup() throws ClientProtocolException, IOException
	{
		testbase =new Testbase();
		 baseurl=prop.getProperty("URL");
		 endpoint=prop.getProperty("ServiceURL");
		 FullUrl_Str=baseurl+endpoint;			
	}

	
	@Test

	public void postApiTest() throws JsonGenerationException, JsonMappingException, IOException
	{
		restclient=new RestClient();
		HashMap <String,String> headerMap= new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		// jackson api is needed to convert the User.java file to Json type to  send post request
		ObjectMapper mapper = new ObjectMapper();
		Users user = new Users("morpheus", "leader");
		
		//object to json converter
		 
		mapper.writeValue(new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\data\\users.json"),user);
		
		//java object to json in String:
				String usersJsonString = mapper.writeValueAsString(user);
				System.out.println(usersJsonString);
				
				CloseableHttpResponse_obj = restclient.post(FullUrl_Str, usersJsonString, headerMap); //call the API
				
				//validate response from API:
				//1. status code:
				int statusCode = CloseableHttpResponse_obj.getStatusLine().getStatusCode();
				Assert.assertEquals(statusCode, Testbase.RESPONCE_STATUS_CODE_201);
				
				//2. JsonString:
				String responseString = EntityUtils.toString(CloseableHttpResponse_obj.getEntity(), "UTF-8");
				
				JSONObject responseJson = new JSONObject(responseString);
				System.out.println("The response from API is:"+ responseJson);
				
				//json to java object:
				Users usersResObj = mapper.readValue(responseString, Users.class); //actual users object
				System.out.println(usersResObj);
				
				Assert.assertTrue(user.getName().equals(usersResObj.getName()));
				
				Assert.assertTrue(user.getJob().equals(usersResObj.getJob()));
	
	}
}


