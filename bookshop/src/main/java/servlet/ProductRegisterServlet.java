package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.ProductsDAO;
import model.Product;
import model.User;

/**
 * Servlet implementation class ProductRegisterServlet
 */
@WebServlet("/ProductRegisterServlet")
public class ProductRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		// 管理者以外は弾く
		if (loginUser == null || !loginUser.isAdmin()) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}

		request.getRequestDispatcher("WEB-INF/jsp/ProductRegister.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if (loginUser == null || !loginUser.isAdmin()) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}

		Product product = new Product(
				request.getParameter("title"),
				Integer.parseInt(request.getParameter("price")),
				Integer.parseInt(request.getParameter("stock")),
				request.getParameter("author"),
				request.getParameter("description"),
				request.getParameter("publisher"),
				request.getParameter("img"),
				Integer.parseInt(request.getParameter("categoryId")),
				Integer.parseInt(request.getParameter("seriesId"))
				);

		ProductsDAO dao = new ProductsDAO();
		dao.create(product);

		// 登録後は管理者トップへ
		response.sendRedirect("top");
	}

}
