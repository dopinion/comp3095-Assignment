package assignment1;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;


public class UtilityHelper {
	
	
	//this one works
	public String buildDivs(Object user) throws ClassNotFoundException, SQLException
	{
		ConnectionUtil util = new ConnectionUtil();

		ArrayList<HashMap<String,Object>> postList = util.getPosts();
		ArrayList<HashMap<String,Object>> commentList = util.getComments();
		//return list.get(0).get("title").toString();
		
		
	
		//Create a String that creates a new Tr
		String tr = "<tr class='postTableRow'><td>";
		String div = "<div class='createPost'>";
		String h4 = "<h4 class='posth4'>";
		String table = "<table class='postTable'>";
		String finish = "";
		String removeLink ="";
		String commentLink ="";
		String commentBuild = "";
		
		/*
	 	<table id="postTable" border="1">
          ${createPost}
        </table>
		 */
		
		
		
		//loop all of the posts
		for(int i = 0; i < postList.size(); i++){
			
			
		//if the username in the post is equal to the session user, it will create a remove link.	
			if(postList.get(i).get("username").toString().equals(user.toString()))
			{
				removeLink = "<a class='commentOption' href='/comp3095/assignment1/Remove?id=" + postList.get(i).get("idPosts") + "&username=" +postList.get(i).get("username")+"'>|Remove</a>";
			}
			
			//Create a loop that creates comments
			for(int a = 0; a < commentList.size(); a++)
			{
				if(postList.get(i).get("idPosts").equals(commentList.get(a).get("idPosts")))
				{
					commentBuild += "<p class='commentText'>" +commentList.get(a).get("username") + " commented, \"" + commentList.get(a).get("body") + "\"" + commentList.get(a).get("date") + "</p><hr>";
				}
				
				
			}
			
				finish += table + tr + div + 
				h4 + postList.get(i).get("title").toString() + "</h4> " + "<p class='byText'>by " + postList.get(i).get("username") + postList.get(i).get("date")  +"</p><hr>"+ "<p class='postText'>" +
				postList.get(i).get("body") + "</p><hr>" +
			     commentBuild + "</td></tr>" + tr + 
			     " <a class='commentOption' href='/comp3095/assignment1/Comment?id=" + postList.get(i).get("idPosts") + "'>Comment</a>" 
				
				+ removeLink
						+ "</div></td></tr></table>";
				
				
			//set these values to null in order to use again 
			commentBuild = "";
			removeLink = "";
		}

		return finish;
		
	}
	
	//Specific Method that allows remove link for everything
	public String buildAdminDivs() throws ClassNotFoundException, SQLException
	{
		ConnectionUtil util = new ConnectionUtil();

		ArrayList<HashMap<String,Object>> postList = util.getPosts();
		ArrayList<HashMap<String,Object>> commentList = util.getComments();
		//return list.get(0).get("title").toString();
		
		
	
		//Create a String that creates a new Tr
		String tr = "<tr class='postTableRow'><td>";
		String div = "<div class='createPost'>";
		String h4 = "<h4 class='posth4'>";
		String table = "<table class='postTable'>";
		String finish = "";
		String removeLink ="";
		String commentLink ="";
		String commentBuild = "";

		
		//loop all of the posts
		for(int i = 0; i < postList.size(); i++){
			
		//Creates a remove link	
		removeLink = "<a class='commentOption' href='/comp3095/assignment1/RemovePostAdmin?id=" + postList.get(i).get("idPosts") + "&username=" +postList.get(i).get("username")+"'>|Remove</a>";
		
			
			//Create a loop that creates comments
			for(int a = 0; a < commentList.size(); a++)
			{
				if(postList.get(i).get("idPosts").equals(commentList.get(a).get("idPosts")))
				{
					commentBuild += "<p class='commentText'>" +commentList.get(a).get("username") + " commented, \"" + commentList.get(a).get("body") + "\"" + commentList.get(a).get("date") + "</p><hr>";
				}
				
				
			}
			
				finish += table + tr + div + 
				h4 + postList.get(i).get("title").toString() + "</h4> " + "<p class='byText'>by " + postList.get(i).get("username") + postList.get(i).get("date")  +"</p><hr>"+ "<p class='postText'>" +
				postList.get(i).get("body") + "</p><hr>" +
			     commentBuild + "</td></tr>" + tr + 
			     " <a class='commentOption' href='/comp3095/assignment1/Comment?id=" + postList.get(i).get("idPosts") + "'>Comment</a>" 
				
				+ removeLink
						+ "</div></td></tr></table>";
				
				
			//set these values to null in order to use again 
			commentBuild = "";
			removeLink = "";
		}

		return finish;
		
	}

	
}
