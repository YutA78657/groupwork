package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.User;

/**
 * Servlet implementation class UserRegisterServlet
 */
@WebServlet("/userRegister")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/jsp/userRegister.jsp");
		dis.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		User user = new User(email,pass,name);
		boolean result = logic.RegisterUser.execute(user);
		if(result) {
			user = logic.LoginLogic.execute(email, pass);
			HttpSession session = request.getSession();
			session.setAttribute("loginUser",user );
			response.sendRedirect(request.getContextPath() + "/index");
		}else {
			RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/jsp/userRegister.jsp");
			dis.forward(request, response);
		}
	}

}
