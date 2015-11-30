<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>

        <link rel="stylesheet" href="/comp3095/assignment1/css/style2.css">
</head>
<body>
<%if(session.getAttribute("user") != null) { %>
<div id="hmenu">
  <ul> Message Board
    <li><a href="/comp3095/assignment1/Home.html">Home</a></li>
    <li><a href="/comp3095/assignment1/Posts">Posts</a></li>
    <li><a href="/comp3095/assignment2/AdminController">Admin</a></li>
    <%if(session.getAttribute( "user")!=null) { %>
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
<%--So lets create a form for the user to enter Post title and Post Body --%>
<div id="newpostmenu">
    <h2 id="title">New Post</h2><hr>
    <form method=post action=/comp3095/Post>
      <table id="commentTable">
        <tr>
          <td>
          	<p>Post Title</p>
            <input type="text" name="posttitle" value="${postTitleCookie}" size="50"></input>
          </td>
        </tr>
        <tr>
          <td>
            <textarea name="postbody" placeholder="Input post text. Max 3000 characters." maxlength="3000" rows="5" cols="75">${postBodyCookie}</textarea>
          </td>
        </tr>
        <tr id=logintr>
          <td>
            <input type="submit" name="postit" value="Submit" class="primary-btn"></input>
            <label>${errorTitle}</label>
            <label>${errorBody}</label>
          </td>
    
        </tr>
      </table>
    </form>
  </div>
   

<%}else{ response.sendRedirect("Home.html");}%> 
</body>
</html>