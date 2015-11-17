package assignment1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */

/* ************************************************************************
 * Project: COMP3095 
 * Assignment: Assignment 1
 * Author: Danmark Opinion, Alexander Levshun, Abdul
 * Student Number: 100921569, 100-921-795, 100-924-178
 * Date: 10/18/2015
 * Description: Looks for errors in registration form, if no errors are found it will add user to database
 * ************************************************************************/
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//request all the parameters from the form
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String reemail = request.getParameter("reemail");
		String phone = request.getParameter("phone");
		String pass = request.getParameter("pass");
		String repass = request.getParameter("repass");
		
		//Cookies for sticky form
		Cookie c1 = new Cookie("firstname", firstname);
		c1.setMaxAge(60*60*60);
		response.addCookie(c1);
		request.setAttribute("cookie1", c1.getValue());
		
		Cookie c2 = new Cookie("lastname", lastname);
		c1.setMaxAge(60*60*60);
		response.addCookie(c2);
		request.setAttribute("cookie2", c2.getValue());
		
		Cookie c3 = new Cookie("email", email);
		c1.setMaxAge(60*60*60);
		response.addCookie(c3);
		request.setAttribute("cookie3", c3.getValue());
		
		Cookie c4 = new Cookie("phone", phone);
		c1.setMaxAge(60*60*60);
		response.addCookie(c4);
		request.setAttribute("cookie4", c4.getValue());
		
		//add booleans
		Boolean fn = false;
		Boolean ln = false;
		Boolean ea = false;
		Boolean rea = false;
		Boolean tp = false;
		Boolean pas = false;
		Boolean repas = false;
		

		//REGEX validations
		String emailregex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		String nameregex = "^[a-zA-Z_\\-]+$";
		String phoneregex = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$";
		String passregex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
		if(firstname.isEmpty())
		{
			request.setAttribute("errorMessage1", "*Please enter your first name");
		}
		else if(!firstname.matches(nameregex))
		{
			request.setAttribute("errorMessage1", "*Must contain only alphabets. Spaces are not allowed");
		}
		else
		{
			fn = true;
		}
		
		
		if(lastname.isEmpty())
		{
			request.setAttribute("errorMessage2", "*Please enter your last name");
		}
		else if(!lastname.matches(nameregex))
		{
			request.setAttribute("errorMessage1", "*Must contain only alphabets. Spaces are not allowed");
		}
		else
		{
			ln = true;
		}
		
		if(email.isEmpty())
		{
			request.setAttribute("errorMessage3", "*Please enter your email address");
		}
		else if(!email.matches(emailregex))
		{
			request.setAttribute("errorMessage3", "*Incorrect email address");
		}
		else{
			ea = true;
		}
		
		if(reemail.isEmpty())
		{
			request.setAttribute("errorMessage4", "*Please re-enter your email address");
		}
		else if(!reemail.equals(email))
		{
			request.setAttribute("errorMessage4", "*Email addresses do not match");
		}
		else
		{
			rea = true;
		}
		
		if(phone.isEmpty())
		{
			request.setAttribute("errorMessage5", "*Please enter your phone number");
		}
		else if(!phone.matches(phoneregex))
		{
			request.setAttribute("errorMessage5", "*Must only contain numbers and formatted 123-456-7894");
		}
		else
		{
			tp = true;
		}
		if(pass.isEmpty())
		{
			request.setAttribute("errorMessage6", "*Please enter your password");
		}
		else if(!pass.matches(passregex))
		{
			request.setAttribute("errorMessage6", "*Password must contain 8 character and at least one number");
		}
		else{
			pas = true;
		}
		if(repass.isEmpty())
		{
			request.setAttribute("errorMessage7", "*Please re-enter your password");
		}
		else if(!repass.equals(pass))
		{
			request.setAttribute("errorMessage7", "*Passwords do not match");
		}
		else
		{
			repas = true;
		}
		
		
		String url = "jdbc:mysql://localhost:3306/demo";
		if(fn.equals(true) && ln.equals(true) && ea.equals(true) && ln.equals(true) && rea.equals(true) && tp.equals(true) && pas.equals(true) && repas.equals(true)){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				//Get a connection to the database
				Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "admin", "admin");
			
				//Create a sql Statement
				Statement myStmt = myConn.createStatement();
				//Execute SQL query
				String sql = "insert into user"
						+ "(username, password, email, telephone, firstname, lastname)"
						+ "values ('"+email+"', '"+pass+"', '"+email+"', '"+phone+"', '"+firstname+"', '"+lastname+"')";
				myStmt.executeUpdate(sql);
				response.sendRedirect("Success.html");
			
			}

			catch (Exception exc){
				exc.printStackTrace();
			}
		}
		else{
			RequestDispatcher requestDispatcher;
			requestDispatcher = request.getRequestDispatcher("/form.jsp");
			requestDispatcher.forward(request, response);
		}
		
	
	}

}
