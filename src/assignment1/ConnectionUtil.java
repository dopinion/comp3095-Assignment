package assignment1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionUtil {
	
	String url = "jdbc:mysql://localhost:3306/demo";
	String driver = "com.mysql.jdbc.Driver";
	public void registerUser(String user,String password,String email,String telephone,String firstname,String lastname) throws SQLException, ClassNotFoundException
	{
		Class.forName(driver);
		//Get a connection to the database
		Connection myConn = DriverManager.getConnection(url, "admin", "admin");
	
		//Create a sql Statement
		Statement myStmt = myConn.createStatement();
		//Execute SQL query
		String sql = "insert into user"
				+ "(username, password, email, telephone, firstname, lastname)"
				+ "values ('"+user+"', '"+password+"', '"+email+"', '"+telephone+"', '"+firstname+"', '"+lastname+"')";
		myStmt.executeUpdate(sql);
	}

	public boolean findUser (String user) throws ClassNotFoundException, SQLException
	{
		Class.forName(driver);
		
		//Get a connection to the database
		Connection myConn = DriverManager.getConnection(url, "admin", "admin");
		
		//Create a sql Statement
		Statement myStmt = myConn.createStatement();
		//Execute SQL query
		ResultSet myRs = myStmt.executeQuery("select * from user where username='" + user + "'");
		
		
		
		//finds user if it exists and returns a boolean
        if(myRs.next())
        {
        	return true;
        }
		else
		{
			return false;
		}
		
	

	}

}
