package assignment1;

import java.io.IOException;
import java.sql.SQLException;

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

		
		
		
		//Cookies for sticky form
		Cookie c1 = new Cookie("firstname", request.getParameter("firstname"));
		c1.setMaxAge(60*60*60);
		response.addCookie(c1);
		request.setAttribute("cookie1", c1.getValue());
		
		Cookie c2 = new Cookie("lastname", request.getParameter("lastname"));
		c1.setMaxAge(60*60*60);
		response.addCookie(c2);
		request.setAttribute("cookie2", c2.getValue());
		
		Cookie c3 = new Cookie("email", request.getParameter("email"));
		c1.setMaxAge(60*60*60);
		response.addCookie(c3);
		request.setAttribute("cookie3", c3.getValue());
		
		Cookie c4 = new Cookie("phone", request.getParameter("phone"));
		c1.setMaxAge(60*60*60);
		response.addCookie(c4);
		request.setAttribute("cookie4", c4.getValue());
		

		//Validating data and setting error messages
		Validations validator = new Validations();
		
		request.setAttribute("errorFirstName", validator.checkFirstName(request.getParameter("firstname")));
		request.setAttribute("errorLastName", validator.checkLastName(request.getParameter("lastname")));
		
		try {
			request.setAttribute("errorEmail", validator.checkEmail(request.getParameter("email")));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("errorReemail", validator.checkReEmail(request.getParameter("reemail")));
		request.setAttribute("errorPhone", validator.checkPhone(request.getParameter("phone")));
		request.setAttribute("errorPass", validator.checkPass(request.getParameter("repass")));
		request.setAttribute("errorRepass", validator.checkRePass(request.getParameter("reemail")));
		

		if(validator.checkEverything().equals(true)){
			
			//Create a new user instance and adding form fields to it.
			User newUser = new User();
			newUser.setFirstName(request.getParameter("firstname"));
			newUser.setLasttName(request.getParameter("lastname"));
			newUser.setEmail(request.getParameter("email"));
			newUser.setPass(request.getParameter("pass"));
			newUser.setPhone(request.getParameter("phone"));
			newUser.setRePass(request.getParameter("repass"));
			newUser.setReEmail(request.getParameter("reemail"));
			try {
				ConnectionUtil util = new ConnectionUtil();
				util.registerUser(newUser.getEmail(), newUser.getPass(), newUser.getEmail(), newUser.getPhone(), newUser.getFirstName(), newUser.getLastName());
				response.sendRedirect("Success.html");
			}

			catch (Exception exc){
				exc.printStackTrace();
			}
		}
		else{
			RequestDispatcher requestDispatcher;
			requestDispatcher = request.getRequestDispatcher("assignment1/Register.jsp");
			requestDispatcher.forward(request, response);
		}
		
	
	}

}
