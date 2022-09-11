package com.petStore.test.nonfuctional;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.petStore.test.utils.PetStoreUtil;

public class GetUserByNameNonFucntional {

	    
	    public String responseBody;
	    
	    //RESTTemplate Object
	    private RestTemplate restTemplate;
	     
	    // Create Response Entity - Stores HTTPStatus Code, Response Body, etc
	    ResponseEntity<String> response ;
	    
		 private String url;

	    @BeforeSuite
	    public void loadProperties() throws IOException, ParseException {
	    	PetStoreUtil.info("Calling  GetUserByNameNonFucntional.loadProperties()");
	    	PetStoreUtil reader = PetStoreUtil.getInstance("project.properties"); 
	    	url = reader.getProperty("petstore.url");
	    }
		    
	    @BeforeTest
	    public void beforeTest() throws IOException, ParseException {
	    	PetStoreUtil.info("Calling  GetUserByNameNonFucntional.beforeTest()");
	    	PetStoreUtil.info("Creating RestTemplate object before tests");
	        this.restTemplate = new RestTemplate(); 
	    }
	    
	    @AfterTest
	    public void afterTest() {
	    	PetStoreUtil.info("Calling  GetUserByNameNonFucntional.afterTest()");
	    	PetStoreUtil.info("Resetting response and responseBody object");
	        response = null;
	        responseBody = null;
	    }
	    
	    @Test
	    public void test_getUserForNonExistentUserName() throws IOException, ParseException {
	    	
	    	PetStoreUtil.info("executing test_getUserForNonExistentUserName");
	    	StringBuffer baseurl = new StringBuffer(url);

	    	String addURI  = baseurl.append("/v2/user/").append("vivekNonExistent").toString();
	    	HttpStatus httpStatus = null;
	        PetStoreUtil.info("calling URL :"+addURI);
	        PetStoreUtil.info("rest api call type :"+HttpMethod.GET);
	        try {
	        response = restTemplate.getForEntity(addURI,String.class);
	        }catch(HttpClientErrorException e) {
	        	PetStoreUtil.info("HTTP resposne code :"+e.getStatusCode().value());
	        	httpStatus = e.getStatusCode();
	        }
	        responseBody = (response != null ) && (response.getBody() != null )  ? response.getBody() :"" ;   
	        PetStoreUtil.info("responseBody --->;" + responseBody);
	        PetStoreUtil.info("Assert start" );
	        Assert.assertEquals(httpStatus.value(), HttpStatus.NOT_FOUND.value(),"Processing should fail with 404 As we are trying to fetch invalid user details :");
	        PetStoreUtil.info("Assert completed" );
	    }
	    
	    @Test
	    public void test_getUserForNonExistentUserNameWithSqlInjection() throws IOException, ParseException {
	    	
	    	PetStoreUtil.info("executing test_getUserForNonExistentUserNameWithSqlInjection");
	    	StringBuffer baseurl = new StringBuffer(url);
	    	String addURI  = baseurl.append("/v2/user/").append("vivekUpdated OR 1=1").toString();
	    	HttpStatus httpStatus = null;
	        PetStoreUtil.info("calling URL :"+addURI);
	        PetStoreUtil.info("rest api call type :"+HttpMethod.GET);
	        try {
	        response = restTemplate.getForEntity(addURI,String.class);
	        }catch(HttpClientErrorException e) {
	        	PetStoreUtil.info("HTTP resposne code :"+e.getStatusCode().value());
	        	httpStatus = e.getStatusCode();
	        }
	        responseBody = (response != null ) && (response.getBody() != null )  ? response.getBody() :"" ;
	        PetStoreUtil.info("responseBody --->;" + responseBody);
	        PetStoreUtil.info("Assert start" );
	        Assert.assertEquals(httpStatus.value(), HttpStatus.BAD_REQUEST.value(),"Processing should fail with Error Code 400 as Payload rather than processing giving 404 not found search result as we had sql injection in query paramaters :");
	        PetStoreUtil.info("Assert completed" );
	    }
	    

}
