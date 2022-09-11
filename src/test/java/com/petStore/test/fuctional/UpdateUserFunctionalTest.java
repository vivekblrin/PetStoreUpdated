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
import org.springframework.web.client.RestTemplate;
 
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.petStore.test.utils.PetStoreUtil;

public class UpdateUserFunctionalTest {

	    private String responseBody;
	    
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
	    	PetStoreUtil.info("Calling  UpdateUserFunctionalTest.beforeTest()");
	    	PetStoreUtil.info("Creating RestTemplate object before tests");
	        this.restTemplate = new RestTemplate(); 
	    }
	    
	    @AfterTest
	    public void afterTest() {
	    	PetStoreUtil.info("Calling  UpdateUserFunctionalTest.afterTest()");
	    	PetStoreUtil.info("Resetting response and responseBody object");
	        response = null;
	        responseBody = null;
	    }
	    
        @BeforeClass
	    public void createUser() throws IOException, ParseException {
	    	
        	PetStoreUtil.info("executing createUser");
        	StringBuffer baseurl = new StringBuffer(url);
	        String addURI = baseurl.append("/v2/user/createWithArray").toString();
	        
	        HttpHeaders headers = new HttpHeaders();        
	        headers.add("Content-Type", "application/json");
	          
	        PetStoreUtil.info("calling URL :"+addURI);
	        PetStoreUtil.info("rest api call type :"+HttpMethod.POST);
	        String jsonBody = "[\r\n"
	        		+ "  {\r\n"
	        		+ "    \"id\": 1234,\r\n"
	        		+ "    \"username\": \"vivek\",\r\n"
	        		+ "    \"firstName\": \"vivek\",\r\n"
	        		+ "    \"lastName\": \"vivek\",\r\n"
	        		+ "    \"email\": \"test@gmail.com\",\r\n"
	        		+ "    \"password\": \"pwd\",\r\n"
	        		+ "    \"phone\": \"7564545644\",\r\n"
	        		+ "    \"userStatus\": 12\r\n"
	        		+ "  }\r\n"
	        		+ "]";
	        System.out.println("\n\n" + jsonBody);
	        HttpEntity entity = new HttpEntity(jsonBody, headers);

	        response = this.restTemplate.postForEntity(addURI, entity, String.class);
	        responseBody = response.getBody();
	        PetStoreUtil.info("responseBody --->;" + responseBody);
	    }
	    
	    @Test 
	    public void test_updateUsewithValidValues() throws IOException, ParseException {
	    	
	    	PetStoreUtil.info("executing test_updateUsewithValidValues");
	    	StringBuffer baseurl = new StringBuffer(url);
	    	String addURI = baseurl.append("/v2/user/").append("vivek").toString();
	        PetStoreUtil.info("calling URL :"+addURI);
	        PetStoreUtil.info("rest api call type :"+HttpMethod.PUT);
	        String jsonBody = "{\r\n"
		        		+ "    \"id\": 1234,\r\n"
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
	        
	        baseurl = new StringBuffer(url);
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
            Assert.assertEquals(userName,"vivekUpdated","userName was updated sucessfully");
            Assert.assertEquals(firstName,"vivekUpdated","firstName was updated sucessfully");
            Assert.assertEquals(lastName,"vivekUpdated","lastName was updated sucessfully");
            Assert.assertEquals(email,"test1@gmail.com","email was updated sucessfully");
            Assert.assertEquals(password,"pwd1","password was updated sucessfully");
            Assert.assertEquals(phone,"7564545644999","phone was updated sucessfully");
            Assert.assertEquals(userStatus,"1234","userStatus was updated sucessfully");
            PetStoreUtil.info("Assert completed" );
	    }
	    
	    @Test
	    public void test_updateUserWithLongfieldNames() throws IOException, ParseException {
	    	
	    	PetStoreUtil.info("executing test_updateUserWithLongfieldNames");
	    	StringBuffer baseurl = new StringBuffer(url);
	    	String addURI = baseurl.append("/v2/user/").append("vivek").toString();
	    	
	        PetStoreUtil.info("calling URL :"+addURI);
	        PetStoreUtil.info("rest api call type :"+HttpMethod.PUT);
	        String jsonBody = "{\r\n"
		        		+ "    \"id\": 1234,\r\n"
		        		+ "    \"username\": \"vivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdated\",\r\n"
		        		+ "    \"firstName\": \"vivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdated\",\r\n"
		        		+ "    \"lastName\": \"vivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdatedvivekUpdated\",\r\n"
		        		+ "    \"email\": \"test@gmail.com\",\r\n"
		        		+ "    \"password\": \"pwd\",\r\n"
		        		+ "    \"phone\": \"7564545644\",\r\n"
		        		+ "    \"userStatus\": 12\r\n"
		        		+ "  }\r\n";
	        
	        PetStoreUtil.info("body :\r\n" + jsonBody);
	        
	        HttpHeaders headers = new HttpHeaders();        
	        headers.add("Content-Type", "application/json");
	        HttpEntity entity = new HttpEntity(jsonBody, headers);
	        
	        response = restTemplate.exchange(addURI, HttpMethod.PUT, entity, String.class);
	        responseBody = response.getBody().toString();
	        PetStoreUtil.info("responseBody --->;" + responseBody);
	        PetStoreUtil.info("Assert start" );        
	        Assert.assertEquals(response.getStatusCode().value(), HttpStatus.BAD_REQUEST.value(),"Actual Response Code should be 400 as payload has verylong usernameupdate and is client error:");
	        PetStoreUtil.info("Assert completed" ); 
	    }
	    
	    @Test 
	    public void test_updateUserWithinvalidEmailPattern() throws IOException, ParseException {
	    	
	    	PetStoreUtil.info("executing test_updateUserWithinvalidEmailPattern");
	    	StringBuffer baseurl = new StringBuffer(url);
	    	String addURI = baseurl.append("/v2/user/").append("vivek").toString();
	    	
	        PetStoreUtil.info("calling URL :"+addURI);
	        PetStoreUtil.info("rest api call type :"+HttpMethod.PUT);
	        String jsonBody = "{\r\n"
		        		+ "    \"id\": 1234,\r\n"
		        		+ "    \"username\": \"vivek\",\r\n"
		        		+ "    \"firstName\": \"vivek\",\r\n"
		        		+ "    \"lastName\": \"vivek\",\r\n"		        		
		        		+ "    \"email\": \"test@@gmail...com\",\r\n"
		        		+ "    \"password\": \"pwd\",\r\n"
		        		+ "    \"phone\": \"7564545644\",\r\n"
		        		+ "    \"userStatus\": 12\r\n"
		        		+ "  }\r\n";
	        
	        PetStoreUtil.info("body :\r\n" + jsonBody);
	        
	        HttpHeaders headers = new HttpHeaders();        
	        headers.add("Content-Type", "application/json");
	        HttpEntity entity = new HttpEntity(jsonBody, headers);
	        
	        response = restTemplate.exchange(addURI, HttpMethod.PUT, entity, String.class);
	        responseBody = response.getBody().toString();
	        PetStoreUtil.info("responseBody --->;" + responseBody);   
	        PetStoreUtil.info("Assert start" );
	        Assert.assertEquals(response.getStatusCode().value(), HttpStatus.BAD_REQUEST.value(),"Actual Response Code should be 400 as payload has invaldemail Pattern and is client error:");
	        PetStoreUtil.info("Assert completed" );  
	    }
	    
	    @Test 
	    public void test_updateUserWithinvalidPhonePattern() throws IOException, ParseException {
	    
	    	PetStoreUtil.info("executing test_updateUserWithinvalidPhonePattern");
	    	StringBuffer baseurl = new StringBuffer(url);
	    	String addURI = baseurl.append("/v2/user/").append("vivek").toString();
	    	
	        PetStoreUtil.info("calling URL :"+addURI);
	        PetStoreUtil.info("rest api call type :"+HttpMethod.PUT);
	        String jsonBody = "{\r\n"
		        		+ "    \"id\": 1234,\r\n"
		        		+ "    \"username\": \"vivek\",\r\n"
		        		+ "    \"firstName\": \"vivek\",\r\n"
		        		+ "    \"lastName\": \"vivek\",\r\n"		        		
		        		+ "    \"email\": \"test@@gmail...com\",\r\n"
		        		+ "    \"password\": \"pwd\",\r\n"
		        		+ "    \"phone\": \"91-7564545644ABCSDF\",\r\n"
		        		+ "    \"userStatus\": 12\r\n"
		        		+ "  }\r\n";
	        
	        PetStoreUtil.info("body :\r\n" + jsonBody);
	        
	        HttpHeaders headers = new HttpHeaders();        
	        headers.add("Content-Type", "application/json");
	        HttpEntity entity = new HttpEntity(jsonBody, headers);
	        
	        response = restTemplate.exchange(addURI, HttpMethod.PUT, entity, String.class);
	        responseBody = response.getBody().toString();
	        PetStoreUtil.info("responseBody --->;" + responseBody);
	        PetStoreUtil.info("Assert start" );         
	        Assert.assertEquals(response.getStatusCode().value(), HttpStatus.BAD_REQUEST.value(),"Actual Response Code should be 400 as payload has invaldPhone Pattern and is client error:");
	        PetStoreUtil.info("Assert completed" );  
	    }
	    
	    @Test 
	    public void test_updateUseWithinvalidUserStatus() throws IOException, ParseException {
	    	
	    	PetStoreUtil.info("executing test_updateUseWithinvalidUserStatus");
	    	StringBuffer baseurl = new StringBuffer(url);
	    	String addURI = baseurl.append("/v2/user/").append("vivek").toString();
	    	
	        PetStoreUtil.info("calling URL :"+addURI);
	        PetStoreUtil.info("rest api call type :"+HttpMethod.PUT);
	        String jsonBody = "{\r\n"
		        		+ "    \"id\": 1234,\r\n"
		        		+ "    \"username\": \"vivek\",\r\n"
		        		+ "    \"firstName\": \"vivek\",\r\n"
		        		+ "    \"lastName\": \"vivek\",\r\n"		        		
		        		+ "    \"email\": \"test@@gmail...com\",\r\n"
		        		+ "    \"password\": \"pwd\",\r\n"
		        		+ "    \"phone\": \"91-7564545644ABCSDF\",\r\n"
		        		+ "    \"userStatus\": -1\r\n"
		        		+ "  }\r\n";
	        
	        PetStoreUtil.info("body :\r\n" + jsonBody);
	        
	        HttpHeaders headers = new HttpHeaders();        
	        headers.add("Content-Type", "application/json");
	        HttpEntity entity = new HttpEntity(jsonBody, headers);
	        
	        response = restTemplate.exchange(addURI, HttpMethod.PUT, entity, String.class);
	        responseBody = response.getBody().toString();
	        PetStoreUtil.info("responseBody --->;" + responseBody);
	        PetStoreUtil.info("Assert start" );        
	        Assert.assertEquals(response.getStatusCode().value(), HttpStatus.BAD_REQUEST.value(),"Actual Response Code should be 400 as payload has invaldUserStatus Pattern and is client error:");
	        PetStoreUtil.info("Assert completed" );
	    }
	    	    
}
