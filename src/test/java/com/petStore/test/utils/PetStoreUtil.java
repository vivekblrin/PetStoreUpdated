package com.petStore.test.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.ResponseEntity;
public class PetStoreUtil {
    //Initialize Log4j instance
    private static final Logger Log =  LogManager.getLogger(PetStoreUtil.class);
    private String url = "";
    //Info Level Logs
    public static void info (String message) {
        Log.info(message);
    }
    //Warn Level Logs
    public static void warn (String message) {
        Log.warn(message);
    }
    //Error Level Logs
    public static void error (String message) {
        Log.error(message);
    }
    //Fatal Level Logs
    public static void fatal (String message) {
        Log.fatal(message);
    }
    //Debug Level Logs
    public static void debug (String message) {
        Log.debug(message);
    }
    
    public static String getfieldValueFromResponse(String json,String fieldNme) {
        JSONParser parser = new JSONParser();
        JSONObject jsonResponseObject = new JSONObject();
        Object obj = new Object();
        try {
            obj = parser.parse(json);
        } catch (org.json.simple.parser.ParseException e) {
        	PetStoreUtil.error(e.getStackTrace().toString());
        }
        jsonResponseObject = (JSONObject) obj;
        String reponseCode = jsonResponseObject.get(fieldNme).toString();
        return reponseCode;
    }
    
    public static boolean getfieldValueArrayResponse( ResponseEntity<String> resp) {
        boolean isList = false;
        JSONParser parser = new JSONParser();
        Object obj = new Object();
        try {
            obj = parser.parse(resp.getBody());
        } catch (org.json.simple.parser.ParseException e) {
        	PetStoreUtil.error(e.getStackTrace().toString());
        }
        if (obj instanceof JSONObject) {
        	isList = false;
        } else if  (obj instanceof JSONArray){
        	isList = true;
        }	        

        return isList;
    }
    
    public static boolean getJSONObjectValueArrayResponse(String json,String fieldName,String queryParamValue) {
        JSONParser parser = new JSONParser();
        Object obj = new Object();
        boolean correctStausCodeRetrivedforAllObject = false;
        try {
            obj = parser.parse(json);
        } catch (org.json.simple.parser.ParseException e) {
        	PetStoreUtil.error(e.getStackTrace().toString());
        }
        
        if(obj instanceof JSONArray) {
        	JSONArray jsonArray = (JSONArray) obj;
	         for (int i=0;i<jsonArray.size();i++)
	         {
	        	 JSONObject current = (JSONObject) jsonArray.get(i);
	             String fieldValue = current.get(fieldName) != null ? current.get(fieldName).toString(): "";
	             correctStausCodeRetrivedforAllObject = queryParamValue.equals(fieldValue);
	         }
        }
        return correctStausCodeRetrivedforAllObject;
    }
    
    private Properties properties;
    private volatile static PetStoreUtil instance = null;

    private PetStoreUtil(String propertyFileName)throws IOException {
    	if( PetStoreUtil.instance != null ) {
            throw new InstantiationError( "Creating of this object is not allowed." );
    	}
    	info("Calling  PetStoreUtil Constructor");
        InputStream is = getClass().getClassLoader()
                .getResourceAsStream(propertyFileName);
            this.properties = new Properties();
            this.properties.load(is);
    }

    public static PetStoreUtil getInstance(String propertyName) throws IOException {
          if (instance == null) {               
              synchronized (PetStoreUtil.class) {
                  if (instance == null) {        
                	  instance = new PetStoreUtil(propertyName);
                  }
              }
          }

    	    return instance;
     }

    public String getProperty(String propertyName) throws IOException {
        return this.properties.getProperty(propertyName);
    }
    
    
    protected Object readResolve() 
    { 
        return instance; 
    } 

    @Override
    protected Object clone() throws CloneNotSupportedException  
    { 
    	return instance;  
    } 
}