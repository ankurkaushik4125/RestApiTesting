package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.base.Testbase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends Testbase{
	
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
	
	 
		@Test(priority = 1)
	public void getApiTest() throws ClientProtocolException, IOException
	{
			restclient=new RestClient();
			CloseableHttpResponse_obj=	restclient.get(FullUrl_Str);
			

			   //to store and validate the status code by assertion
			   int status_code=CloseableHttpResponse_obj.getStatusLine().getStatusCode();
			   
			   System.out.println("status code  -->"+status_code);
			   
			 
			   Assert.assertEquals(status_code, RESPONCE_STATUS_CODE_200,"Status code isn't 200");
			   //Hold Response from api into a string with UTF-8 format
			   
			   String  reponse_string = EntityUtils.toString(CloseableHttpResponse_obj.getEntity(),"UTF-8");
			   
			   JSONObject json_response= new JSONObject(reponse_string);
			   
			   System.out.println("json_response FROM API   -->"+json_response);
			   // calling the utility from json object at root level and doing comparison
			   
			   String per_page_str =TestUtil.getValueByJPath(json_response, "/per_page");			   
			   System.out.println("Value of per page " +per_page_str);
			   Assert.assertEquals(per_page_str, "6","value not match");
				 
			   String total_str =TestUtil.getValueByJPath(json_response, "/total");			   
			   System.out.println("Value of total " +total_str);
			   Assert.assertEquals(total_str, "12","value not match");
			   
			   
			   // get the value from JSON array
			   
			   String total_pages_str =TestUtil.getValueByJPath(json_response, "/total_pages");			   
			   System.out.println("Value of total pages" +total_pages_str);
			   Assert.assertEquals(total_pages_str, "2","value not match");
			   
			   String id= TestUtil.getValueByJPath(json_response, "/data[0]/id");
			   String first_name= TestUtil.getValueByJPath(json_response, "/data[0]/first_name");
			   String Lastname= TestUtil.getValueByJPath(json_response, "/data[0]/last_name");
			   String avatar= TestUtil.getValueByJPath(json_response, "/data[0]/avatar");
			  String comma=",";
			   System.out.println(id+comma+first_name+comma+Lastname+comma+avatar);
			   
			   // header values iteration with hashmap
			   
			   Header[] HeaderArray =CloseableHttpResponse_obj.getAllHeaders();
			    
				HashMap<String,String> allheader= new HashMap<String,String>();
				
				
				for(Header header:HeaderArray	)				
				{
					allheader.put(header.getName(),header.getValue());					
				}
				
				System.out.println("All header values :" +allheader);
				}
				
		 
			@Test(priority = 2)
		
		public void getApiTestwithheader() throws ClientProtocolException, IOException
		{
			restclient=new RestClient();
			CloseableHttpResponse_obj=	restclient.get(FullUrl_Str);
			
			HashMap <String,String> headerMap= new HashMap<String, String>();
			headerMap.put("Content-Type", "application/json");

			   //to store and validate the status code by assertion
			   int status_code=CloseableHttpResponse_obj.getStatusLine().getStatusCode();
			   
			   System.out.println("status code  -->"+status_code);
			   
			 
			   Assert.assertEquals(status_code, RESPONCE_STATUS_CODE_200,"Status code isn't 200");
			   //Hold Response from api into a string with UTF-8 format
			   
			   String  reponse_string = EntityUtils.toString(CloseableHttpResponse_obj.getEntity(),"UTF-8");
			   
			   JSONObject json_response= new JSONObject(reponse_string);
			   
			   System.out.println("json_response FROM API   -->"+json_response);
			   // calling the utility from json object at root level and doing comparison
			   
			   String per_page_str =TestUtil.getValueByJPath(json_response, "/per_page");			   
			   System.out.println("Value of per page " +per_page_str);
			   Assert.assertEquals(per_page_str, "6","value not match");
				 
			   String total_str =TestUtil.getValueByJPath(json_response, "/total");			   
			   System.out.println("Value of total " +total_str);
			   Assert.assertEquals(total_str, "12","value not match");
			   
			   
			   // get the value from JSON array
			   
			   String total_pages_str =TestUtil.getValueByJPath(json_response, "/total_pages");			   
			   System.out.println("Value of total pages" +total_pages_str);
			   Assert.assertEquals(total_pages_str, "2","value not match");
			   
			   String id= TestUtil.getValueByJPath(json_response, "/data[0]/id");
			   String first_name= TestUtil.getValueByJPath(json_response, "/data[0]/first_name");
			   String Lastname= TestUtil.getValueByJPath(json_response, "/data[0]/last_name");
			   String avatar= TestUtil.getValueByJPath(json_response, "/data[0]/avatar");
			  String comma=",";
			   System.out.println(id+comma+first_name+comma+Lastname+comma+avatar);
			   // header values iteration with hashmap
			   Header[] HeaderArray =CloseableHttpResponse_obj.getAllHeaders();
			    
				HashMap<String,String> allheader= new HashMap<String,String>();
				
				
				for(Header header:HeaderArray	)				
				{
					allheader.put(header.getName(),header.getValue());
					
				}
				
				String transfer_str= TestUtil.getValueByJPath(json_response, "Transfer-Encoding");
				System.out.println("transfer_str values :" +transfer_str);
				}
	
}
