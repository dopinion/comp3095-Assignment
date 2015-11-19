package assignment1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Validations {
	//add booleans
	Boolean fn = false;
	Boolean ln = false;
	Boolean ea = false;
	Boolean rea = false;
	Boolean tp = false;
	Boolean pas = false;
	Boolean repas = false;
	Boolean everything = false;
	
	//REGEX validations
	String emailregex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	String nameregex = "^[a-zA-Z_\\-]+$";
	String phoneregex = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$";
	String passregex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
	
	public String checkFirstName(String firstname)
	{
		String message;
		if(firstname.isEmpty())
		{
			message = "Please enter your first name";
			return message;
		}
		else if(!firstname.matches(nameregex))
		{
			message = "Must contain only alphabets. Spaces are not allowed";
			return message;
		}
		else
		{
			fn = true;
			return "";
		}
		
	}
	
	public String checkLastName(String lastname)
	{
		String message;
		if(lastname.isEmpty())
		{
			message = "Please enter your last name";
			return message;
		}
		else if(!lastname.matches(nameregex))
		{
			message = "Must contain only alphabets. Spaces are not allowed";
			return message;
		}
		else
		{
			ln = true;
			return "";
		}
		
	}
	
	public String checkEmail(String email) throws ClassNotFoundException, SQLException
	{
		ConnectionUtil util = new ConnectionUtil();
		String message;
		if(email.isEmpty())
		{
			message = "Please enter your email address";
			return message;
		}
		else if(!email.matches(emailregex))
		{
			message = "Incorrect email address";
			return message;
		}
		else if(util.findUser(email) == true)
		{
			message = "User already exists.";
			return message;
		}
		else
		{
			ea = true;
			return "";
		}
		
	}
	
	public String checkReEmail(String reemail)
	{
		String message;
		if(reemail.isEmpty())
		{
			message = "Please re-enter your email address";
			return message;
		}
		else if(!reemail.matches(emailregex))
		{
			message = "Incorrect email address";
			return message;
		}
		else
		{
			rea = true;
			return "";
		}
		
	}
	
	public String checkPhone(String telephone)
	{
		String message;
		if(telephone.isEmpty())
		{
			message = "Please enter your phone number";
			return message;
		}
		else if(!telephone.matches(phoneregex))
		{
			message = "Must only contain numbers and formatted 123-456-7894";
			return message;
		}
		else
		{
			tp = true;
			return "";
		}
		
	}
	
	public String checkPass(String password)
	{
		if(password.isEmpty())
		{
			return "Please enter your password";
		}
		else if(!password.matches(passregex))
		{
			return "Password must contain 8 character and at least one number";
		}
		else{
			pas = true;
			return "";
		}
		
	}
	
	public String checkRePass(String repassword)
	{
		if(repassword.isEmpty())
		{
			return "Please enter your password";
		}
		else if(!repassword.matches(passregex))
		{
			return "Password must contain 8 character and at least one number";
		}
		else{
			repas = true;
			return "";
		}
		
	}
	
	public Boolean checkEverything()
	{
		if(fn.equals(true) && ln.equals(true) && ea.equals(true) && ln.equals(true) && rea.equals(true) && tp.equals(true) && pas.equals(true) && repas.equals(true))
		{
			everything = true;
			return everything;
		}
		else
		{
			everything = false;
			return everything;
		}
	}

	
}
