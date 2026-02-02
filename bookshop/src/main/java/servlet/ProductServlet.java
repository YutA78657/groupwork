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
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		boolean isAdmin = (loginUser != null && loginUser.isAdmin());

		String idStr = request.getParameter("id");
		if (idStr == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		int id = Integer.parseInt(idStr);

		ProductsDAO dao = new ProductsDAO();
		Product product = dao.findById(id);

		if (product == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		request.setAttribute("product", product);

		if (isAdmin) {
			request.getRequestDispatcher("WEB-INF/jsp/ProductM.jsp")
			.forward(request, response);
		} else {
			request.getRequestDispatcher("WEB-INF/jsp/Product.jsp")
			.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
		ProductsDAO dao = new ProductsDAO();

		// ---------- 商品更新 ----------
		if ("update".equals(action)) {

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

			dao.update(product);

			// 更新後は再表示
			request.setAttribute("product", dao.findById(product.getId()));
			request.getRequestDispatcher("WEB-INF/jsp/productM.jsp")
			.forward(request, response);
			return;
		}

		// ---------- おすすめ切替 ----------
		if ("recommend".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			dao.toggleRecommend(id);

			request.setAttribute("product", dao.findById(id));
			request.getRequestDispatcher("WEB-INF/jsp/productM.jsp")
			.forward(request, response);
			return;
		}

		// ---------- 削除 ----------
		if ("delete".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			dao.delete(id);

			response.sendRedirect("topM");
			return;
		}
	}

}
