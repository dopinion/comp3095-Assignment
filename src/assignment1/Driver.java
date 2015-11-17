package assignment1;
import java.sql.*;

/* ************************************************************************
 * Project: COMP3095 
 * Assignment: Assignment 1
 * Author: Danmark Opinion, Alexander Levshun, Abdul
 * Student Number: 100921569, 100-921-795, 100-924-178
 * Date: 10/18/2015
 * Description: Gets username and passwords
 * ************************************************************************/
public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
		
			PreparedStatement ps = null;
			String user = "admin";
			String password = "password";
			//Get a connection to the database
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "admin", "admin");
			
			//Create a sql Statement
			Statement myStmt = myConn.createStatement();
			//Execute SQL query
			ResultSet myRs = myStmt.executeQuery("select * from user");
			
			//Process the results set
			ps = myConn.prepareStatement("select * from user where username=? and password=?");
			ps.setString(1, user);  
	        ps.setString(2, password);  
	        
	        myRs = ps.executeQuery();
	        if(myRs.next())
	        {
	        	System.out.println(user + " " + password);
	        }
		}
		catch (Exception exc){
			exc.printStackTrace();
		}
	}

}
