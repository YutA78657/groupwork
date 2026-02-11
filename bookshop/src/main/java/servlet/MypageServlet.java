package servlet;

import java.io.IOException;

import dao.UsersDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/mypage")
public class MypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/jsp/mypage.jsp");
		dis.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		User luser = (User)session.getAttribute("loginUser");
		UsersDAO dao = new UsersDAO();
		String mes1  = "";
		String mes2  = "";
		boolean flg = true;
		// ---------- ユーザー更新 ----------済
		if (action.equals("update")) {
			String name = request.getParameter("name");
			String mail = request.getParameter("mail");
			if(name.equals("")) {
				mes1 = "ユーザネームが入力されていません";
				flg = false;
			}
			if(mail.equals("")) {
				mes2 = "メールアドレスが入力されていません";
				flg = false;
			}
			request.setAttribute("mes1", mes1);
			request.setAttribute("mes2", mes2);
			if(flg) {
				User user = new User(
						luser.getId(),
						request.getParameter("mail"),
						request.getParameter("name"),
						request.getParameter("address_number"),
						request.getParameter("address1"),
						request.getParameter("address2"),
						request.getParameter("address3")
						);

				dao.update(user);

				// 更新後は再表示
				session.setAttribute("loginUser", dao.findById(user.getId()));
				request.getRequestDispatcher("WEB-INF/jsp/mypage.jsp")
				.forward(request, response);
			}else {
				request.getRequestDispatcher("WEB-INF/jsp/mypage.jsp")
				.forward(request, response);
			}


			// ---------- パスワードリセット ----------
		}else if(action.equals("reset")){
			String currentPass = request.getParameter("currentPassword");
			String newPass = request.getParameter("newPassword");


			if (newPass.equals(currentPass)) {
				System.out.println("reset");
				User updateUser = new User(luser.getId(), newPass);


				dao.updatePassword(updateUser);

				// セッション更新
				session.setAttribute("loginUser", dao.findById(luser.getId()));

				response.sendRedirect(request.getContextPath() + "/mypage");
			}else {
				System.out.println("reset-error");
				request.getRequestDispatcher("WEB-INF/jsp/mypage.jsp")
				.forward(request, response);
			}
		}else if(action.equals("delete")) {
			dao.delete(luser.getId());

			// セッション破棄
			session.invalidate();

			response.sendRedirect(request.getContextPath() + "/index");
		}
	}
}

