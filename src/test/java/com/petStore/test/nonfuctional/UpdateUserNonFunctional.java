package com.petStore.test.nonfuctional;
import java.io.IOException;
import java.text.ParseException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.petStore.test.utils.PetStoreUtil;

public class UpdateUserNonFunctional {

	   private String responseBody;
	   
	    //RESTTemplate Object
	    private RestTemplate restTemplate;
	     
	    // Create Response Entity - Stores HTTPStatus Code, Response Body, etc
	    ResponseEntity<String> response ;
	    
		private String url = "";
		
	    @BeforeSuite
	    public void loadProperties() throws IOException, ParseException {
	    	PetStoreUtil.info("Calling  UpdateUserNonFunctional.loadProperties()");
	    	PetStoreUtil reader = PetStoreUtil.getInstance("project.properties"); 
	    	url = reader.getProperty("petstore.url");
	    }	
	    
	    @BeforeTest
	    public void beforeTest() throws IOException, ParseException {
	    	PetStoreUtil.info("Calling  UpdateUserNonFunctional.beforeTest()");
	    	PetStoreUtil.info("Creating RestTemplate object before tests");
	        this.restTemplate = new RestTemplate(); 
	    }
	    
	    @AfterTest
	    public void afterTest() {
	    	PetStoreUtil.info("Calling  UpdateUserNonFunctional.afterTest()");
	    	PetStoreUtil.info("Resetting response and responseBody object");
	        response = null;
	        responseBody = null;
	    }
	    
	    @Test
	    public void test_updateUserWithNonExistingUserName() throws IOException, ParseException {
	    	
	    	PetStoreUtil.info("executing test_updateUserWithNonExistingUserName");
	    	StringBuffer baseurl = new StringBuffer(url);
	    	String addURI = baseurl.append("/v2/user/").append("VivekDoesNotExists").toString();
	        PetStoreUtil.info("calling URL :"+addURI);
	        PetStoreUtil.info("rest api call type :"+HttpMethod.PUT);
	        String jsonBody = "{\r\n"
		        		+ "    \"id\": 1234,\r\n"
		        		+ "    \"username\": \"VivekDoesNotExistsUpdated\",\r\n"
		        		+ "    \"firstName\": \"vivekUpdated\",\r\n"
		        		+ "    \"lastName\": \"vivekUpdated\",\r\n"
		        		+ "    \"email\": \"test1@gmail.com\",\r\n"
		        		+ "    \"password\": \"pwd1\",\r\n"
		        		+ "    \"phone\": \"7564545644999\",\r\n"
		        		+ "    \"userStatus\": 1234\r\n"
		        		+ "  }\r\n";
	        
	        PetStoreUtil.info("body :\r\n" + jsonBody);
	        
	        HttpHeaders headers = new HttpHeaders();        
	        headers.add("Content-Type", "application/json");
	        HttpEntity entity = new HttpEntity(jsonBody, headers);
	        
	        response = restTemplate.exchange(addURI, HttpMethod.PUT, entity, String.class);
	        responseBody = response.getBody();
	        PetStoreUtil.info("responseBody --->;" + responseBody);
	        PetStoreUtil.info("Assert start" );
	        Assert.assertEquals(response.getStatusCode().value(), HttpStatus.NOT_FOUND.value(),"Processing should fail with 404 instead of success as we are trying to update nonexistent object  :");
	        PetStoreUtil.info("Assert completed" );
	    }
	    
}
