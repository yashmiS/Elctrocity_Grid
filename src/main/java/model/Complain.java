package model;

import java.sql.*; 

public class Complain {
	
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
	
	public String insertComplain(String name, String date, String complaintype, String nic) {
		
		String output = "";
		
		try {
			Connection con = connect();
			
			if (con == null)
			{return  "Error while connecting to the database for inserting.";}
			
			// create a prepared statement
			String query = "  insert into complain (`idcomplain`,`name`,`date`,`complaintype`,`nic`)" + " values (?, ?, ?, ?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			 // binding values
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setString(2, name); 
			 preparedStmt.setString(3, date); 
			 preparedStmt.setString(4, complaintype); 
			 preparedStmt.setString(5, nic); 
			 
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
	
	public String readComplain() {
		
		String output = "";
		
		try {
			
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to the database for reading.";
			}
			
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Complain ID</th><th>Complain</th><th>Date</th>" +
			 "<th>Complain Type</th>" + 
			 "<th>NIC</th></tr>";
			 
			 String query = "select * from complain"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 
			 while(rs.next()) {
				 String idcomplain = Integer.toString(rs.getInt("idcomplain")); 
				 String name = rs.getString("name"); 
				 String date = rs.getString("date"); 
				 String complaintype = rs.getString("complaintype"); 
				 String nic = rs.getString("nic"); 
				 
				 // Add into the html table
				 output += "<tr><td>" + idcomplain + "</td>"; 
				 output += "<td>" + name + "</td>"; 
				 output += "<td>" + date + "</td>"; 
				 output += "<td>" + complaintype + "</td>"; 
				 output += "<td>" + nic + "</td>";
				 
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
	
	public String updateComplain(String idcomplain,String name, String date, String complaintype, String nic) 
	{ 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 String query = "UPDATE complain SET name=?,date=?,complaintype=?,nic=? WHERE idcomplain=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, name); 
		 preparedStmt.setString(2, date); 
		 preparedStmt.setString(3, complaintype); 
		 preparedStmt.setString(4, nic); 
		 preparedStmt.setInt(5, Integer.parseInt(idcomplain)); 

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
	
	public String deleteComplain(String idcomplain) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 String query = "delete from complain where idcomplain=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(idcomplain)); 
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
