package model;

import java.sql.*; 
public class Bill {
    private Connection connect()
	{
			Connection con = null;
			try
			{
				Class.forName("com.mysql.jdbc.Driver");

				//Provide the correct details: DBServer/DBName, user name, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "1234");
			}
			catch (Exception e)
			{e.printStackTrace();}
			return con;
	}
    public String insertBill(String name, String date, String period) {
		
		String output = "";
		
		try {
			Connection con = connect();
			
			if (con == null)
			{return  "Error while connecting to the database for inserting.";}
			
			// create a prepared statement
			String query = "  insert into bill (`idbill`,`name`,`date`,`period`)" + " values (?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			 // binding values
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setString(2, name); 
			 preparedStmt.setString(3, date); 
			 preparedStmt.setString(4, period); 
		
			 
			// execute the statement
			 
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Inserted successfully"; 
		}
		catch (Exception e) {
			 output = "Error while inserting the Complain."; 
			 System.err.println(e.getMessage()); 
		}
		
		return output;
	}

public String readBill() {
	
	String output = "";
	
	try {
		
		Connection con = connect();
		
		if(con == null) {
			return "Error while connecting to the database for reading.";
		}
		
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>Bill ID</th><th>Name</th><th>Date</th>" +
		 "<th>period Type</th>";
		 
		 String query = "select * from bill"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 
		 while(rs.next()) {
			 String idbill = Integer.toString(rs.getInt("idbill")); 
			 String name = rs.getString("name"); 
			 String date = rs.getString("date"); 
			 String period = rs.getString("period"); 
			 
			 // Add into the html table
			 output += "<tr><td>" + idbill + "</td>"; 
			 output += "<td>" + name + "</td>"; 
			 output += "<td>" + date + "</td>"; 
			 output += "<td>" + period + "</td>"; 
			
			 
		 }
		 con.close(); 
		 // Complete the html table
		 output += "</table>";
	}
	catch(Exception e)
	{
		 output = "Error while reading the bill."; 
		 System.err.println(e.getMessage()); 
	}
	
	return output;
}

public String updateBill(String idbill,String name, String date, String period) 
{ 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for updating."; } 
	 // create a prepared statement
	 String query = "UPDATE bill SET name=?,date=?,period=? WHERE idbill=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setString(1, name); 
	 preparedStmt.setString(2, date); 
	 preparedStmt.setString(3, period); 
	 preparedStmt.setInt(5, Integer.parseInt(idbill)); 

	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Updated successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while updating the complain details."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
     public String deleteBill(String idbill) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for deleting."; } 
 // create a prepared statement
 String query = "delete from bill where idcomplain=?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, Integer.parseInt(idbill)); 
 // execute the statement
 preparedStmt.execute(); 
 con.close(); 
 output = "Deleted successfully"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while deleting the Complain Details"; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 }
}