package assignment1;

public class User {
	
	private String firstname;
	private String lastname;
	private String email;
	private String phone;
	private String pass;
	private String repass;
	private String reemail;

	
	public String getFirstName()
	{
		return firstname;
	}
	public void setFirstName(String fn)
	{
		fn = firstname;
	}
	
	public String getLastName()
	{
		return lastname;
	}
	public void setLasttName(String ln)
	{
		ln = firstname;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String em)
	{
		em = email;
	}
	public String getReEmail()
	{
		return reemail;
	}
	
	public void setReEmail(String rm)
	{
		rm = reemail;
	}
	
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String ph)
	{
		ph = phone;
	}
	
	public String getPass()
	{
		return pass;
	}
	
	public void setPass(String ps)
	{
		ps = pass;
	}
	public String getRePass()
	{
		return repass;
	}
	
	public void setRePass(String rps)
	{
		rps = repass;
	}

}
