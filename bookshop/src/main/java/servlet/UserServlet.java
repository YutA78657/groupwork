package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.UsersDAO;
import model.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		User loginUser = (User) session.getAttribute("loginUser");

		if (loginUser == null || !loginUser.isAdmin()) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}

		UsersDAO dao = new UsersDAO();
		List<User> userList = dao.findAll();

		request.setAttribute("userList", userList);
		request.getRequestDispatcher("/WEB-INF/jsp/user.jsp")
		.forward(request, response);
	}

}