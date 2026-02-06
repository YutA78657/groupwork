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
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
		UsersDAO dao = new UsersDAO();

		// ---------- ユーザー更新 ----------済
		if ("update".equals(action)) {

			User user = new User(
					Integer.parseInt(request.getParameter("id")),
					request.getParameter("name"),
					request.getParameter("email"),
					request.getParameter("address_num"),
					request.getParameter("address1"),
					request.getParameter("address2"),
					request.getParameter("address3")
					);

			dao.update(user);

			// 更新後は再表示
			request.setAttribute("user", dao.findById(user.getId()));
			request.getRequestDispatcher("WEB-INF/jsp/mypage.jsp")
			.forward(request, response);
			return;
		}

		// ---------- パスワードリセット ----------

		// ---------- 削除 ----------
		if ("delete".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			dao.delete(id);

			response.sendRedirect("index");
			return;
		}
	}

}