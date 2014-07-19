package database;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import accounts.Book;
import accounts.Club;
import accounts.ClubMember;

/**
 * Servlet implementation class AddGroup
 */
@WebServlet("/AddClub")
public class AddClub extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddClub() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// build addClub page
		ArrayList<Book> bookList = Book.getBookList();
		request.setAttribute("bookList", bookList);
		request.getRequestDispatcher("/addClub.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int accountId = (Integer) session.getAttribute("accountId");
		String name = request.getParameter("name");
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		
		Date dt = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String currentTime = sdf.format(dt);
		Club newClub= new Club();
		newClub.setBookId(bookId);
		newClub.setDateCreated(currentTime);
		newClub.setName(name);
		
		int clubId = Club.addClub(newClub);
		ClubMember.joinClub(accountId, clubId);
		response.sendRedirect("/clubPosts?clubId=" + clubId);

	}
}
