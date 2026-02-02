package servlet;

import java.io.IOException;
import java.util.List;

import dao.UsersDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.User;

/**
 * Servlet implementation class CheckPreviewServlet
 */
@WebServlet("/checkPreview")
public class CheckPreviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckPreviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<Cart> cart = (List<Cart>)session.getAttribute("cart");
		if(cart == null) {
			response.sendRedirect(request.getContextPath() + "/cart");
		}else {
			User user = (User)session.getAttribute("loginUser");
			UsersDAO ud = new UsersDAO();
			user = ud.findById(user.getId());
			session.setAttribute("loginUser", user);
			request.getRequestDispatcher("/WEB-INF/jsp/checkPreview.jsp")
			.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
