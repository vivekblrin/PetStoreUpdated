package com.petStore.test.nonfuctional;

import java.io.IOException;
import java.text.ParseException;


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

public class CreatePetNonFunctional {
	   
	   public String responseBody;
	   	    
	   //RESTTemplate Object
	   private RestTemplate restTemplate;
	     
	   ResponseEntity<String> response ;
	   
	   private String url;

	    @BeforeSuite
	    public void loadProperties() throws IOException, ParseException {
	    	PetStoreUtil.info("Calling  CreatePetNonFunctional.loadProperties()");
	    	PetStoreUtil reader = PetStoreUtil.getInstance("project.properties"); 
	    	url = reader.getProperty("petstore.url");
	    }
	    
	    @BeforeTest
	    public void beforeTest() throws IOException, ParseException {
	    	PetStoreUtil.info("Calling  CreatePetNonFunctional.beforeTest()");
	    	PetStoreUtil.info("Creating RestTemplate object before tests");
	        this.restTemplate = new RestTemplate(); 
	    }
	    
	    @AfterTest
	    public void afterTest() {
	    	PetStoreUtil.info("Calling  CreatePetNonFunctional.afterTest()");
	    	PetStoreUtil.info("Resetting response and responseBody object");
	        response = null;
	        responseBody = null;
	    }
	    
      @Test
    public void test_creatPetWithNonExixtentFieldNameandValue() throws IOException, ParseException {
    	
    	PetStoreUtil.info("executing test_creatPetWithNonExixtentFieldNameandValue");
    	StringBuffer baseurl = new StringBuffer(url);
        String addURI = baseurl.append("/v2/pet").toString();
        
        HttpHeaders headers = new HttpHeaders();        
        headers.add("Content-Type", "application/json");
          
        PetStoreUtil.info("calling URL :"+addURI);
        PetStoreUtil.info("rest api call type :"+HttpMethod.POST);
        String jsonBody = "{\r\n"
        		+ "  \"id\": 1,\r\n"
        		+ "  \"Test\": \"Test\",\r\n"
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
        Assert.assertEquals(response.getStatusCode().value(), HttpStatus.BAD_REQUEST.value(),"Processing should fail with Error Code 400 rather than success as invalid field shoudld throw schema validation error :");
        PetStoreUtil.info("Assert completed" );
	  }
	      
	      @Test
	    public void test_creatPetWithImproperJsonFormat() throws IOException, ParseException {
	    	
	        PetStoreUtil.info("executing test_creatPetWithValidFields");
	        StringBuffer baseurl = new StringBuffer(url);
	        String addURI = baseurl.append("/v2/pet").toString();
	        
	        HttpHeaders headers = new HttpHeaders();        
	        headers.add("Content-Type", "application/json");
	          
	        PetStoreUtil.info("calling URL :"+addURI);
	        PetStoreUtil.info("rest api call type :"+HttpMethod.POST);
	        String jsonBody = "{\r\n"
	        		+ "  \"id\": 112,\r\n"
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
	        		+ "    },\r\n"
	        		+ "  ],\r\n"
	        		+ "  \"status\": \"available\"\r\n"
	        		+ "}";
	        PetStoreUtil.info("body :\r\n" + jsonBody);
	        HttpEntity entity = new HttpEntity(jsonBody, headers);
	    	HttpStatus httpStatus = null;

	        try {
               response = this.restTemplate.postForEntity(addURI, entity, String.class);
	        }catch(HttpServerErrorException e) {
	        	PetStoreUtil.error("Http error code"+ e.getStatusCode());
	        	httpStatus = e.getStatusCode();
	        }
	        responseBody = (response != null ) && (response.getBody() != null )  ? response.getBody() :"" ;
	        PetStoreUtil.info("responseBody --->;" + responseBody);
	        PetStoreUtil.info("Assert start" );
	        Assert.assertEquals(httpStatus.value(), HttpStatus.BAD_REQUEST.value(),"Processing should fail with Error Code 400 as Payload has as invalid comma in tag array :");
	        PetStoreUtil.info("Assert completed" );
		}
	      
		       
}
