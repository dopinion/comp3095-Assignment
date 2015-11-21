<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%if(session.getAttribute("user") != null) { %>
<div id="hmenu">
  <ul> Message Board
    <li><a href="/comp3095/assignment1/Home.html">Home</a></li>
    <li><a href="/comp3095/assignment1/Posts.jsp">Posts</a></li>
    <li><a href="/comp3095/assignment1/admin.jsp">Admin</a></li>
  </ul>
</div>
<%--So lets create a form for the user to enter Post title and Post Body --%>
<div id="newpostmenu">
    <h3>New Post</h3>
    <form method=post action=/comp3095/Post>
      <table>
        <tr>
          <td>Post Title</td>
          <td>
            <input type="text" name="posttitle"></input>
          </td>
        </tr>
        <tr>
          <td></td>
          <td>
            <textarea name="postbody" placeholder="Input post text. Max 3000 characters." maxlength="3000" rows="5" cols="50"></textarea>
          </td>
        </tr>
        <tr id=logintr>
          <td>
            <input type="submit" name="postit" value="Publish" class="primary-btn"></input>
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