package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.UsersDAO;
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
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/jsp/userRegister.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

        // フォーム入力値取得
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String name = request.getParameter("name");

        // オブジェクト生成
        User user = new User(email, pass, name);
        user.setEmail(email);
        user.setPass(pass);
        user.setName(name);
        user.setAdminFlg(0);

        // 登録処理
        UsersDAO dao = new UsersDAO();
        dao.create(user);

        // リダイレクト
        response.sendRedirect(request.getContextPath() + "/top");
	}

}
