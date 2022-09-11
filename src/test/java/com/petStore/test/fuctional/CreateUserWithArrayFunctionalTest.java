package com.petStore.test.fuctional;

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

public class CreateUserWithArrayFunctionalTest {

	   
	   public String responseBody;
	   
	   private RestTemplate restTemplate;
	    
	   ResponseEntity<String> response;
	   
	   private String url;
	    
	   
	    @BeforeSuite
	    public void loadProperties() throws IOException, ParseException {
	    	PetStoreUtil.info("Calling  CreateUserWithArrayFunctionalTest.loadProperties()");
	    	PetStoreUtil reader = PetStoreUtil.getInstance("project.properties"); 
	    	url = reader.getProperty("petstore.url");
	    }
	    
	    @BeforeTest
	    public void beforeTest() throws IOException, ParseException {
	    	PetStoreUtil.info("Calling  CreateUserWithArrayFunctionalTest.beforeTest()");
	    	PetStoreUtil.info("Creating RestTemplate object before tests");
	        this.restTemplate = new RestTemplate(); 
	        
	    }
	    
	    @AfterTest
	    public void afterTest() {
	    	PetStoreUtil.info("Calling  CreateUserWithArrayFunctionalTest.afterTest()");
	    	PetStoreUtil.info("Resetting response and responseBody object");
	        response = null;
	        responseBody = null;
	    }
	    
	    @Test
	    public void test_addOneUser() throws IOException, ParseException {
	    	PetStoreUtil.info("executing test_addOneUser");
	    	StringBuffer baseurl = new StringBuffer(url);
	        String addURI = baseurl.append("/v2/user/createWithArray").toString();
	        
	        HttpHeaders headers = new HttpHeaders();        
	        headers.add("Content-Type", "application/json");
	          
	        PetStoreUtil.info("calling URL :"+addURI);
	        PetStoreUtil.info("rest api call type :"+HttpMethod.POST);
	        String jsonBody = "[{\"id\": 123,\"username\": \"Vivek\", \"firstName\": \"vivek\",\"lastName\": \"swarup\", \"email\": \"sendmail.v22@gmail.com\", \"password\": \"pwd1\",\"phone\": \"011\", \"userStatus\": 12}]";
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
	public void test_addMultipleUser() throws IOException, ParseException {
		PetStoreUtil.info("executing test_addMultipleUser");
    	
    	StringBuffer baseurl = new StringBuffer(url);
        String addURI = baseurl.append("/v2/user/createWithArray").toString();
        
        HttpHeaders headers = new HttpHeaders();        
        headers.add("Content-Type", "application/json");
          
        PetStoreUtil.info("calling URL :"+addURI);
        PetStoreUtil.info("rest api call type :"+HttpMethod.POST);
        String jsonBody = "[\r\n"
        		+ "  {\r\n"
        		+ "    \"id\": 1,\r\n"
        		+ "    \"username\": \"Vivek1\",\r\n"
        		+ "    \"firstName\": \"Vivek1\",\r\n"
        		+ "    \"lastName\": \"swarup1\",\r\n"
        		+ "    \"email\": \"vivek@oracle1.com\",\r\n"
        		+ "    \"password\": \"vivpwd1\",\r\n"
        		+ "    \"phone\": \"011\",\r\n"
        		+ "    \"userStatus\": 11\r\n"
        		+ "  },\r\n"
        		+ "  {\r\n"
        		+ "    \"id\": 2,\r\n"
        		+ "    \"username\": \"Vivek2\",\r\n"
        		+ "    \"firstName\": \"Vivek2\",\r\n"
        		+ "    \"lastName\": \"swarup2\",\r\n"
        		+ "    \"email\": \"vivek2@oracle.com\",\r\n"
        		+ "    \"password\": \"vivpwd2\",\r\n"
        		+ "    \"phone\": \"012\",\r\n"
        		+ "    \"userStatus\": 12\r\n"
        		+ "  }\r\n"
        		+ "]";
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
    public void test_AddUserWithLongFieldValues() throws IOException, ParseException {
    	PetStoreUtil.info("executing test_AddUserWithLongFieldValues");
    	StringBuffer baseurl = new StringBuffer(url);
        String addURI = baseurl.append("/v2/user/createWithArray").toString();
        
        HttpHeaders headers = new HttpHeaders();        
        headers.add("Content-Type", "application/json");
          
        PetStoreUtil.info("calling URL :"+addURI);
        PetStoreUtil.info("rest api call type :"+HttpMethod.POST);
        String jsonBody = "[\r\n"
        		+ "  {\r\n"
        		+ "    \"id\": 1,\r\n"
        		+ "    \"username\": \"Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1\",\r\n"
        		+ "    \"firstName\": \"Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1Vivek1\",\r\n"
        		+ "    \"lastName\": \"swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1swarup1\",\r\n"
        		+ "    \"email\": \"vivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivek@oracle1.com\",\r\n"
        		+ "    \"password\": \"vivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekvivekpwd1\",\r\n"
        		+ "    \"phone\": \"011\",\r\n"
        		+ "    \"userStatus\": 11\r\n"
        		+ "  }\r\n"
        		+ "]";
        PetStoreUtil.info("body :\r\n" + jsonBody);
        HttpEntity entity = new HttpEntity(jsonBody, headers);
         
        response = this.restTemplate.postForEntity(addURI, entity, String.class);
        responseBody = response.getBody();
        PetStoreUtil.info("responseBody --->;" + responseBody);
        PetStoreUtil.info("Assert start" );
        Assert.assertEquals(response.getStatusCode().value(), HttpStatus.BAD_REQUEST.value(),"Actual Response Code should be 400 instead od success as payload has long field names and is client error:");
        PetStoreUtil.info("Assert completed" );
    }
	    
    @Test
    public void test_addSingleUserWithInValidEmailPattern() throws IOException, ParseException {
    	PetStoreUtil.info("executing test_addSingleUserWithInValidEmailPattern");
    	StringBuffer baseurl = new StringBuffer(url);
        String addURI = baseurl.append("/v2/user/createWithArray").toString();
        
        HttpHeaders headers = new HttpHeaders();        
        headers.add("Content-Type", "application/json");
          
        PetStoreUtil.info("Add URL :"+addURI);
        String jsonBody = "[\r\n"
        		+ "  {\r\n"
        		+ "    \"id\": 1,\r\n"
        		+ "    \"username\": \"Vivek1\",\r\n"
        		+ "    \"firstName\": \"Vivek1\",\r\n"
        		+ "    \"lastName\": \"swarup1\",\r\n"
        		+ "    \"email\": \"test@@gmail.com\",\r\n"
        		+ "    \"password\": \"vivpwd1\",\r\n"
        		+ "    \"phone\": \"011\",\r\n"
        		+ "    \"userStatus\": 9\r\n"
        		+ "  }\r\n"
        		+ "]";
        System.out.println("\n\n" + jsonBody);
        HttpEntity entity = new HttpEntity(jsonBody, headers);
        response = this.restTemplate.postForEntity(addURI, entity, String.class);
        responseBody = response.getBody();
        PetStoreUtil.info("responseBody --->;" + responseBody);
        PetStoreUtil.info("Assert start" );
        Assert.assertEquals(response.getStatusCode().value(), HttpStatus.BAD_REQUEST.value(),"Actual Response Code should be 400 as payload has invalid emailpattern and is client error:");
        PetStoreUtil.info("Assert completed" );
    }
	    
	    @Test
    public void test_BulkUserCreation() throws IOException, ParseException {
	    
	    PetStoreUtil.info("executing test_BulkUserCreation");
    	StringBuffer baseurl = new StringBuffer(url);
        String addURI = baseurl.append("/v2/user/createWithArray").toString();
        
        HttpHeaders headers = new HttpHeaders();        
        headers.add("Content-Type", "application/json");
          
        PetStoreUtil.info("calling URL :"+addURI);
        PetStoreUtil.info("rest api call type :"+HttpMethod.POST);
        String jsonBody = "[\r\n"
        		+ "  {\r\n"
        		+ "    \"id\": 1,\r\n"
        		+ "    \"username\": \"Vivek1\",\r\n"
        		+ "    \"firstName\": \"Vivek1\",\r\n"
        		+ "    \"lastName\": \"swarup1\",\r\n"
        		+ "    \"email\": \"\",\r\n"
        		+ "    \"password\": \"vivpwd1\",\r\n"
        		+ "    \"phone\": \"011-74118710160845345309485038453\",\r\n"
        		+ "    \"userStatus\": 9\r\n"
        		+ "  },\r\n"
        		+ "  {\r\n"
        		+ "    \"id\": 1,\r\n"
        		+ "    \"username\": \"Vivek1\",\r\n"
        		+ "    \"firstName\": \"Vivek1\",\r\n"
        		+ "    \"lastName\": \"swarup1\",\r\n"
        		+ "    \"email\": \"\",\r\n"
        		+ "    \"password\": \"vivpwd1\",\r\n"
        		+ "    \"phone\": \"011-74118710160845345309485038453\",\r\n"
        		+ "    \"userStatus\": 9\r\n"
        		+ "  },\r\n"
        		+ "  {\r\n"
        		+ "    \"id\": 1,\r\n"
        		+ "    \"username\": \"Vivek1\",\r\n"
        		+ "    \"firstName\": \"Vivek1\",\r\n"
        		+ "    \"lastName\": \"swarup1\",\r\n"
        		+ "    \"email\": \"\",\r\n"
        		+ "    \"password\": \"vivpwd1\",\r\n"
        		+ "    \"phone\": \"011-74118710160845345309485038453\",\r\n"
        		+ "    \"userStatus\": 9\r\n"
        		+ "  },\r\n"
        		+ "  {\r\n"
        		+ "    \"id\": 1,\r\n"
        		+ "    \"username\": \"Vivek1\",\r\n"
        		+ "    \"firstName\": \"Vivek1\",\r\n"
        		+ "    \"lastName\": \"swarup1\",\r\n"
        		+ "    \"email\": \"\",\r\n"
        		+ "    \"password\": \"vivpwd1\",\r\n"
        		+ "    \"phone\": \"011-74118710160845345309485038453\",\r\n"
        		+ "    \"userStatus\": 9\r\n"
        		+ "  },\r\n"
        		+ "  {\r\n"
        		+ "    \"id\": 1,\r\n"
        		+ "    \"username\": \"Vivek1\",\r\n"
        		+ "    \"firstName\": \"Vivek1\",\r\n"
        		+ "    \"lastName\": \"swarup1\",\r\n"
        		+ "    \"email\": \"\",\r\n"
        		+ "    \"password\": \"vivpwd1\",\r\n"
        		+ "    \"phone\": \"011-74118710160845345309485038453\",\r\n"
        		+ "    \"userStatus\": 9\r\n"
        		+ "  },\r\n"
        		+ "  {\r\n"
        		+ "    \"id\": 1,\r\n"
        		+ "    \"username\": \"Vivek1\",\r\n"
        		+ "    \"firstName\": \"Vivek1\",\r\n"
        		+ "    \"lastName\": \"swarup1\",\r\n"
        		+ "    \"email\": \"\",\r\n"
        		+ "    \"password\": \"vivpwd1\",\r\n"
        		+ "    \"phone\": \"011-74118710160845345309485038453\",\r\n"
        		+ "    \"userStatus\": 9\r\n"
        		+ "  },\r\n"
        		+ "  {\r\n"
        		+ "    \"id\": 1,\r\n"
        		+ "    \"username\": \"Vivek1\",\r\n"
        		+ "    \"firstName\": \"Vivek1\",\r\n"
        		+ "    \"lastName\": \"swarup1\",\r\n"
        		+ "    \"email\": \"\",\r\n"
        		+ "    \"password\": \"vivpwd1\",\r\n"
        		+ "    \"phone\": \"011-74118710160845345309485038453\",\r\n"
        		+ "    \"userStatus\": 9\r\n"
        		+ "  },\r\n"
        		+ "  {\r\n"
        		+ "    \"id\": 1,\r\n"
        		+ "    \"username\": \"Vivek1\",\r\n"
        		+ "    \"firstName\": \"Vivek1\",\r\n"
        		+ "    \"lastName\": \"swarup1\",\r\n"
        		+ "    \"email\": \"\",\r\n"
        		+ "    \"password\": \"vivpwd1\",\r\n"
        		+ "    \"phone\": \"011-74118710160845345309485038453\",\r\n"
        		+ "    \"userStatus\": 9\r\n"
        		+ "  },\r\n"
        		+ "  {\r\n"
        		+ "    \"id\": 1,\r\n"
        		+ "    \"username\": \"Vivek1\",\r\n"
        		+ "    \"firstName\": \"Vivek1\",\r\n"
        		+ "    \"lastName\": \"swarup1\",\r\n"
        		+ "    \"email\": \"\",\r\n"
        		+ "    \"password\": \"vivpwd1\",\r\n"
        		+ "    \"phone\": \"011-74118710160845345309485038453\",\r\n"
        		+ "    \"userStatus\": 9\r\n"
        		+ "  },\r\n"
        		+ "  {\r\n"
        		+ "    \"id\": 1,\r\n"
        		+ "    \"username\": \"Vivek1\",\r\n"
        		+ "    \"firstName\": \"Vivek1\",\r\n"
        		+ "    \"lastName\": \"swarup1\",\r\n"
        		+ "    \"email\": \"\",\r\n"
        		+ "    \"password\": \"vivpwd1\",\r\n"
        		+ "    \"phone\": \"011-74118710160845345309485038453\",\r\n"
        		+ "    \"userStatus\": 9\r\n"
        		+ "  },\r\n"
        		+ "  {\r\n"
        		+ "    \"id\": 1,\r\n"
        		+ "    \"username\": \"Vivek1\",\r\n"
        		+ "    \"firstName\": \"Vivek1\",\r\n"
        		+ "    \"lastName\": \"swarup1\",\r\n"
        		+ "    \"email\": \"\",\r\n"
        		+ "    \"password\": \"vivpwd1\",\r\n"
        		+ "    \"phone\": \"011-74118710160845345309485038453\",\r\n"
        		+ "    \"userStatus\": 9\r\n"
        		+ "  },\r\n"
        		+ "  {\r\n"
        		+ "    \"id\": 1,\r\n"
        		+ "    \"username\": \"Vivek1\",\r\n"
        		+ "    \"firstName\": \"Vivek1\",\r\n"
        		+ "    \"lastName\": \"swarup1\",\r\n"
        		+ "    \"email\": \"\",\r\n"
        		+ "    \"password\": \"vivpwd1\",\r\n"
        		+ "    \"phone\": \"011-74118710160845345309485038453\",\r\n"
        		+ "    \"userStatus\": 9\r\n"
        		+ "  },\r\n"
        		+ "  {\r\n"
        		+ "    \"id\": 1,\r\n"
        		+ "    \"username\": \"Vivek1\",\r\n"
        		+ "    \"firstName\": \"Vivek1\",\r\n"
        		+ "    \"lastName\": \"swarup1\",\r\n"
        		+ "    \"email\": \"\",\r\n"
        		+ "    \"password\": \"vivpwd1\",\r\n"
        		+ "    \"phone\": \"011-74118710160845345309485038453\",\r\n"
        		+ "    \"userStatus\": 9\r\n"
        		+ "  },\r\n"
        		+ "  {\r\n"
        		+ "    \"id\": 1,\r\n"
        		+ "    \"username\": \"Vivek1\",\r\n"
        		+ "    \"firstName\": \"Vivek1\",\r\n"
        		+ "    \"lastName\": \"swarup1\",\r\n"
        		+ "    \"email\": \"\",\r\n"
        		+ "    \"password\": \"vivpwd1\",\r\n"
        		+ "    \"phone\": \"011-74118710160845345309485038453\",\r\n"
        		+ "    \"userStatus\": 9\r\n"
        		+ "  },\r\n"
        		+ "  {\r\n"
        		+ "    \"id\": 1,\r\n"
        		+ "    \"username\": \"Vivek1\",\r\n"
        		+ "    \"firstName\": \"Vivek1\",\r\n"
        		+ "    \"lastName\": \"swarup1\",\r\n"
        		+ "    \"email\": \"\",\r\n"
        		+ "    \"password\": \"vivpwd1\",\r\n"
        		+ "    \"phone\": \"011-74118710160845345309485038453\",\r\n"
        		+ "    \"userStatus\": 9\r\n"
        		+ "  },\r\n"
        		+ "  {\r\n"
        		+ "    \"id\": 1,\r\n"
        		+ "    \"username\": \"Vivek1\",\r\n"
        		+ "    \"firstName\": \"Vivek1\",\r\n"
        		+ "    \"lastName\": \"swarup1\",\r\n"
        		+ "    \"email\": \"\",\r\n"
        		+ "    \"password\": \"vivpwd1\",\r\n"
        		+ "    \"phone\": \"011-74118710160845345309485038453\",\r\n"
        		+ "    \"userStatus\": 9\r\n"
        		+ "  },\r\n"
        		+ "  {\r\n"
        		+ "    \"id\": 1,\r\n"
        		+ "    \"username\": \"Vivek1\",\r\n"
        		+ "    \"firstName\": \"Vivek1\",\r\n"
        		+ "    \"lastName\": \"swarup1\",\r\n"
        		+ "    \"email\": \"\",\r\n"
        		+ "    \"password\": \"vivpwd1\",\r\n"
        		+ "    \"phone\": \"011-74118710160845345309485038453\",\r\n"
        		+ "    \"userStatus\": 9\r\n"
        		+ "  },\r\n"
        		+ "  {\r\n"
        		+ "    \"id\": 1,\r\n"
        		+ "    \"username\": \"Vivek1\",\r\n"
        		+ "    \"firstName\": \"Vivek1\",\r\n"
        		+ "    \"lastName\": \"swarup1\",\r\n"
        		+ "    \"email\": \"\",\r\n"
        		+ "    \"password\": \"vivpwd1\",\r\n"
        		+ "    \"phone\": \"011-74118710160845345309485038453\",\r\n"
        		+ "    \"userStatus\": 9\r\n"
        		+ "  },\r\n"
        		+ "  {\r\n"
        		+ "    \"id\": 1,\r\n"
        		+ "    \"username\": \"Vivek1\",\r\n"
        		+ "    \"firstName\": \"Vivek1\",\r\n"
        		+ "    \"lastName\": \"swarup1\",\r\n"
        		+ "    \"email\": \"\",\r\n"
        		+ "    \"password\": \"vivpwd1\",\r\n"
        		+ "    \"phone\": \"011-74118710160845345309485038453\",\r\n"
        		+ "    \"userStatus\": 9\r\n"
        		+ "  },\r\n"
        		+ "  {\r\n"
        		+ "    \"id\": 1,\r\n"
        		+ "    \"username\": \"Vivek1\",\r\n"
        		+ "    \"firstName\": \"Vivek1\",\r\n"
        		+ "    \"lastName\": \"swarup1\",\r\n"
        		+ "    \"email\": \"\",\r\n"
        		+ "    \"password\": \"vivpwd1\",\r\n"
        		+ "    \"phone\": \"011-74118710160845345309485038453\",\r\n"
        		+ "    \"userStatus\": 9\r\n"
        		+ "  },\r\n"
        		+ "  {\r\n"
        		+ "    \"id\": 1,\r\n"
        		+ "    \"username\": \"Vivek1\",\r\n"
        		+ "    \"firstName\": \"Vivek1\",\r\n"
        		+ "    \"lastName\": \"swarup1\",\r\n"
        		+ "    \"email\": \"\",\r\n"
        		+ "    \"password\": \"vivpwd1\",\r\n"
        		+ "    \"phone\": \"011-74118710160845345309485038453\",\r\n"
        		+ "    \"userStatus\": 9\r\n"
        		+ "  },\r\n"
        		+ "v,\r\n"
        		+ "  {\r\n"
        		+ "    \"id\": 1,\r\n"
        		+ "    \"username\": \"Vivek1\",\r\n"
        		+ "    \"firstName\": \"Vivek1\",\r\n"
        		+ "    \"lastName\": \"swarup1\",\r\n"
        		+ "    \"email\": \"\",\r\n"
        		+ "    \"password\": \"vivpwd1\",\r\n"
        		+ "    \"phone\": \"011-74118710160845345309485038453\",\r\n"
        		+ "    \"userStatus\": 9\r\n"
        		+ "  },\r\n"
        		+ "  {\r\n"
        		+ "    \"id\": 1,\r\n"
        		+ "    \"username\": \"Vivek1\",\r\n"
        		+ "    \"firstName\": \"Vivek1\",\r\n"
        		+ "    \"lastName\": \"swarup1\",\r\n"
        		+ "    \"email\": \"\",\r\n"
        		+ "    \"password\": \"vivpwd1\",\r\n"
        		+ "    \"phone\": \"011-74118710160845345309485038453\",\r\n"
        		+ "    \"userStatus\": 9\r\n"
        		+ "  },\r\n"
        		+ "  {\r\n"
        		+ "    \"id\": 1,\r\n"
        		+ "    \"username\": \"Vivek1\",\r\n"
        		+ "    \"firstName\": \"Vivek1\",\r\n"
        		+ "    \"lastName\": \"swarup1\",\r\n"
        		+ "    \"email\": \"\",\r\n"
        		+ "    \"password\": \"vivpwd1\",\r\n"
        		+ "    \"phone\": \"011-74118710160845345309485038453\",\r\n"
        		+ "    \"userStatus\": 9\r\n"
        		+ "  },\r\n"
        		+ "  {\r\n"
        		+ "    \"id\": 1,\r\n"
        		+ "    \"username\": \"Vivek1\",\r\n"
        		+ "    \"firstName\": \"Vivek1\",\r\n"
        		+ "    \"lastName\": \"swarup1\",\r\n"
        		+ "    \"email\": \"\",\r\n"
        		+ "    \"password\": \"vivpwd1\",\r\n"
        		+ "    \"phone\": \"011-74118710160845345309485038453\",\r\n"
        		+ "    \"userStatus\": 9\r\n"
        		+ "  },\r\n"
        		+ "  {\r\n"
        		+ "    \"id\": 1,\r\n"
        		+ "    \"username\": \"Vivek1\",\r\n"
        		+ "    \"firstName\": \"Vivek1\",\r\n"
        		+ "    \"lastName\": \"swarup1\",\r\n"
        		+ "    \"email\": \"\",\r\n"
        		+ "    \"password\": \"vivpwd1\",\r\n"
        		+ "    \"phone\": \"011-74118710160845345309485038453\",\r\n"
        		+ "    \"userStatus\": 9\r\n"
        		+ "  }\r\n"
        		+ "]";
        PetStoreUtil.info("body :\r\n" + jsonBody);
        HttpEntity entity = new HttpEntity(jsonBody, headers);
        HttpStatus httpStatus = null; 
        //POST Method to Add New Employee
        try {
        response = this.restTemplate.postForEntity(addURI, entity, String.class);
        responseBody = response.getBody();
        }catch(HttpServerErrorException e)
        {
        	PetStoreUtil.error("HTTP response code :"+e.getStatusCode().value());
        	httpStatus = e.getStatusCode();
        }
        
        responseBody= (response != null ) && (response.getBody() != null )  ? response.getBody() :"" ;
        PetStoreUtil.info("responseBody --->;" + responseBody);
        Assert.assertEquals(httpStatus.value(), HttpStatus.PAYLOAD_TOO_LARGE.value(),"Actual Response Code should be 413 as payload is too large and is client error :");
        PetStoreUtil.info("Assert completed" );
    }
	    
    @Test
    public void test_addSingleUserWithInvalidPhoneNo() throws IOException, ParseException {
    	PetStoreUtil.info("executing test_addSingleUserWithInvalidPhoneNo");
    	StringBuffer baseurl = new StringBuffer(url);
        String addURI = baseurl.append("/v2/user/createWithArray").toString();
        
        HttpHeaders headers = new HttpHeaders();        
        headers.add("Content-Type", "application/json");
          
        PetStoreUtil.info("calling URL :"+addURI);
        PetStoreUtil.info("rest api call type :"+HttpMethod.POST);
        String jsonBody = "[\r\n"
        		+ "  {\r\n"
        		+ "    \"id\": 1,\r\n"
        		+ "    \"username\": \"Vivek1\",\r\n"
        		+ "    \"firstName\": \"Vivek1\",\r\n"
        		+ "    \"lastName\": \"swarup1\",\r\n"
        		+ "    \"email\": \"\",\r\n"
        		+ "    \"password\": \"vivpwd1\",\r\n"
        		+ "    \"phone\": \"011-787686546666845345309485038453\",\r\n"
        		+ "    \"userStatus\": 9\r\n"
        		+ "  }\r\n"
        		+ "]";
        PetStoreUtil.info("body :\r\n" + jsonBody);
        HttpEntity entity = new HttpEntity(jsonBody, headers);
        response = this.restTemplate.postForEntity(addURI, entity, String.class);
        responseBody = response.getBody();
        PetStoreUtil.info("responseBody --->;" + responseBody);
        PetStoreUtil.info("Assert start" );
        Assert.assertEquals(response.getStatusCode().value(), HttpStatus.BAD_REQUEST.value(),"Actual Response Code should be 400 as payload has invalid phone and is client error : ");
        PetStoreUtil.info("Assert completed" );
    }
	    
	    @Test
	    public void test_addSingleUserWithinvalidId() throws IOException, ParseException {
	    	PetStoreUtil.info("executing test_addSingleUserWithinvalidId");
	    	StringBuffer baseurl = new StringBuffer(url);
	        String addURI = baseurl.append("/v2/user/createWithArray").toString();
	        
	        HttpHeaders headers = new HttpHeaders();        
	        headers.add("Content-Type", "application/json");
	          
	        PetStoreUtil.info("calling URL :"+addURI);
	        PetStoreUtil.info("rest api call type :"+HttpMethod.POST);
	        String jsonBody = "[\r\n"
	        		+ "  {\r\n"
	        		+ "    \"id\": 1@23%342,\r\n"
	        		+ "    \"username\": \"Vivek1\",\r\n"
	        		+ "    \"firstName\": \"Vivek1\",\r\n"
	        		+ "    \"lastName\": \"swarup1\",\r\n"
	        		+ "    \"email\": \"\",\r\n"
	        		+ "    \"password\": \"vivpwd1\",\r\n"
	        		+ "    \"phone\": \"011-74123422335\",\r\n"
	        		+ "    \"userStatus\": 9\r\n"
	        		+ "  }\r\n"
	        		+ "\r\n"
	        		+ "]";
	        PetStoreUtil.info("body :\r\n" + jsonBody);
	        HttpEntity entity = new HttpEntity(jsonBody, headers);
	        HttpStatus httpStatus = null; 
	        //POST Method to Add New Employee
	        try {
	        response = this.restTemplate.postForEntity(addURI, entity, String.class);
	        responseBody = response.getBody();
	        }catch(HttpServerErrorException e)
	        {
	        	PetStoreUtil.error("User with invalid id no should not be added");
	        	httpStatus = e.getStatusCode();
	        }
	        
	        responseBody = (response != null ) && (response.getBody() != null )  ? response.getBody() :"" ;
	        PetStoreUtil.info("responseBody --->;" + responseBody);
	        PetStoreUtil.info("Assert start" );
	        Assert.assertEquals(httpStatus.value(), HttpStatus.BAD_REQUEST.value(),"Actual Response Code should be 400 as payload has invalid userId and is client error:");
	        PetStoreUtil.info("Assert completed" );
	    }
	    
	    @Test
	    public void test_addSinglleuserWihtInvalidUserStatus() throws IOException, ParseException {
	    	PetStoreUtil.info("executing test_addSinglleuserWihtInvalidUserStatus");
	    	StringBuffer baseurl = new StringBuffer(url);
	        String addURI = baseurl.append("/v2/user/createWithArray").toString();
	        
	        HttpHeaders headers = new HttpHeaders();        
	        headers.add("Content-Type", "application/json");	          
	        PetStoreUtil.info("calling URL :"+addURI);
	        PetStoreUtil.info("rest api call type :"+HttpMethod.POST);
	        String jsonBody = "[\r\n"
	        		+ "  {\r\n"
	        		+ "    \"id\": 123,\r\n"
	        		+ "    \"username\": \"Vivek1\",\r\n"
	        		+ "    \"firstName\": \"Vivek1\",\r\n"
	        		+ "    \"lastName\": \"swarup1\",\r\n"
	        		+ "    \"email\": \"test@gmail.com\",\r\n"
	        		+ "    \"password\": \"vivpwd1\",\r\n"
	        		+ "    \"phone\": \"011-74123422335\",\r\n"
	        		+ "    \"userStatus\": -1\r\n"
	        		+ "  }\r\n"
	        		+ "\r\n"
	        		+ "]";
	        System.out.println("\n\n" + jsonBody);
	        HttpEntity entity = new HttpEntity(jsonBody, headers);
	        response = this.restTemplate.postForEntity(addURI, entity, String.class);
	        responseBody = response.getBody();
	        PetStoreUtil.info("responseBody --->;" + responseBody);
	        PetStoreUtil.info("Assert start" );
	        Assert.assertEquals(response.getStatusCode().value(), HttpStatus.BAD_REQUEST.value(),"The processing should fail with actual Response Code 400 as payload has invalid userstatus and is client error");
	        PetStoreUtil.info("Assert completed" );
	    }
	    
	    
}
