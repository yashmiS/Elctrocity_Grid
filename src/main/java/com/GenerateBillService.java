package com;
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*;

import model.Bill; 
@Path("/bill")
public class BillService {
     Bill billObj = new Bill();
  @POST
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String insertBill(String insertData)
	 {
			//Convert the input string to a JSON object 
		 JsonObject insertObject = new JsonParser().parse(insertData).getAsJsonObject();
		 
		 String name = insertObject.get("name").getAsString();
		 String date = insertObject.get("date").getAsString();
		 String period = insertObject.get("period").getAsString();
		

		 String output = billObj.insertBill(name, date, period);
		 return output; 
	 }
  
     @GET
	 @Path("/") 
	 @Produces(MediaType.TEXT_HTML) 
	 public String readBill() 
	  { 
		  return billObj.readBill(); 
	  }
     
}