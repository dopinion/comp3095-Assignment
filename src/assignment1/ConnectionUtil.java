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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;


public class ConnectionUtil {
	
	String url = "jdbc:mysql://localhost:3306/demo";
	String driver = "com.mysql.jdbc.Driver";

	//Adds Posts to the database
	public void insertPost(String title, String body, Object object) throws ClassNotFoundException, SQLException
	{
		Class.forName(driver);
		Connection myConn = DriverManager.getConnection(url, "admin", "admin");
		
		//Create prepared statement
		PreparedStatement ps = myConn.prepareStatement("insert into posts(title, body, username, date) values (?, ?, ?, ?)");
		
		DateFormat dateFormat = new SimpleDateFormat(" '-' yyyy/MM/dd '-' h:mmaaa");
		Date date = new Date();
		
		ps.setString(1, title);
		ps.setString(2, body);
		ps.setString(3, object.toString());
		ps.setString(4, dateFormat.format(date));

		
		ps.executeUpdate();
		myConn.close();
	
	}
	
	public void insertComment(String comment, Object object, Integer id) throws ClassNotFoundException, SQLException
	{
		Class.forName(driver);
		Connection myConn = DriverManager.getConnection(url, "admin", "admin");
		
		//Create a sql Statement
		Statement myStmt = myConn.createStatement();
		
		//Create prepared statement
		DateFormat dateFormat = new SimpleDateFormat(" '-' yyyy/MM/dd '-' h:mmaaa");
		Date date = new Date();
		myStmt.executeUpdate("insert into comments(body, username, idPosts, date) values ('" + comment + "', '" + object.toString() + "', '" + id + "', '" + dateFormat.format(date).toString() + "')");
		myConn.close();
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
		myConn.close();
	}
	
	public String findPost(int id) throws ClassNotFoundException, SQLException
	{
		String body = null;
		Class.forName(driver);
		Connection myConn = DriverManager.getConnection(url, "admin", "admin");
		
		//Create a sql Statement
		Statement myStmt = myConn.createStatement();
		//Execute SQL query
		ResultSet myRs = myStmt.executeQuery("select * from posts where idPosts='" + id + "'");
		
		while(myRs.next())
		{
			body = myRs.getString("body");
		}
		myConn.close();
		return body;
		
	}
	
	public String findPostTitle(int id) throws ClassNotFoundException, SQLException
	{
		String title = null;
		Class.forName(driver);
		Connection myConn = DriverManager.getConnection(url, "admin", "admin");
		
		//Create a sql Statement
		Statement myStmt = myConn.createStatement();
		//Execute SQL query
		ResultSet myRs = myStmt.executeQuery("select * from posts where idPosts='" + id + "'");
		
		while(myRs.next())
		{
			title = myRs.getString("title");
		}
		myConn.close();
		return title;
		
	}
	
	//Checks how many rows in the post table. if none, displays a warning to a user that there are no posts.
	public Boolean countRows() throws ClassNotFoundException, SQLException
	{
		Class.forName(driver);
		Connection myConn = DriverManager.getConnection(url, "admin", "admin");
		
		//Create a sql Statement
		Statement myStmt = myConn.createStatement();
		//Execute SQL query
		ResultSet myRs = myStmt.executeQuery("select * from posts");
		
		if(!myRs.next())
		{
			myConn.close();
			return true;
		}
		else{
			return false;
		}
	}
	
	
	public Timestamp findDate(int id) throws ClassNotFoundException, SQLException
	{
		Connection myConn = DriverManager.getConnection(url, "admin", "admin");
		Timestamp stamp = null;
	
		//Create a sql Statement
		Statement myStmt = myConn.createStatement();
		//Execute SQL query
		ResultSet myRs = myStmt.executeQuery("select * from posts where idPosts='" + id + "'");
		
		while(myRs.next())
		{
			stamp = myRs.getTimestamp("date");
		}
		myConn.close();
		return stamp;
		
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
        	myConn.close();
        	return true;
        }
		else
		{
			myConn.close();
			return false;
		}
        

	}
	
	//remove a Post
	public void removePost(String id) throws SQLException, ClassNotFoundException
	{
		
		Class.forName(driver);
		Connection myConn = DriverManager.getConnection(url, "admin", "admin");
		
		//Create a sql Statement
		Statement myStmt = myConn.createStatement();
		
		
		myStmt.executeUpdate("delete from posts where idPosts='" + id + "'");
		myConn.close();
		
	}
	//remove all comments
	public void removeComments(String id) throws SQLException, ClassNotFoundException
	{
		
		Class.forName(driver);
		Connection myConn = DriverManager.getConnection(url, "admin", "admin");
		
		//Create a sql Statement
		Statement myStmt = myConn.createStatement();
		
		
		myStmt.executeUpdate("delete from comments where idPosts='" + id + "'");
		myConn.close();
		
	}
	
	//Add a method that returns Post Title, Author, and Post body
	
	public ArrayList<HashMap<String,Object>> getPosts() throws SQLException, ClassNotFoundException
	{
		//Create an Hashmap	
		
		ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

		Class.forName(driver);
		
		Connection myConn = DriverManager.getConnection(url, "admin", "admin");
		Statement myStmt = myConn.createStatement();
		
		ResultSet myRs = myStmt.executeQuery("select idPosts, title, body, username, date from posts");
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
		myConn.close();
		return list;
	
	}
	
	public ArrayList<HashMap<String,Object>> getComments() throws SQLException, ClassNotFoundException
	{
		//Create an Hashmap	
		
		ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

		Class.forName(driver);
		
		Connection myConn = DriverManager.getConnection(url, "admin", "admin");
		Statement myStmt = myConn.createStatement();
		
		ResultSet myRs = myStmt.executeQuery("select idcomments, body, username, idPosts, date from comments");
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
		myConn.close();
		return list;
		
	
	}
	


}
