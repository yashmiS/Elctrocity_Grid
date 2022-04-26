package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Payment;

@Path("/payment")
public class PaymentService {
	Payment paymentObj = new Payment();
	
	@POST
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String insertPayment(String insertData)
	 {
		//Convert the input string to a JSON object 
		 JsonObject insertObject = new JsonParser().parse(insertData).getAsJsonObject();
		 
		 String accountno = insertObject.get("accountno").getAsString();
		 String nic = insertObject.get("nic").getAsString();
		 String amount = insertObject.get("amount").getAsString();
		 String date = insertObject.get("date").getAsString();

		 String output = paymentObj.insertPayment(accountno, nic, amount, date);
		 return output; 
	 }
	@GET
	 @Path("/") 
	 @Produces(MediaType.TEXT_HTML) 
	 public String readPayment() 
	  { 
		  return paymentObj.readPayment(); 
	  }
	@PUT
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String updatePayment(String updateData)
	 {
		 JsonObject updateObject = new JsonParser().parse(updateData).getAsJsonObject();
		 
		 String idpayment = updateObject.get("idpayment").getAsString();
		 String accountno = updateObject.get("accountno").getAsString();
		 String nic = updateObject.get("nic").getAsString();
		 String amount = updateObject.get("amount").getAsString();
		 String date = updateObject.get("date").getAsString();
		 
		 String output = paymentObj.updatePayment(idpayment, accountno, nic, amount, date);
		 return output;
	 }
	 
	 @DELETE
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String deleteCustomer(String deleteData)
	 {
		 JsonObject updateObject = new JsonParser().parse(deleteData).getAsJsonObject();
		 
		 String idpayment = updateObject.get("idpayment").getAsString();
		 
		 String output = paymentObj.deletePayment(idpayment);
		 return output;

	 }
}
