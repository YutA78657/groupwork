package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.User;

/**
 * Servlet implementation class CheckServlet
 */
@WebServlet("/check")
public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<Cart> cartlist = (List<Cart>)session.getAttribute("cart");
		User user = (User)session.getAttribute("loginUser");
		boolean result = logic.StockCheckLogic.execute(cartlist);
		if(result == true) {
			Boolean r2 = logic.CheckLogic.execute(user,cartlist);
			if(r2 == true) {
				session.removeAttribute("cart");
				request.setAttribute("flg", "true");
				request.getRequestDispatcher("WEB-INF/jsp/check.jsp").forward(request, response);
			}
		}
		
		
	}

}
