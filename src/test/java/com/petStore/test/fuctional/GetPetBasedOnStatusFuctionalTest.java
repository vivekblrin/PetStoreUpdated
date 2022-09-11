package com.petStore.test.fuctional;


import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.petStore.test.utils.PetStoreUtil;

public class GetPetBasedOnStatusFuctionalTest {


	   public String responseBody;
	   
	    //RESTTemplate Object
	   private RestTemplate restTemplate;
	    // Create Response Entity - Stores HTTPStatus Code, Response Body, etc
	    ResponseEntity<String> response ;
	    
	    static String[] strStatusArray= new String[] {"available", "pending", "sold"};
	    
	    private String url ;
	    
	    @BeforeSuite
	    public void loadProperties() throws IOException, ParseException {
	    	PetStoreUtil.info("Calling  GetPetBasedOnStatusFuctionalTest.loadProperties()");
	    	PetStoreUtil reader = PetStoreUtil.getInstance("project.properties"); 
	    	url = reader.getProperty("petstore.url");
	    }
	    
	    @BeforeTest
	    public void beforeTest() throws IOException, ParseException {
	    	PetStoreUtil.info("Calling  GetPetBasedOnStatusFuctionalTest.beforeTest()");
	    	PetStoreUtil.info("Creating RestTemplate object before tests");
	        this.restTemplate = new RestTemplate(); 
	    }
	    
	    @AfterTest
	    public void afterTest() {
	    	PetStoreUtil.info("Calling  GetPetBasedOnStatusFuctionalTest.afterTest()");
	    	PetStoreUtil.info("Resetting response and responseBody object");
	        response = null;
	        responseBody = null;
	    }
	    
	    @BeforeClass
	    public void creatPetWithDifferentStatus() throws IOException, ParseException {

	    	PetStoreUtil.info("executing creatPetWithDifferentStatus");
	    	StringBuffer baseurl = new StringBuffer(url);
	        String addURI = baseurl.append("/v2/pet").toString();
	        
	        HttpHeaders headers = new HttpHeaders();        
	        headers.add("Content-Type", "application/json");
	          
	        PetStoreUtil.info("calling URL :"+addURI);
	        PetStoreUtil.info("rest api call type :"+HttpMethod.POST);
	        
	        for (int i=0 ;i<strStatusArray.length  ;i++)
	        {
	        	int IdValue = i+1;
		        String jsonBody = "{\r\n"
		        		+ "  \"id\": "+ IdValue +",\r\n"
		        		+ "  \"category\": {\r\n"
		        		+ "    \"id\": 21,\r\n"
		        		+ "    \"name\": \"TestCategory\"\r\n"
		        		+ "  },\r\n"
		        		+ "  \"name\": \"doggie\",\r\n"
		        		+ "  \"photoUrls\": [\r\n"
		        		+ "    \"www.testurl.com/image1\"\r\n"
		        		+ "  ],\r\n"
		        		+ "  \"tags\": [\r\n"
		        		+ "    {\r\n"
		        		+ "      \"id\": 31,\r\n"
		        		+ "      \"name\": \"testTag1\"\r\n"
		        		+ "    }\r\n"
		        		+ "  ],\r\n"
		        		+ "  \"status\": \""+ strStatusArray[i] +"\"\r\n"
		        		+ "}";
		        PetStoreUtil.info("body :\r\n" + jsonBody);
		        HttpEntity entity = new HttpEntity(jsonBody, headers);
	
		        response = this.restTemplate.postForEntity(addURI, entity, String.class);
		        responseBody = response.getBody();
		        PetStoreUtil.info("responseBody --->;" + responseBody);
	         }
		    }

	    
	    @Test
	    public void test_getPetsBasedOnFieldStatusAvailable() throws IOException, ParseException {

	    	PetStoreUtil.info("executing test_getPetsBasedOnFieldStatusAvailable");
	    	StringBuffer baseurl = new StringBuffer(url);
	        String addURI = baseurl.append("/v2/pet/findByStatus?status=").append(strStatusArray[0]).toString();
	        
	        HttpHeaders headers = new HttpHeaders();        
	        headers.add("accept", "application/json");
	          
	        PetStoreUtil.info("calling URL :"+addURI);
	        PetStoreUtil.info("rest api call type :"+HttpMethod.GET);
	        response = restTemplate.getForEntity(addURI,String.class);
	        responseBody = response.getBody();
	        PetStoreUtil.info("responseBody --->;" + responseBody);
	        PetStoreUtil.info("Assert start" );
	        boolean correctStausCodeRetrived =  PetStoreUtil.getJSONObjectValueArrayResponse(responseBody , "status", strStatusArray[0]);
	        Assert.assertTrue(correctStausCodeRetrived, "QueryParam did not retried the enm status value: ");
	        PetStoreUtil.info("Assert completed" );
		}
	    
	    @Test
	    public void test_getPetsBasedOnFieldStatusPending() throws IOException, ParseException {

	    	PetStoreUtil.info("executing test_getPetsBasedOnFieldStatusPending");
	    	StringBuffer baseurl = new StringBuffer(url);
	    	
	    	StringBuffer URI = new StringBuffer("/v2/pet/findByStatus?status=").append(strStatusArray[1]);
	        String addURI = baseurl.append(URI).toString();
	        
	        HttpHeaders headers = new HttpHeaders();        
	        headers.add("accept", "application/json");
	          
	        PetStoreUtil.info("calling URL :"+addURI);
	        PetStoreUtil.info("rest api call type :"+HttpMethod.GET);

	        response = restTemplate.getForEntity(addURI,String.class);
	        responseBody = response.getBody();
	        PetStoreUtil.info("responseBody --->;" + responseBody);
	        PetStoreUtil.info("Assert start" );
	        boolean correctStausCodeRetrived =  PetStoreUtil.getJSONObjectValueArrayResponse(responseBody , "status", strStatusArray[1]);

	        Assert.assertTrue(correctStausCodeRetrived, "QueryParam did not retrvied the enm status value: ");
	        
	        PetStoreUtil.info("Assert completed" );

		    }
	    
	    @Test
	    public void test_getPetsBasedOnFieldStatusSold() throws IOException, ParseException {

	    	PetStoreUtil.info("executing test_getPetsBasedOnFieldStatusSold");
	    	StringBuffer baseurl = new StringBuffer(url);
	    	
	        String addURI = baseurl.append("/v2/pet/findByStatus?status=").append(strStatusArray[2]).toString();
	        
	        HttpHeaders headers = new HttpHeaders();        
	        headers.add("accept", "application/json");
	          
	        PetStoreUtil.info("calling URL :"+addURI);
	        PetStoreUtil.info("rest api call type :"+HttpMethod.GET);

	        response = restTemplate.getForEntity(addURI,String.class);
	        responseBody = response.getBody();
	        PetStoreUtil.info("responseBody --->;" + responseBody);
	        PetStoreUtil.info("Assert start" );
	        boolean correctStausCodeRetrived =  PetStoreUtil.getJSONObjectValueArrayResponse(responseBody , "status", strStatusArray[2]);

	        Assert.assertTrue(correctStausCodeRetrived, "QueryParam did not retrvied the enm status value: ");
	        
	        PetStoreUtil.info("Assert completed" );

		  }	   
	    
}
