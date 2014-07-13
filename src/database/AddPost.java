package database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import accounts.Post;

/**
 * Servlet implementation class CreatePost
 */
@WebServlet("/AddPost")
public class AddPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddPost() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//			HttpSession session = request.getSession();
					
		try {
			Date today = new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	        
			String content = request.getParameter("newpost");
			// = sdf.format(today);
			
			
			// do these need to come from the jsp?
			int tempUId = Integer.parseInt(request.getParameter("userId"));
			int tempPId = Integer.parseInt(request.getParameter("postId"));

			String titlePost = request.getParameter("title");
			

			String file;
			String path = System.getenv("OPENSHIFT_DATA_DIR");
		      if (path == null) {
		         file = getServletContext().getRealPath("./post.txt");
		      } else {
		         file = path + "./post.txt";
		      }
			
			Post newPost = new Post(tempPId, titlePost, content, today, tempUId);
			PostList postList = new PostList(file);
			
			// postList.addPostToFile(newPost); // is somthing like this needed to add to the database?
			//ArrayList<Post> currentList = postList.getPostList();
			
			//request.setAttribute("postList", currentList);
			
			System.out.println("Post List: " + postList.toString());

			request.getRequestDispatcher("viewposts.jsp").forward(request, response);
//			response.sendRedirect("viewposts.jsp");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

		
	}

}
