package facebook;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import accounts.ClubPosts;
import accounts.HomePosts;
import accounts.Post;
import accounts.UserAccount;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.auth.AccessToken;

/**
 * Servlet implementation class CallBack
 */
@WebServlet("/CallBack")
public class CallBack extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CallBack() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Facebook facebook = (Facebook)request.getSession().getAttribute("facebook");
		HttpSession session = request.getSession(true);
		String oauthCode = request.getParameter("code");
			
		try {
			AccessToken fbAccessToken = facebook.getOAuthAccessToken(oauthCode);
			session.setAttribute("fbAccessToken", fbAccessToken);
			String fbId = facebook.getId();
			UserAccount fbUser = UserAccount.getUserAccountByFbId(fbId);
			if (fbUser.getFbId() == null) {
				fbUser = UserAccount.addUserAccountWithFb(facebook);
			}
			ArrayList<Post> homePostList = HomePosts.getHomePosts();
			// set session variable
			session.setAttribute("accountId", fbUser.getAccountId());
			ArrayList<Post> clubPostList = ClubPosts.getClubPostsByAcct(fbUser.getAccountId());
			
			request.setAttribute("clubPostList", clubPostList);
			request.setAttribute("user", fbUser);
			request.setAttribute("homePostList", homePostList);
			
			
		} catch (FacebookException e) {
			e.printStackTrace();
		}
			
		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
