package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		User luser = (User)session.getAttribute("loginUser");
		if("update".equals(action)) {
			String name = request.getParameter("name");
			String mail = request.getParameter("mail");
			String addressNum = request.getParameter("address_number");
			String address1 = request.getParameter("address1");
			String address2 = request.getParameter("address2");
			String address3 = request.getParameter("address3");
			User user = new User(luser.getId(),mail,name,addressNum,address1,address2,address3);
			boolean result = logic.UserUpdateLogic.execute(user);
			if(result) {
				UsersDAO ud = new UsersDAO();
				user = ud.findById(user.getId());
				session.setAttribute("loginUser", user);
				response.sendRedirect(request.getContextPath() + "/mypage");
			}else {
				response.sendRedirect(request.getContextPath() + "/top");
			}
			
		}else if("reset".equals(action)){
			String currentPass = request.getParameter("currentPassword");
		    String newPass = request.getParameter("newPassword");
		    
		    UsersDAO dao = new UsersDAO();

		    // 現在のパスワードチェック
		    User checkUser = dao.findByEmailAndPass(luser.getEmail(), currentPass);
		    
		    if (checkUser == null) {
		        request.setAttribute("error", "現在のパスワードが違います");
		        request.getRequestDispatcher("WEB-INF/jsp/mypage.jsp")
		               .forward(request, response);
		        return;
		    }
		    
		    User updateUser = new User(luser.getId(), newPass);

		    
		    dao.updatePassword(updateUser);

		    // セッション更新
		    session.setAttribute("loginUser", dao.findById(luser.getId()));

		    response.sendRedirect(request.getContextPath() + "/mypage");
		    return;
		}else if("delete".equals(action)) {
			
			 UsersDAO dao = new UsersDAO();
			    dao.delete(luser.getId());

			    // セッション破棄
			    session.invalidate();

			    response.sendRedirect(request.getContextPath() + "/index");
			    return;
		}
	}

}
