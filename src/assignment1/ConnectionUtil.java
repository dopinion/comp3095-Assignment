package assignment1;

import java.awt.List;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class ConnectionUtil {
	
	String url = "jdbc:mysql://localhost:3306/demo";
	String driver = "com.mysql.jdbc.Driver";

	//Adds Posts to the database
	public void insertPost(String title, String body, Object object) throws ClassNotFoundException, SQLException
	{
		Class.forName(driver);
		Connection myConn = DriverManager.getConnection(url, "admin", "admin");
		
		//Create a sql Statement
		Statement myStmt = myConn.createStatement();
		
		//Create prepared statement
		PreparedStatement ps = myConn.prepareStatement("insert into posts(title, body, username) values (?, ?, ?)");
		
		ps.setString(1, title);
		ps.setString(2, body);
		ps.setString(3, object.toString());
		
		ps.executeUpdate();
		//ps.close();
	
	}
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
	
	//Add a method that returns Post Title, Author, and Post body
	
	public ArrayList<Object[]> getPost() throws SQLException, ClassNotFoundException
	{
		//Create an ArrayList
		
		ArrayList <Object[]> result = new ArrayList<Object[]>();
		
		Class.forName(driver);
		
		Connection myConn = DriverManager.getConnection(url, "admin", "admin");
		Statement myStmt = myConn.createStatement();
		
		ResultSet myRs = myStmt.executeQuery("select title, body, username from posts");
		
		int count = myRs.getMetaData().getColumnCount();
		
		while(myRs.next())
		{
			//Creating an object array that saves the row
			Object[] row = new Object[count];
			for(int i=0; i < count; i++)
			{
				row[i] = myRs.getObject(i+1);
			}
			result.add(row);
		}
		return result;
	
	}
	
	public ArrayList<HashMap<String,Object>> getPosts() throws SQLException, ClassNotFoundException
	{
		//Create an Hashmap	
		
		ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

		Class.forName(driver);
		
		Connection myConn = DriverManager.getConnection(url, "admin", "admin");
		Statement myStmt = myConn.createStatement();
		
		ResultSet myRs = myStmt.executeQuery("select title, body, username from posts");
		ResultSetMetaData md = myRs.getMetaData();
		
		//Count number of columns in the post table
		int count = md.getColumnCount();
		
		while(myRs.next())
		{
			//Creating an object array that saves the row
			HashMap<String,Object> row = new HashMap<String,Object>(count);
			
		
			for(int i=1; i <= count; i++)
			{
				row.put(md.getColumnName(i), myRs.getObject(i));
			}
			list.add(row);
		}
		return list;
	
	}


}
