package database;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import accounts.Comment;
import accounts.Post;

/**
 * Servlet implementation class ViewPost
 */
@WebServlet("/ViewPost")
public class ViewPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewPost() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// get post and comments
		int postId = Integer.parseInt(request.getParameter("postId"));
		Post post = Post.getPostById(postId);
System.out.println("getting comments");
		ArrayList<Comment> commentList = Comment.getCommentsByPostId(postId);
		request.setAttribute("commentList", commentList);
		request.setAttribute("post", post);
		request.getRequestDispatcher("/viewPost.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
