package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.UsersDAO;
import model.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/admin/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersDAO dao = new UsersDAO();
		List<User> userList = dao.findAll();

		request.setAttribute("userList", userList);
		request.getRequestDispatcher("/WEB-INF/jsp/user.jsp")
		.forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mail = request.getParameter("mail");
		String action = request.getParameter("action");
		if(action.equals("view")) {
			UsersDAO dao = new UsersDAO();
			User user = dao.findByEmail(mail);
			request.setAttribute("searchUser", user);
			request.getRequestDispatcher("/WEB-INF/jsp/mypageM.jsp")
			.forward(request, response);
		}else if(action.equals("update")) {
			
		}

	}

}