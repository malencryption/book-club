package database;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoadPosts
 */
@WebServlet("/LoadPosts")
public class LoadPosts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadPosts() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String file;
		String path = System.getenv("OPENSHIFT_DATA_DIR");
	      if (path == null) {
	         file = getServletContext().getRealPath("./post.txt");
	      } else {
	         file = path + "./post.txt";
	      }
		
		PostList postList = new PostList(file);
		
		ArrayList<Post> currentList = postList.getPostList();
		request.setAttribute("postList", currentList);
		
		System.out.println("Post List: " + postList.toString());

		request.getRequestDispatcher("viewposts.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
