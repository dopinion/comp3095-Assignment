<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Posts page</title>
<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>

        <link rel="stylesheet" href="/comp3095/assignment1/css/style2.css">
</head>
<body>
<%if(session.getAttribute("user") != null) { %>
<div id="hmenu"> 
<ul> Message Board
  <li><a href="/comp3095/assignment1/Home.html">Home</a></li> 
  <li>
  <a href="/comp3095/assignment1/Posts.jsp">Posts</a>
  </li> 
  <li><a href="admin.jsp">Admin</a></li> 
  <%if(session.getAttribute("user") != null) { %>
  <li id="logged">Logged in as ${sessionScope.user} 


  		<a id="log" href="/comp3095/Logout"> Logout</a>


  </li>
    	<%}else{ %> 
    	
  <li id="logged">


  		<a id="log" href="/comp3095/login.jsp"> Login</a>


  </li>
  <%} %>
</ul>   
</div> 
<div>
	<h2>Posts</h2>
	<hr>
	<p>This page will be popualted in a future assignment.</p>
</div>
<%}else{ response.sendRedirect("Home.html");}%> 
</body>
</html>