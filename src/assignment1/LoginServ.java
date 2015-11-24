package assignment1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.PreparedStatement;  


/* ************************************************************************
 * Project: COMP3095 
 * Assignment: Assignment 1
 * Author: Danmark Opinion, Alexander Levshun, Abdul
 * Student Number: 100921569, 100-921-795, 100-924-178
 * Date: 10/18/2015
 * Description: Gets Checks if username and password exist in order to validate login
 * ************************************************************************/
/**
 * Servlet implementation class LoginServ
 */
@WebServlet("/LoginServ")
public class LoginServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String user = request.getParameter("username");
		String password = request.getParameter("password");
		PreparedStatement ps = null;
		ResultSet myRs;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//Get a connection to the database
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "admin", "admin");

			//Create a sql Statement
			//Statement myStmt = myConn.createStatement();
			
			//Execute SQL query
			ps = myConn.prepareStatement("select * from user where username=? and password=?");
			ps.setString(1, user);  
	        ps.setString(2, password);  
	        
	        myRs = ps.executeQuery();
	        if(myRs.next())
	        {
	        	HttpSession session = request.getSession(true);
	        	session.setAttribute("user", user);
	        	request.setAttribute("WelcomeMessage", "Welcome " + session.getAttribute("user"));
	        	RequestDispatcher requestDispatcher;
				requestDispatcher = request.getRequestDispatcher("assignment1/Posts");
				requestDispatcher.forward(request, response);
	        }
	        else
	        {
	        	request.setAttribute("errorLogin", "*Incorrect user or password");
	        	RequestDispatcher requestDispatcher;
				requestDispatcher = request.getRequestDispatcher("assignment1/login.jsp");
				requestDispatcher.forward(request, response);
	        }
	  
		
		}

		catch (Exception exc){
			exc.printStackTrace();
		}
	}

}
