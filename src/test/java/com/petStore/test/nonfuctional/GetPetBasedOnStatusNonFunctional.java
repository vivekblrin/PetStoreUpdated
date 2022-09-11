package com.petStore.test.nonfuctional;

import java.io.IOException;
import java.text.ParseException;

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
public class GetPetBasedOnStatusNonFunctional {

	    private String responseBody;
	    
	    //RESTTemplate Object
	    private RestTemplate restTemplate;
	     
	    // Create Response Entity - Stores HTTPStatus Code, Response Body, etc
	    ResponseEntity<String> response ;
	    
	    static String[] strStatusArray= new String[] {"available", "pending", "sold"};
	    
	   private String url;

	    @BeforeSuite
	    public void loadProperties() throws IOException, ParseException {
	    	PetStoreUtil.info("Calling  GetPetBasedOnStatusNonFunctional.loadProperties()");
	    	PetStoreUtil reader = PetStoreUtil.getInstance("project.properties"); 
	    	url = reader.getProperty("petstore.url");
	    }
		    
	    @BeforeTest
	    public void beforeTest() throws IOException, ParseException {
	    	PetStoreUtil.info("Calling  GetPetBasedOnStatusNonFunctional.beforeTest()");
	    	PetStoreUtil.info("Creating RestTemplate object before tests");
	        this.restTemplate = new RestTemplate(); 
	    }
	    
	    @AfterTest
	    public void afterTest() {
	    	PetStoreUtil.info("Calling  GetPetBasedOnStatusNonFunctional.afterTest()");
	    	PetStoreUtil.info("Resetting response and responseBody object");
	        response = null;
	        responseBody = null;
	    }
	    
	    @Test
	    public void test_getPetsBasedOnFieldStatusAvailableWithsqlInjection() throws IOException, ParseException {

	    	PetStoreUtil.info("executing test_getPetsBasedOnFieldStatusAvailableWithsqlInjection");
	        StringBuffer baseurl = new StringBuffer(url);
	        String addURI = baseurl.append("v2/pet/findByStatus?status=").append(strStatusArray[0]).append(" OR 1=1").toString();
	        
	        HttpHeaders headers = new HttpHeaders();        
	        headers.add("accept", "application/json");
	          
	        PetStoreUtil.info("calling URL :"+addURI);
	        PetStoreUtil.info("rest api call type :"+HttpMethod.GET);
	        response = restTemplate.getForEntity(addURI,String.class);
	        responseBody = response.getBody();
	        PetStoreUtil.info("responseBody --->;" + responseBody);
	        PetStoreUtil.info("Assert start" );
	        Assert.assertEquals(response.getStatusCode().value(),HttpStatus.BAD_REQUEST.value(),"The processing should fail with actual Response Code 400 as payload has sql inject insearch query: ");
	        
		    }
	    
}
