package com.qa.client;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	
	// Create Get Method and pass url in parameters
	public CloseableHttpResponse get (String url) throws ClientProtocolException, IOException
	{
		
	CloseableHttpClient httpClient=	HttpClients.createDefault();
	HttpGet httpget =new HttpGet(url);
	// to send get api call
   CloseableHttpResponse   CloseableHttpResponse_obj=	  httpClient.execute(httpget);
	
	return CloseableHttpResponse_obj;
	}
	
	// Create Get Method and pass url and header in parameters
		public CloseableHttpResponse get (String url,HashMap<String , String> headermap ) throws ClientProtocolException, IOException
		{
			
		CloseableHttpClient httpClient=	HttpClients.createDefault();
		HttpGet httpget =new HttpGet(url);
		
		
		// create a loop to iterate the key value pairs in API request. these parameter given while method call
		for(Map.Entry<String, String> entry : headermap.entrySet())
		{
			httpget.addHeader(entry.getKey(), entry.getValue());	
			
		}
		// to send get api call
	   CloseableHttpResponse   CloseableHttpResponse_obj=	  httpClient.execute(httpget);
		
		return CloseableHttpResponse_obj;
		}
		
		//Create a post method
		
		public CloseableHttpResponse post(String url, String entityString, HashMap<String,String> headermap) throws ClientProtocolException, IOException
		{
			
			CloseableHttpClient httpClient=	HttpClients.createDefault();
			HttpPost httppost= new HttpPost(url);
			httppost.setEntity(new StringEntity(entityString)); //use to set the payload in from of json
			// create a loop to iterate the key value pairs in API request. these parameter given while method call
			for(Map.Entry<String, String> entry : headermap.entrySet())
			{
				httppost.addHeader(entry.getKey(), entry.getValue());	
				
			}
			// to send get api call
		   CloseableHttpResponse   CloseableHttpResponse_obj=	  httpClient.execute(httppost);
		   return CloseableHttpResponse_obj;
		}
	
}
