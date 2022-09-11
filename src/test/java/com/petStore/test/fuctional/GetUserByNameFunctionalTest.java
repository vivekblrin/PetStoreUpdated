package com.petStore.test.fuctional;

import java.io.IOException;
import java.text.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
import org.testng.log4testng.Logger;

import com.petStore.test.utils.PetStoreUtil;

public class GetUserByNameFunctionalTest {

	   private String responseBody;    
	    
	    //RESTTemplate Object
	   private RestTemplate restTemplate;
	    
	    // Create Response Entity - Stores HTTPStatus Code, Response Body, etc
	    ResponseEntity<String> response ;
	    
	    private String url;
	    
	    @BeforeSuite
	    public void loadProperties() throws IOException, ParseException {
	    	PetStoreUtil.info("Calling  GetUserByNameFunctionalTest.loadProperties()");
	    	PetStoreUtil reader = PetStoreUtil.getInstance("project.properties"); 
	    	url = reader.getProperty("petstore.url");
	    }
	    
	    @BeforeTest
	    public void beforeTest() throws IOException, ParseException {
	    	PetStoreUtil.info("Calling  GetUserByNameFunctionalTest.beforeTest()");
	    	PetStoreUtil.info("Creating RestTemplate object before tests");
	        this.restTemplate = new RestTemplate(); 
	    }
	    
	    @AfterTest
	    public void afterTest() {
	    	PetStoreUtil.info("Calling  GetUserByNameFunctionalTest.afterTest()");
	    	PetStoreUtil.info("Resetting response and responseBody object");
	        response = null;
	        responseBody = null;
	    }
	    
	    @BeforeTest
	    public void createUser() throws IOException, ParseException {
	    	
	    	PetStoreUtil.info("executing createUser");
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
	        Assert.assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
	    }
	    
	    @BeforeTest
	    public void createDuplicateUserRecord() throws IOException, ParseException {
	    	
	    	PetStoreUtil.info("executing createDuplicateUserRecord");
	    	StringBuffer baseurl = new StringBuffer(url);
	        String addURI = baseurl.append("/v2/user/createWithArray").toString();
	        
	        HttpHeaders headers = new HttpHeaders();        
	        headers.add("Content-Type", "application/json");
	          
	        PetStoreUtil.info("calling URL :"+addURI);
	        PetStoreUtil.info("rest api call type :"+HttpMethod.POST);
	        String jsonBody = "[\r\n"
	        		+ "  {\r\n"
	        		+ "    \"id\": 1,\r\n"
	        		+ "    \"username\": \"Vivek\",\r\n"
	        		+ "    \"firstName\": \"Vivek\",\r\n"
	        		+ "    \"lastName\": \"swarup\",\r\n"
	        		+ "    \"email\": \"vivek@oracle1.com\",\r\n"
	        		+ "    \"password\": \"vivpwd\",\r\n"
	        		+ "    \"phone\": \"011\",\r\n"
	        		+ "    \"userStatus\": 11\r\n"
	        		+ "  },\r\n"
	        		+ "  {\r\n"
	        		+ "    \"id\": 2,\r\n"
	        		+ "    \"username\": \"Vivek\",\r\n"
	        		+ "    \"firstName\": \"Vivek\",\r\n"
	        		+ "    \"lastName\": \"swarup\",\r\n"
	        		+ "    \"email\": \"vivek@oracle.com\",\r\n"
	        		+ "    \"password\": \"vivpwd\",\r\n"
	        		+ "    \"phone\": \"011\",\r\n"
	        		+ "    \"userStatus\": 11\r\n"
	        		+ "  }\r\n"
	        		+ "]";
	        PetStoreUtil.info("body :\r\n" + jsonBody);
	        HttpEntity entity = new HttpEntity(jsonBody, headers);
	        response = this.restTemplate.postForEntity(addURI, entity, String.class);
	        responseBody = response.getBody();
	        PetStoreUtil.info("responseBody --->;" + responseBody);
	        Assert.assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());

	    }
	    
	    @Test
	    public void test_getUserByUpdatedName() throws IOException, ParseException {
	    	
	    	PetStoreUtil.info("executing test_getUserByUpdatedName");
	    	StringBuffer baseurl = new StringBuffer(url);
	    	String addURI = baseurl.append("/v2/user/").append("Vivek").toString();
	    	
	    	PetStoreUtil.info("calling URL :"+addURI);
	    	PetStoreUtil.info("rest api call type :"+HttpMethod.PUT);
	        String jsonBody = "{\r\n"
		        		+ "    \"id\": 1,\r\n"
		        		+ "    \"username\": \"vivekUpdated\",\r\n"
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
	        responseBody = response.getBody().toString();
	        PetStoreUtil.info("responseBody --->;" + responseBody);
	        PetStoreUtil.info("Assert start" );
	        Assert.assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
	        PetStoreUtil.info("Assert completed" );
	        
//	    	baseurl = new StringBuffer("https://petstore.swagger.io");
	    	baseurl = new StringBuffer(url);
//	    	URI = new StringBuffer("/v2/user/").append("vivekUpdated");
	    	addURI = baseurl.append("/v2/user/").append("vivekUpdated").toString();
	    	
	    	PetStoreUtil.info("calling URL :"+addURI);
	    	PetStoreUtil.info("rest api call type :"+HttpMethod.GET);
	        response = restTemplate.getForEntity(addURI,String.class);
	           
	        responseBody = response.getBody().toString();
	        PetStoreUtil.info("responseBody --->;" + responseBody);                
	        String userName  = PetStoreUtil.getfieldValueFromResponse(responseBody , "username");
	        String firstName  = PetStoreUtil.getfieldValueFromResponse(responseBody , "firstName");
	        String lastName  = PetStoreUtil.getfieldValueFromResponse(responseBody , "lastName");
	        String email  = PetStoreUtil.getfieldValueFromResponse(responseBody , "email");
	        String password  = PetStoreUtil.getfieldValueFromResponse(responseBody , "password");
	        String phone  = PetStoreUtil.getfieldValueFromResponse(responseBody , "phone");
	        String userStatus  = PetStoreUtil.getfieldValueFromResponse(responseBody , "userStatus");
	        
	        PetStoreUtil.info("Assert start" );
	        
            Assert.assertEquals(userName,"vivekUpdated","Correct record was fetched");
            Assert.assertEquals(firstName,"vivekUpdated","Correct record was fetched");
            Assert.assertEquals(lastName,"vivekUpdated","Correct record was fetched");
            Assert.assertEquals(email,"test1@gmail.com","Correct record was fetched");
            Assert.assertEquals(password,"pwd1","Correct record was fetched");
            Assert.assertEquals(phone,"7564545644999","Correct record was fetched");
            Assert.assertEquals(userStatus,"1234","Correct record was fetched");
	        
            PetStoreUtil.info("Assert completed" );
	    }
	    
	    @Test
	    public void test_getUserforDuplicateRecords() throws IOException, ParseException {
	    	
	    	PetStoreUtil.info("executing test_getUserforDuplicateRecords");
	    	StringBuffer baseurl = new StringBuffer(url);
	        String addURI = baseurl.append("/v2/user/").append("Vivek").toString();
	    	
	        PetStoreUtil.info("calling URL :"+addURI);
	        PetStoreUtil.info("rest api call type :"+HttpMethod.GET);
	        System.out.println("Calling get User : "+ HttpMethod.GET +" :" +addURI); 
	        PetStoreUtil.info("URL :"+addURI);
	        response = restTemplate.getForEntity(addURI,String.class);
	           
	        responseBody = response.getBody().toString();
	        PetStoreUtil.info("responseBody --->;" + responseBody);            
	        boolean isList  = PetStoreUtil.getfieldValueArrayResponse(response);

	        PetStoreUtil.info("Assert start" );     
	        
            Assert.assertTrue(isList,"List Should be fetched as there are 2 records :");
            
            PetStoreUtil.info("Assert completed" );
	    }
	    	    
}
