<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
    <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>

        <link rel="stylesheet" href="/comp3095/assignment1/css/style3.css">

</head>
<body>
<%if(session.getAttribute("user") != null) { response.sendRedirect("Posts");} else{ %>
<div id="hmenu">
  <ul> Message Board
    <li><a href="/comp3095/assignment1/Home.html">Home</a></li>
    <li><a href="/comp3095/assignment1/Posts">Posts</a></li>
    <li><a href="/comp3095/assignment2/AdminController">Admin</a></li>
  </ul>
</div>

<div id="loginmenu">
  <h3>Login </h3>
  <form method=post action=/comp3095/LoginServ>
    <table>
      <tr>
        <td>Username</td>
        <td>
          <input type="text" name="username"></input>
          </br>
          <label>Your email address is your Username.</label>
        </td>

      </tr>
      <tr>
        <td>Password</td>
        <td>
          <input type="password" name="password"></input>
          </br>
          <label>Your account password.</label>
        </td>
      </tr>
      <tr id=logintr>
        <td>
          <input type="submit" name="login" value="Login" class="primary-btn"></input>
        </td>
        <td>
          <label>${errorLogin}</label>
        </td>
      </tr>
    </table>
  </form>
</div>
<%} %>
</body>
</html>