package com.petStore.test.fuctional;


import java.io.IOException;
import java.text.ParseException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.petStore.test.utils.PetStoreUtil;


public class CreatePetFunctionalTest {

	   private String responseBody;
	      
	   private RestTemplate restTemplate;
	   
	   ResponseEntity<String> response ;
	   
	   private String url ;

	    @BeforeSuite
	    public void loadProperties() throws IOException, ParseException {
	    	PetStoreUtil.info("Calling  CreatePetFunctionalTest.loadProperties()");
	    	PetStoreUtil reader = PetStoreUtil.getInstance("project.properties"); 
	    	url = reader.getProperty("petstore.url");
	    }
	    
	    @BeforeTest
	    public void beforeTest() throws IOException, ParseException {
	    	PetStoreUtil.info("Calling  CreatePetFunctionalTest.beforeTest()");
	    	PetStoreUtil.info("Creating RestTemplate object before tests");
	    	this.restTemplate = new RestTemplate(); 
	    }
	    
	    @AfterTest
	    public void afterTest() {
	    	PetStoreUtil.info("Calling  CreatePetFunctionalTest.afterTest()");
	    	PetStoreUtil.info("Resetting response and responseBody object");
	        response = null;
	        responseBody = null;
	    }
	    

	    @Test
    public void test_creatPetWithValidFields() throws IOException, ParseException {

    	PetStoreUtil.info("executing test_creatPetWithValidFields");
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
        PetStoreUtil.info("responseBody --->;" + responseBody);
        PetStoreUtil.info("Assert start" );
        Assert.assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        PetStoreUtil.info("Assert completed" );
	 }

       
	      @Test
    public void test_creatPetWithInValidPhotoUrls() throws IOException, ParseException {
	    	       
    	PetStoreUtil.info("executing test_creatPetWithInValidPhotoUrls");
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
        		+ "    \"name\": \"testCategory\"\r\n"
        		+ "  },\r\n"
        		+ "  \"name\": \"doggie\",\r\n"
        		+ "  \"photoUrls\": [\r\n"
        		+ "    \"wert_7343248_8347&^%$@\"\r\n"
        		+ "  ],\r\n"
        		+ "  \"tags\": [\r\n"
        		+ "    {\r\n"
        		+ "      \"id\": 31,\r\n"
        		+ "      \"name\": \"testTags1\"\r\n"
        		+ "    }\r\n"
        		+ "  ],\r\n"
        		+ "  \"status\": \"available\"\r\n"
        		+ "}";
        PetStoreUtil.info("body :\r\n" + jsonBody);
        HttpEntity entity = new HttpEntity(jsonBody, headers);

        response = this.restTemplate.postForEntity(addURI, entity, String.class);
        responseBody = response.getBody();
        PetStoreUtil.info("responseBody --->;" + responseBody);
        PetStoreUtil.info("Assert start" );
        Assert.assertEquals(response.getStatusCode().value(),HttpStatus.BAD_REQUEST.value(),"The processing should fail with actual Response Code 400 as payload has invlid photourls and is client error: ");
        PetStoreUtil.info("Assert completed" );
	}     
	      
	@Test
	public void test_creatPetWithLongFieldNames() throws IOException, ParseException {
	    	
    	PetStoreUtil.info("executing test_creatPetWithLongFieldNames");
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
        		+ "    \"name\": \"TestCategoryTestCategoryTestCategoryTestCategoryTestCategoryTestCategoryTestCategoryTestCategoryTestCategoryTestCategoryTestCategoryTestCategoryTestCategoryTestCategoryTestCategoryTestCategoryTestCategoryTestCategoryTestCategoryTestCategoryTestCategoryTestCategoryTestCategoryTestCategoryTestCategoryTestCategoryTestCategory\"\r\n"
        		+ "  },\r\n"
        		+ "  \"name\": \"doggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggiedoggie\",\r\n"
        		+ "  \"photoUrls\": [\r\n"
        		+ "    \"www.testurltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltesturltes.com/image1\"\r\n"
        		+ "  ],\r\n"
        		+ "  \"tags\": [\r\n"
        		+ "    {\r\n"
        		+ "      \"id\": 31,\r\n"
        		+ "      \"name\": \"testTag1testTag1testTag1testTag1testTag1testTag1testTag1testTag1testTag1testTag1testTag1testTag1testTag1testTag1testTag1testTag1testTag1testTag1testTag1testTag1testTag1testTag1testTag1testTag1testTag1testTag1testTag1testTag1testTag1testTag1testTag1testTag1testTag1testTag1testTag1testTag1testTag1testTag1testTag1testTag1testTag1\"\r\n"
        		+ "    }\r\n"
        		+ "  ],\r\n"
        		+ "  \"status\": \"availableavailableavailableavailableavailableavailableavailableavailableavailableavailableavailableavailableavailableavailableavailableavailableavailableavailableavailableavailableavailableavailableavailableavailableavailableavailableavailableavailableavailableavailableavailable\"\r\n"
        		+ "}";
        
        PetStoreUtil.info("body :\r\n" + jsonBody);
        HttpEntity entity = new HttpEntity(jsonBody, headers);

        response = this.restTemplate.postForEntity(addURI, entity, String.class);
        responseBody = response.getBody();
        PetStoreUtil.info("responseBody --->;" + responseBody);
        PetStoreUtil.info("Assert start" );
        Assert.assertEquals(response.getStatusCode().value(), HttpStatus.BAD_REQUEST.value(),"The processing should fail with actual Response Code 400 as payload has long field values and is client error: ");
        PetStoreUtil.info("Assert completed" );
    }
	      
	 @Test
    public void test_creatPetWithInvalidPetIdPattern() throws IOException, ParseException {
	    		        
    	PetStoreUtil.info("executing test_creatPetWithInvalidPetIdPattern");
    	StringBuffer baseurl = new StringBuffer(url);
        String addURI = baseurl.append("/v2/pet").toString();
        
        HttpHeaders headers = new HttpHeaders();        
        headers.add("Content-Type", "application/json");
          
        PetStoreUtil.info("calling URL :"+addURI);
        PetStoreUtil.info("rest api call type :"+HttpMethod.POST);
        String jsonBody = "{\r\n"
        		+ "  \"id\": 1@12&845_!@34,\r\n"
        		+ "  \"category\": {\r\n"
        		+ "    \"id\": 1@12&845_!@34,\r\n"
        		+ "    \"name\": \"TestCategory\"\r\n"
        		+ "  },\r\n"
        		+ "  \"name\": \"doggie\",\r\n"
        		+ "  \"photoUrls\": [\r\n"
        		+ "    \"www.testurl.com/image1\"\r\n"
        		+ "  ],\r\n"
        		+ "  \"tags\": [\r\n"
        		+ "    {\r\n"
        		+ "      \"id\": 1@12&845_!@34,\r\n"
        		+ "      \"name\": \"testTag1\"\r\n"
        		+ "    }\r\n"
        		+ "  ],\r\n"
        		+ "  \"status\": \"available\"\r\n"
        		+ "}";
        PetStoreUtil.info("body :\r\n" + jsonBody);
        HttpEntity entity = new HttpEntity(jsonBody, headers);
        HttpStatus httpStatus = null; 
        
        try {
        response = this.restTemplate.postForEntity(addURI, entity, String.class);
        }catch(HttpClientErrorException e)
        {
        	PetStoreUtil.error("HTTP response code :" + e.getStatusCode().value());
        	httpStatus = e.getStatusCode();
        }
        responseBody = (response != null ) && (response.getBody() != null )  ? response.getBody() :"" ;
        PetStoreUtil.info("responseBody --->;" + responseBody);
        PetStoreUtil.info("Assert start" );
        Assert.assertEquals(httpStatus.value(), HttpStatus.BAD_REQUEST.value(),"Actual Response Code should be 400 as payload has invalid Id Pattern and is client error:");
        PetStoreUtil.info("Assert completed" );
    }
	      
	      
	        @Test
    public void test_creatPetWithInValidStatus() throws IOException, ParseException {
	    PetStoreUtil.info("executing test_creatPetWithInValidStatus");
	    StringBuffer baseurl = new StringBuffer(url);
        String addURI = baseurl.append("/v2/pet").toString();
        
        HttpHeaders headers = new HttpHeaders();        
        headers.add("Content-Type", "application/json");
          
        PetStoreUtil.info("calling URL :"+addURI);
        PetStoreUtil.info("rest api call type :"+HttpMethod.POST);
        String[] strStatusArray= new String[] {"available1", "pending1", "sold1"};
        for (int i= 0 ;i < strStatusArray.length ; i++ ) 
        {
	        String jsonBody = "{\r\n"
	        		+ "  \"id\": 1,\r\n"
	        		+ "  \"category\": {\r\n"
	        		+ "    \"id\": 21,\r\n"
	        		+ "    \"name\": \"testCategory\"\r\n"
	        		+ "  },\r\n"
	        		+ "  \"name\": \"doggie\",\r\n"
	        		+ "  \"photoUrls\": [\r\n"
	        		+ "    \"www.test.url/image\"\r\n"
	        		+ "  ],\r\n"
	        		+ "  \"tags\": [\r\n"
	        		+ "    {\r\n"
	        		+ "      \"id\": 31,\r\n"
	        		+ "      \"name\": \"testTags1\"\r\n"
	        		+ "    }\r\n"
	        		+ "  ],\r\n"
	        		+ "  \"status\": \""+ strStatusArray[i] +"\"\r\n"
	        		+ "}";
	        PetStoreUtil.info("body :\r\n" + jsonBody);
	        HttpEntity entity = new HttpEntity(jsonBody, headers);

	        response = this.restTemplate.postForEntity(addURI, entity, String.class);
	        responseBody = response.getBody();
	        PetStoreUtil.info("responseBody --->;" + responseBody);
	        PetStoreUtil.info("Assert start" );
	        Assert.assertEquals(response.getStatusCode().value(),HttpStatus.BAD_REQUEST.value(),"The processing should fail with actual Response Code 400 as payload has invlid status enum values and is client error: ");
	        PetStoreUtil.info("Assert completed" );
          }
	  } 
	           	    
}
