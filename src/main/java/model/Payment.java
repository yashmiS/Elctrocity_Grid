package model;
import java.sql.*; 
public class Payment {
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
public String insertPayment(String accountno, String nic, String amount, String date) {
		
		String output = "";
		
		try {
			Connection con = connect();
			
			if (con == null)
			{return  "Error while connecting to the database for inserting.";}
			
			// create a prepared statement
			String query = "  insert into payment (`idpayment`,`accountno`,`nic`,`amount`,`date`)" + " values (?, ?, ?, ?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			 // binding values
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setString(2, accountno); 
			 preparedStmt.setString(3, nic); 
			 preparedStmt.setString(4, amount); 
			 preparedStmt.setString(5, date); 
			 
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

public String readPayment() {
	
	String output = "";
	
	try {
		
		Connection con = connect();
		
		if(con == null) {
			return "Error while connecting to the database for reading.";
		}
		
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>Payment ID</th><th>Account NO</th><th>NIC</th>" +
		 "<th>AMOUNT</th>" + 
		 "<th>Date</th></tr>";
		 
		 String query = "select * from payment"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 
		 while(rs.next()) {
			 String idpayment = Integer.toString(rs.getInt("idpayment")); 
			 String accountno = rs.getString("accountno"); 
			 String nic = rs.getString("nic"); 
			 String amount = rs.getString("amount"); 
			 String date = rs.getString("date"); 
			 
			 // Add into the html table
			 output += "<tr><td>" + idpayment + "</td>"; 
			 output += "<td>" + accountno + "</td>"; 
			 output += "<td>" + nic + "</td>"; 
			 output += "<td>" + amount + "</td>"; 
			 output += "<td>" + date + "</td>";
			 
		 }
		 con.close(); 
		 // Complete the html table
		 output += "</table>";
	}
	catch(Exception e)
	{
		 output = "Error while reading the Payment."; 
		 System.err.println(e.getMessage()); 
	}
	
	return output;
}
public String updatePayment(String idpayment,String accountno, String nic, String amount, String date) 
{ 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for updating."; } 
	 // create a prepared statement
	 String query = "UPDATE payment SET accountno=?,nic=?,amount=?,date=? WHERE idpayment=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setString(1, accountno); 
	 preparedStmt.setString(2, nic); 
	 preparedStmt.setString(3, amount); 
	 preparedStmt.setString(4, date); 
	 preparedStmt.setInt(5, Integer.parseInt(idpayment)); 

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

public String deletePayment(String idpayment) 
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
 preparedStmt.setInt(1, Integer.parseInt(idpayment)); 
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
