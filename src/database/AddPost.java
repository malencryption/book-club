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

import accounts.Comment;
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
		int clubId = Integer.parseInt(request.getParameter("clubId"));
		request.setAttribute("clubId", clubId);
		request.getRequestDispatcher("/addPost.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		try {
			Post newPost = new Post();
			int accountId = (Integer) session
					.getAttribute("accountId");
			String content = request.getParameter("content");
			String title = request.getParameter("title");
			int clubId = Integer.parseInt(request.getParameter("clubId"));
			Date dt = new Date();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			String currentTime = sdf.format(dt);
			
			newPost.setTitle(title);
			newPost.setContent(content);
			newPost.setDate(currentTime);
			newPost.setAccountId(accountId);
			newPost.setClubId(clubId);
			
			Post.addPost(newPost);
			System.out.println("going to club view");
//			request.getRequestDispatcher("/ClubPosts").forward(request, response);
			response.sendRedirect("clubPosts?clubId=" + clubId);
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

}
