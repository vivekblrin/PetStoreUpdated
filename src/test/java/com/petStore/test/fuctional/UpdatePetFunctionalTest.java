package com.petStore.test.fuctional;

import java.io.IOException;
import java.text.ParseException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import com.petStore.test.utils.PetStoreUtil;

public class UpdatePetFunctionalTest {

	   private String responseBody;
	      	   
	   //RESTTemplate Object
	    private RestTemplate restTemplate;
	     
	    // Create Response Entity - Stores HTTPStatus Code, Response Body, etc
	    ResponseEntity<String> response ;
	    
	    private String url;
	    
	    @BeforeSuite
	    public void loadProperties() throws IOException, ParseException {
	    	PetStoreUtil.info("Calling  CreatePetFunctionalTest.loadProperties()");
	    	PetStoreUtil reader = PetStoreUtil.getInstance("project.properties"); 
	    	url = reader.getProperty("petstore.url");
	    }
	    
	    @BeforeTest
	    public void beforeTest() throws IOException, ParseException {
	    	PetStoreUtil.info("Calling  UpdatePetFunctionalTest.beforeTest()");
	    	PetStoreUtil.info("Creating RestTemplate object before tests");
	        this.restTemplate = new RestTemplate(); 
	    }
	    
	    @AfterTest
	    public void afterTest() {
	    	PetStoreUtil.info("Calling  UpdatePetFunctionalTest.afterTest()");
	    	PetStoreUtil.info("Resetting response and responseBody object");
	        response = null;
	        responseBody = null;
	    }
	    
      @BeforeTest
	   public void createPet() throws IOException, ParseException {
	        
    	    PetStoreUtil.info("executing createPet");
    	    StringBuffer baseurl = new StringBuffer(url);
	        String addURI = baseurl.append("/v2/pet").toString();
	        
	        HttpHeaders headers = new HttpHeaders();        
	        headers.add("Content-Type", "application/json");
	          
	        PetStoreUtil.info("calling URL :"+addURI);
	        PetStoreUtil.info("rest api call type :"+HttpMethod.POST);
	        String jsonBody = "{\r\n"
	        		+ "  \"id\": 1,\r\n"
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
	        		+ "  \"status\": \"available\"\r\n"
	        		+ "}";
	        PetStoreUtil.info("body :\r\n" + jsonBody);
	        HttpEntity entity = new HttpEntity(jsonBody, headers);

	        response = this.restTemplate.postForEntity(addURI, entity, String.class);
	        responseBody = response.getBody();
	        responseBody = response.getBody();
	        PetStoreUtil.info("responseBody --->;" + responseBody);
	        PetStoreUtil.info("Assert start" );
	        Assert.assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
	        PetStoreUtil.info("Assert completed" );
		  }
      
      @Test 
 	   public void test_updatePetWithValidFields() throws IOException, ParseException {
 	        
    	    PetStoreUtil.info("executing test_updatePetWithValidFields");
    	    StringBuffer baseurl = new StringBuffer(url);
 	        String addURI = baseurl.append("/v2/pet").toString();
 	        
 	        HttpHeaders headers = new HttpHeaders();        
 	        headers.add("Content-Type", "application/json");
 	          
 	        PetStoreUtil.info("calling URL :"+addURI);
 	        PetStoreUtil.info("rest api call type :"+HttpMethod.PUT);
 	        String jsonBody = "{\r\n"
 	        		+ "  \"id\": 1,\r\n"
 	        		+ "  \"category\": {\r\n"
 	        		+ "    \"id\": 21,\r\n"
 	        		+ "    \"name\": \"testCategory\"\r\n"
 	        		+ "  },\r\n"
 	        		+ "  \"name\": \"doggie\",\r\n"
 	        		+ "  \"photoUrls\": [\r\n"
 	        		+ "    \"ww.test.com/naupdated\"\r\n"
 	        		+ "  ],\r\n"
 	        		+ "  \"tags\": [\r\n"
 	        		+ "    {\r\n"
 	        		+ "      \"id\": 31,\r\n"
 	        		+ "      \"name\": \"testTags1Updated\"\r\n"
 	        		+ "    }\r\n"
 	        		+ "  ],\r\n"
 	        		+ "  \"status\": \"pending\"\r\n"
 	        		+ "}";
 	        System.out.println("\n\n" + jsonBody);
 	        HttpEntity entity = new HttpEntity(jsonBody, headers);

 	        response = restTemplate.exchange(addURI, HttpMethod.PUT, entity, String.class);
 	        responseBody = response.getBody();
 	        
 	        PetStoreUtil.info("responseBody --->;" + responseBody);
 	        String  returedStatusvalue =  PetStoreUtil.getfieldValueFromResponse(responseBody.toString(),"status");
 	        
 	        PetStoreUtil.info("Assert start" );
 	        Assert.assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
 	        Assert.assertEquals(returedStatusvalue, "pending", "the status was not updated correctly : ");
 	        PetStoreUtil.info("Assert completed" );
 		  }
      
      @Test
 	   public void test_updatePetWithInValidStatus() throws IOException, ParseException {
 	        
    	PetStoreUtil.info("executing test_updatePetWithInValidStatus");
    	StringBuffer baseurl = new StringBuffer(url);
        String addURI = baseurl.append("/v2/pet").toString();
        
        HttpHeaders headers = new HttpHeaders();        
        headers.add("Content-Type", "application/json");
          
        PetStoreUtil.info("calling URL :"+addURI);
        PetStoreUtil.info("rest api call type :"+HttpMethod.PUT);
        String jsonBody = "{\r\n"
        		+ "  \"id\": 1,\r\n"
        		+ "  \"category\": {\r\n"
        		+ "    \"id\": 21,\r\n"
        		+ "    \"name\": \"testCategory\"\r\n"
        		+ "  },\r\n"
        		+ "  \"name\": \"doggie\",\r\n"
        		+ "  \"photoUrls\": [\r\n"
        		+ "    \"ww.test.com/naupdated\"\r\n"
        		+ "  ],\r\n"
        		+ "  \"tags\": [\r\n"
        		+ "    {\r\n"
        		+ "      \"id\": 31,\r\n"
        		+ "      \"name\": \"testTags1Updated\"\r\n"
        		+ "    }\r\n"
        		+ "  ],\r\n"
        		+ "  \"status\": \"available1\"\r\n"
        		+ "}";
        PetStoreUtil.info("body :\r\n" + jsonBody);
        HttpEntity entity = new HttpEntity(jsonBody, headers);

        response = this.restTemplate.postForEntity(addURI, entity, String.class);
        responseBody = response.getBody();
        PetStoreUtil.info("responseBody --->;" + responseBody);
        PetStoreUtil.info("Assert start" );
        Assert.assertEquals(response.getStatusCode().value(), HttpStatus.BAD_REQUEST.value(),"The processing should fail with actual Response Code 400 instead of success as payload has invalid status and is client error: ");
        PetStoreUtil.info("Assert completed" );
 	}
      
      @Test
	   public void test_updatePetWithInvalidIdsPattern() throws IOException, ParseException {
	        
    	PetStoreUtil.info("executing test_updatePetWithInvalidIdsPattern");
    	StringBuffer baseurl = new StringBuffer(url);
        String addURI = baseurl.append("/v2/pet").toString();
        
        HttpHeaders headers = new HttpHeaders();        
        headers.add("Content-Type", "application/json");
          
        PetStoreUtil.info("calling URL :"+addURI);
        PetStoreUtil.info("rest api call type :"+HttpMethod.PUT);
        String jsonBody = "{\r\n"
        		+ "  \"id\": 1,\r\n"
        		+ "  \"category\": {\r\n"
        		+ "    \"id\": 21@123###,\r\n"
        		+ "    \"name\": \"testCategoryUpdated\"\r\n"
        		+ "  },\r\n"
        		+ "  \"name\": \"doggie\",\r\n"
        		+ "  \"photoUrls\": [\r\n"
        		+ "    \"ww.test.com/naupdated\"\r\n"
        		+ "  ],\r\n"
        		+ "  \"tags\": [\r\n"
        		+ "    {\r\n"
        		+ "      \"id\": 31@#$,\r\n"
        		+ "      \"name\": \"testTags1Updated\"\r\n"
        		+ "    }\r\n"
        		+ "  ],\r\n"
        		+ "  \"status\": \"available\"\r\n"
        		+ "}";
        
        PetStoreUtil.info("body :\r\n" + jsonBody);
        HttpEntity entity = new HttpEntity(jsonBody, headers);
        HttpStatus httpStatus = null; 
        
        try {
        response = this.restTemplate.postForEntity(addURI, entity, String.class);
        }catch(HttpServerErrorException e)
        {
        	PetStoreUtil.error("Http Status Code:"+ e.getStatusCode());
        	httpStatus = e.getStatusCode();
        }
        responseBody =response!= null && response.getBody()!= null ? response.getBody() :"";
        PetStoreUtil.info("responseBody --->;" + responseBody);
        PetStoreUtil.info("Assert start" );
        Assert.assertEquals(httpStatus.value(), HttpStatus.BAD_REQUEST.value(),"The processing  failure  Response Code should be 400 as payload has invalidIds and is client error: ");
        PetStoreUtil.info("Assert completed" );
	}
      
      @Test
	   public void test_updatePetWithLongFieldNames() throws IOException, ParseException {
	        
    	PetStoreUtil.info("executing test_updatePetWithLongFieldNames");
    	StringBuffer baseurl = new StringBuffer(url);
        String addURI = baseurl.append("/v2/pet").toString();
        
        HttpHeaders headers = new HttpHeaders();        
        headers.add("Content-Type", "application/json");
          
        PetStoreUtil.info("calling URL :"+addURI);
        PetStoreUtil.info("rest api call type :"+HttpMethod.PUT);
        
        String jsonBody = "{\r\n"
        		+ "  \"id\": 1,\r\n"
        		+ "  \"category\": {\r\n"
        		+ "    \"id\": 21,\r\n"
        		+ "    \"name\": \"testCategorytestCategorytestCategorytestCategorytestCategorytestCategorytestCategorytestCategorytestCategorytestCategorytestCategorytestCategorytestCategorytestCategorytestCategorytestCategorytestCategorytestCategorytestCategorytestCategorytestCategorytestCategorytestCategorytestCategory\"\r\n"
        		+ "  },\r\n"
        		+ "  \"name\": \"doggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggie\",\r\n"
        		+ "  \"photoUrls\": [\r\n"
        		+ "    \"www.testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest.com/naupdated\"\r\n"
        		+ "  ],\r\n"
        		+ "  \"tags\": [\r\n"
        		+ "    {\r\n"
        		+ "      \"id\": 31,\r\n"
        		+ "      \"name\": \"testTags1testTags1testTags1testTags1testTags1testTags1testTags1testTags1testTags1testTags1testTags1testTags1testTags1testTags1testTags1testTags1testTags1testTags1testTags1testTags1testTags1testTags1testTags1testTags1testTags1testTags1testTags1testTags1testTags1testTags1testTags1testTags1testTags1\"\r\n"
        		+ "    }\r\n"
        		+ "  ],\r\n"
        		+ "  \"status\": \"available\"\r\n"
        		+ "}";
        PetStoreUtil.info("body :\r\n" + jsonBody);
        HttpEntity entity = new HttpEntity(jsonBody, headers);
        HttpStatus httpStatus = null; 
        
        response = this.restTemplate.postForEntity(addURI, entity, String.class);
        
        responseBody = (response != null ) && (response.getBody() != null )  ? response.getBody() :"" ;
        PetStoreUtil.info("responseBody --->;" + responseBody);
        PetStoreUtil.info("Assert start" );
        Assert.assertEquals(response.getStatusCode().value(), HttpStatus.BAD_REQUEST.value(),"Actual Response Code should be 400 as payload has long Field  names and is client error:");
        PetStoreUtil.info("Assert completed" );
	  }
      	    
}
