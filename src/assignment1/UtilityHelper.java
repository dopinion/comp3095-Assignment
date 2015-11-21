package assignment1;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class UtilityHelper {
	
	//this method will build a div and populate posts and comments into it.
	/*
	 * 
	 * 
	 * <tr>
	 * 	<div>
	 * 		<h2>Title</h2> 
	 * 		<p class='postHeader'> by Author - Timestamp</p><br>
	 * 		Body
	 * 	</div>
	 * </tr>
	 * 
	 * 
	 * 
	 * 
	 * */
	public String buildDiv() throws ClassNotFoundException, SQLException
	{
		ConnectionUtil util = new ConnectionUtil();
		
		util.getPost();
		ArrayList<Object[]> result = util.getPost();
		
		Object[]bar = result.toArray(new Object[result.size()]);
	
		//Create a String that creates a new Tr
		String tr = "<tr class='postTableRow'>";
		String div = "<div class='createPost'>";
		String h1 = "<h1>";
		
		String finish = tr + div + 
				h1 + Arrays.deepToString(bar) + "</h1></div>";
		
		return finish;
	}
	
	//this one works
	public String buildDivs() throws ClassNotFoundException, SQLException
	{
		ConnectionUtil util = new ConnectionUtil();

		ArrayList<HashMap<String,Object>> list = util.getPosts();
		//return list.get(0).get("title").toString();
		
		
	
		//Create a String that creates a new Tr
		String tr = "<tr class='postTableRow'><td>";
		String div = "<div class='createPost'>";
		String h2 = "<h2>";
		String finish = "";
		
		//loop all of the posts
		for(int i = 0; i < list.size(); i++){
		
				finish += tr + div + 
				h2 + list.get(i).get("title").toString() + "</h2> " + "by " + list.get(i).get("username")+ "<br>" +
				list.get(i).get("body")
						+ "</div></tr>";
		}
		
		return finish;
		
	}

	
}
