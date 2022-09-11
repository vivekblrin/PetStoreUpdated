package com.petStore.test.nonfuctional;
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
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.petStore.test.utils.PetStoreUtil;
public class UpdatePetStatusNonFunctional {
	
	   private String responseBody;
	      
	    //RESTTemplate Object
	    private RestTemplate restTemplate;
	     
	    // Create Response Entity - Stores HTTPStatus Code, Response Body, etc
	    ResponseEntity<String> response ;
	    
		private String url = "";
	
	    @BeforeSuite
	    public void loadProperties() throws IOException, ParseException {
	    	PetStoreUtil.info("Calling  UpdatePetStatusNonFunctional.loadProperties()");
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
 	   public void test_updateNonExistencePet() throws IOException, ParseException {
 	        
    	    PetStoreUtil.info("executing test_updateNonExistencePet");
    	    StringBuffer baseurl = new StringBuffer(url);
 	        String addURI = baseurl.append("/v2/pet/").append("1122334455").toString();
 	        
 	        HttpHeaders headers = new HttpHeaders();
 	        HttpEntity entity = new HttpEntity(headers);
	        headers.add("Content-Type", "application/json");
	        headers.add("api_key", "1122334455");
	        PetStoreUtil.info("calling URL :"+addURI);
	        PetStoreUtil.info("rest api call type :"+HttpMethod.DELETE);
	        try {
	    	response = this.restTemplate.exchange(addURI,HttpMethod.DELETE, entity, String.class);
	        }
	        catch(HttpClientErrorException e)
            {
           	 PetStoreUtil.error("HTTP response code :" + e.getStackTrace().toString());
            }
	        
	        baseurl = new StringBuffer(url);
// 	    	URI = new StringBuffer("/v2/pet/");
 	        addURI = baseurl.append(("/v2/pet/")).toString();
 	        headers = new HttpHeaders();        
 	        headers.add("Content-Type", "application/json");
 	        PetStoreUtil.info("calling URL :"+addURI);
 	        PetStoreUtil.info("rest api call type :"+HttpMethod.PUT);
 	        String jsonBody = "{\r\n"
 	        		+ "  \"id\": 1122334455,\r\n"
 	        		+ "  \"category\": {\r\n"
 	        		+ "    \"id\": 21,\r\n"
 	        		+ "    \"name\": \"testCategoryUpdated\"\r\n"
 	        		+ "  },\r\n"
 	        		+ "  \"name\": \"NonExistingPet\",\r\n"
 	        		+ "  \"photoUrls\": [\r\n"
 	        		+ "    \"ww.test.com/naupdated\"\r\n"
 	        		+ "  ],\r\n"
 	        		+ "  \"tags\": [\r\n"
 	        		+ "    {\r\n"
 	        		+ "      \"id\": 31,\r\n"
 	        		+ "      \"name\": \"testTags1Updated\"\r\n"
 	        		+ "    }\r\n"
 	        		+ "  ],\r\n"
 	        		+ "  \"status\": \"available\"\r\n"
 	        		+ "}";
 	       PetStoreUtil.info("body :\r\n" + jsonBody);
 	       entity = new HttpEntity(jsonBody, headers);
	        try {
	        	response = this.restTemplate.exchange(addURI,HttpMethod.PUT, entity, String.class);
	           }catch(HttpClientErrorException e)
	           {
	           	PetStoreUtil.error("HTTP response code :" + e.getStackTrace().toString());
	           }
 	        responseBody = (response != null ) && (response.getBody() != null )  ? response.getBody() :"" ;
 	        PetStoreUtil.info("responseBody --->;" + responseBody);
 	        PetStoreUtil.info("Assert start" );
 	        Assert.assertEquals(response.getStatusCode().value(), HttpStatus.NOT_FOUND.value(),"Processing should fail with Error Code 404 as Payload has an non existent object :");
 	        PetStoreUtil.info("Assert completed" );
 		 }
      
}
