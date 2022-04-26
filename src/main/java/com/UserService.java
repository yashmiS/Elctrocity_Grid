package com;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*;

import model.User; 
@Path("/user")
public class UserService {
    User userObj = new User();
	
	 @POST
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String insertUser(String insertData)
	 {
			//Convert the input string to a JSON object 
		 JsonObject insertObject = new JsonParser().parse(insertData).getAsJsonObject();
		 
		 String name = insertObject.get("name").getAsString();
		 String nic = insertObject.get("nic").getAsString();
		 String password = insertObject.get("password").getAsString();
		

		 String output = userObj.insertUser(name, nic, password);
		 return output; 
	 }
	 
	 @GET
	 @Path("/") 
	 @Produces(MediaType.TEXT_HTML) 
	 public String readUser() 
	  { 
		  return userObj.readUser(); 
	  }
}