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

public class CreateUserWithArrayNonFunctinal {

	   private String responseBody;
	     
	   //RESTTemplate Object
	    private RestTemplate restTemplate;
	     
	    // Create Response Entity - Stores HTTPStatus Code, Response Body, etc
	    ResponseEntity<String> response ;
	    
	   private String url = "";

	    @BeforeSuite
	    public void loadProperties() throws IOException, ParseException {
	    	PetStoreUtil.info("Calling  CreateUserWithArrayNonFunctinal.loadProperties()");
	    	PetStoreUtil reader = PetStoreUtil.getInstance("project.properties"); 
	    	url = reader.getProperty("petstore.url");
	    }
	    
	    @BeforeTest
	    public void beforeTest() throws IOException, ParseException {
	    	PetStoreUtil.info("Calling  CreateUserWithArrayNonFunctinal.beforeTest()");
	    	PetStoreUtil.info("Creating RestTemplate object before tests");
	        this.restTemplate = new RestTemplate(); 
	    }
	    
	    @AfterTest
	    public void afterTest() {
	    	PetStoreUtil.info("Calling  CreateUserWithArrayNonFunctinal.afterTest()");
	    	PetStoreUtil.info("Resetting response and responseBody object");
	        response = null;
	        responseBody = null;
	    }
	    
	    @Test
	    public void test_createSingleUserWithMoreThanMaxIdSuported() throws IOException, ParseException {
	    	
	    	PetStoreUtil.info("executing test_creatPetWithValidFields");
	    	StringBuffer baseurl = new StringBuffer(url);
	        String addURI = baseurl.append("/v2/user/createWithArray").toString();
	        
	        HttpHeaders headers = new HttpHeaders();        
	        headers.add("Content-Type", "application/json");
	          
	        PetStoreUtil.info("calling URL :"+addURI);
	        PetStoreUtil.info("rest api call type :"+HttpMethod.POST);
	        String jsonBody = "[\r\n"
	        		+ "  {\r\n"
	        		+ "    \"id\": 99999999999999999999999,\r\n"
	        		+ "    \"username\": \"Vivek1\",\r\n"
	        		+ "    \"firstName\": \"Vivek1\",\r\n"
	        		+ "    \"lastName\": \"swarup1\",\r\n"
	        		+ "    \"email\": \"vivek@oracle1.com\",\r\n"
	        		+ "    \"password\": \"vivpwd1\",\r\n"
	        		+ "    \"phone\": \"011\",\r\n"
	        		+ "    \"userStatus\": 11\r\n"
	        		+ "  }\r\n"
	        		+ "]";
	        
	        PetStoreUtil.info("body :\r\n" + jsonBody);
	        HttpEntity entity = new HttpEntity(jsonBody, headers);
	        HttpStatus httpStatus = null; 
	        
	        try {
	        response = this.restTemplate.postForEntity(addURI, entity, String.class);
	        }catch(HttpServerErrorException e)
	        {
	        	PetStoreUtil.info("HTT resposne code :"+e.getStatusCode().value());
	        	httpStatus = e.getStatusCode();
	        }
	        responseBody = (response != null ) && (response.getBody() != null )  ? response.getBody() :"" ;
	        PetStoreUtil.info("responseBody --->;" + responseBody);
	        PetStoreUtil.info("Assert start" );
	        Assert.assertEquals(httpStatus.value(), HttpStatus.BAD_REQUEST.value(),"Actual Response Code should be 400 as payload has invalid Id more than Integer.Max and is client error:");
	        PetStoreUtil.info("Assert completed" );
	        
	    }
	    
	    @Test
	    public void test_createSingleUserWithInvalidPayloadFormat() throws IOException, ParseException {
	    	
	    	PetStoreUtil.info("executing test_createSingleUserWithInvalidPayloadFormat");
	    	StringBuffer baseurl = new StringBuffer(url);
	        String addURI = baseurl.append("/v2/user/createWithArray").toString();
	        
	        HttpHeaders headers = new HttpHeaders();        
	        headers.add("Content-Type", "application/json");
	          
	        PetStoreUtil.info("calling URL :"+addURI);
	        PetStoreUtil.info("rest api call type :"+HttpMethod.POST);
	        String jsonBody = "[\r\n"
	        		+ "  {\r\n"
	        		+ "    \"id\": 9,\r\n"
	        		+ "    \"username\": \"Vivek1\",\r\n"
	        		+ "    \"firstName\": \"Vivek1\",\r\n"
	        		+ "    \"lastName\": \"swarup1\",\r\n"
	        		+ "    \"email\": \"vivek@oracle1.com\",\r\n"
	        		+ "    \"password\": \"vivpwd1\",\r\n"
	        		+ "    \"phone\": \"011\"\r\n"
	        		+ "    \"userStatus\": 11\r\n"
	        		+ "  },\r\n"
	        		+ "]";
	        PetStoreUtil.info("body :\r\n" + jsonBody);
	        HttpEntity entity = new HttpEntity(jsonBody, headers);
	        HttpStatus httpStatus = null; 
	        
	        try {
	        response = this.restTemplate.postForEntity(addURI, entity, String.class);
	        }catch(HttpServerErrorException e)
	        {
	        	PetStoreUtil.info("HTT resposne code :"+e.getStatusCode().value());
	        	httpStatus = e.getStatusCode();
	        }
	        responseBody = (response != null ) && (response.getBody() != null )  ? response.getBody() :"" ;
	        PetStoreUtil.info("responseBody --->;" + responseBody);
	        PetStoreUtil.info("Assert start" );
	        Assert.assertEquals(httpStatus.value(), HttpStatus.BAD_REQUEST.value(),"Actual Response Code should be 400 with schema validation error as it has comma after array:");
	        PetStoreUtil.info("Assert completed" );
	    }
	    

}
