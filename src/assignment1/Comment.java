package assignment1;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Comment
 */
@WebServlet("/Comment")
public class Comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Comment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ConnectionUtil util = new ConnectionUtil();
		Integer foo = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("id", request.getParameter("id"));
		request.setAttribute("url", "/comp3095/Comment?id=" + request.getParameter("id"));
		
		try {
			request.setAttribute("post",util.findPost(foo));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher requestDispatcher;
		requestDispatcher = request.getRequestDispatcher("/assignment2/Comment.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getParameter("id");
		request.getParameter("commentbody");
		
		Validations validator = new Validations();
		
		validator.checkPostTitle(request.getParameter("commentit"));
		
		request.setAttribute("errorComment", validator.checkComment(request.getParameter("commentbody")));
		
		HttpSession session = request.getSession(true);
		String user = session.getAttribute("user").toString();
		Integer x = Integer.parseInt(request.getParameter("id"));
		
		if(validator.validateComment())
		{
			
			try {
				ConnectionUtil util = new ConnectionUtil();
				util.insertComment(request.getParameter("commentbody"),session.getAttribute("user"), x);
				
				UtilityHelper helper = new UtilityHelper();
				
				try {
					request.setAttribute("createPost", helper.buildDivs(user));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				RequestDispatcher requestDispatcher;
				requestDispatcher = request.getRequestDispatcher("/assignment1/Posts.jsp");
				requestDispatcher.forward(request, response);
			
			}

			catch (Exception exc){
				exc.printStackTrace();
			}
			
		}
		else{
			ConnectionUtil util = new ConnectionUtil();
			request.setAttribute("id", x);
			try {
				request.setAttribute("post",util.findPost(x));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher requestDispatcher;
			requestDispatcher = request.getRequestDispatcher("assignment2/Comment.jsp");
			requestDispatcher.forward(request, response);
		}
		
	}

}
