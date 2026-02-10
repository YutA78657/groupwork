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
		boolean flg = true;
		String mes = "";
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loginUser");
		if(user.getAddressNum() == null ||
				user.getAddress1() == null  ||
				user.getAddress2() == null  ||
				user.getAddress3() == null  ||
				user.getAddressNum().equals("") ||
				user.getAddress1().equals("")  ||
				user.getAddress2().equals("")  ||
				user.getAddress3().equals("") ) {
			flg = false;
			mes = "お届け先住所が未設定です";
		}
		if(flg) {
			@SuppressWarnings("unchecked")
			List<Cart> cartlist = (List<Cart>)session.getAttribute("cart");
			boolean result = logic.StockCheckLogic.execute(cartlist);
			if(result == true) {
				Boolean r2 = logic.CheckLogic.execute(user,cartlist);
				if(r2 == true) {
					mes = "商品の購入が完了しました";
					session.setAttribute("mes",mes);
					session.removeAttribute("cart");
					response.sendRedirect(request.getContextPath() + "/index");
				}
			}
		}else {
			request.setAttribute("mes",mes);
			request.getRequestDispatcher("/WEB-INF/jsp/checkPreview.jsp").forward(request, response);
		}





	}

}
