package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.UsersDAO;
import model.User;


@WebServlet("/admin/userpage")
public class UserpageServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		UsersDAO dao = new UsersDAO();
		User user = dao.findById(id);
		request.setAttribute("searchUser", user);
		request.getRequestDispatcher("/WEB-INF/jsp/userpage.jsp")
		.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("update")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String mail = request.getParameter("mail");
			String addressNum = request.getParameter("address_number");
			String address1 = request.getParameter("address1");
			String address2 = request.getParameter("address2");
			String address3 = request.getParameter("address3");
			int adminFlg = Integer.parseInt(request.getParameter("admin_flg"));
			User user = new User(id,mail,name,addressNum,address1,address2,address3,adminFlg);
			boolean result = logic.UserAdminUpdateLogic.execute(user);
			if(result) {
				UsersDAO ud = new UsersDAO();
				user = ud.findById(user.getId());
				response.sendRedirect(request.getContextPath() + "/admin/userpage?id=" + id);
			}else {
				response.sendRedirect(request.getContextPath() + "/top");
			}
			
		}
	}
}
