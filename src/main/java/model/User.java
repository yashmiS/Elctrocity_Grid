package model;

import java.sql.*; 

public class User {
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
    public String insertUser(String name, String nic, String password) {
		
		String output = "";
		
		try {
			Connection con = connect();
			
			if (con == null)
			{return  "Error while connecting to the database for inserting.";}
			
			// create a prepared statement
			String query = "  insert into user (`iduser`,`name`,`nic`,`password`)" + " values (?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			 // binding values
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setString(2, name); 
			 preparedStmt.setString(3, nic); 
			 preparedStmt.setString(4, password); 
		
			 
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
    public String readUser() {
		
		String output = "";
		
		try {
			
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to the database for reading.";
			}
			
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>User ID</th><th>Name</th><th>NIC</th>" +
			 "<th>Password</th>";
			 
			 String query = "select * from user"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 
			 while(rs.next()) {
				 String iduser = Integer.toString(rs.getInt("iduser")); 
				 String name = rs.getString("name"); 
				 String nic = rs.getString("nic"); 
				 String password = rs.getString("password"); 
				
				 
				 // Add into the html table
				 output += "<tr><td>" + iduser + "</td>"; 
				 output += "<td>" + name + "</td>"; 
				 output += "<td>" + nic + "</td>"; 
				 output += "<td>" + password + "</td>"; 
				
				 
			 }
			 con.close(); 
			 // Complete the html table
			 output += "</table>";
		}
		catch(Exception e)
		{
			 output = "Error while reading the Complain."; 
			 System.err.println(e.getMessage()); 
		}
		
		return output;
	} 
    public String updateUser(String iduser,String name, String nic, String password) 
	{ 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 String query = "UPDATE user SET name=?,nic=?,password=? WHERE iduser=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, name); 
		 preparedStmt.setString(2, nic); 
		 preparedStmt.setString(3, password); 
		 preparedStmt.setInt(5, Integer.parseInt(iduser)); 

		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while updating the user details."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
}