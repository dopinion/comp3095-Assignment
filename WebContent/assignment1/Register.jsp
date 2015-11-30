<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>

        <link rel="stylesheet" href="/comp3095/assignment1/css/style2.css">
</head>
  <body>

    <div id="hmenu"> 
<ul> Message Board
  <li><a href="/comp3095/assignment1/Home.html">Home</a></li> 
  <li><a href="/comp3095/assignment1/Posts">Posts</a></li> 
  <li><a href="/comp3095/assignment2/AdminController">Admin</a></li> 
</ul>   
</div> 

<div id="formmenu">
  <h1>New User Registration</h1>
  <hr></hr>
  <form method="post" action="/comp3095/Register">
    <table id="registerPage">
      <tr>
        <td class="test">First Name</td>
        <td>
          <input type="text" name="firstname" value="${cookie1}" ></input><label>${errorFirstName}</label><br>
          <label>Must contain only alphabets. Spaces are not allowed.</label>
    
        </td>
      </tr>

      <tr>
        <td class="test">Last Name</td>
        <td>
          <input type="text" name="lastname" value="${cookie2}" ></input></br>
          <label>Must contain only alphabets. Spaces are not allowed.</label>
          <label>${errorLastName}</label>
        </td>
      </tr>

      <tr>
        <td class="test">E-mail Address</td>
        <td>
          <input type="text" name="email" value="${cookie3}" ></input></br>
          <label>Must be a valid e-mail address</label>
          <label>${errorEmail}</label>
        </td>
      </tr>

      <tr>
        <td class="test">Re-Enter E-mail Address</td>
        <td>
          <input type="text" name="reemail"></input></br>
          <label>Must be a valid e-mail address</label>
          <label>${errorReemail}</label>
        </td>
      </tr>

      <tr>
        <td class="test">Telephone #</td>
        <td>
          <input type="text" name="phone" value="${cookie4}" ></input></br>
          <label>Must only contain numbers.</label>
          <label>${errorPhone}</label>
        </td>
      </tr>

      <tr>
        <td class="test">Password</td>
        <td>
          <input type="password" name="pass"></input></br>
          <label>Your account password. Must contain at least one letter and number.</label>
          <label>${errorPass}</label>
      </tr>
      <tr>
        <td class="test">Re-Enter Password</td>
        <td>
          <input type="password" name="repass"></input></br>
          <label>Your account password. Must contain at least one letter and number.</label>
          <label>${errorRepass}</label>
      </tr>

      <tr>
        <td></td>
        <td>
          <input class="primary-btn" type="submit" value="Register"></input>
      </tr>
    </table>
  </form>
</div>
    
    
    
    
    
  </body>
</html>