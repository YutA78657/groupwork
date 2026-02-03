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
@WebServlet("/product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ユーザー情報取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		boolean isAdmin = (loginUser != null && loginUser.isAdmin());
		
		String idStr = request.getParameter("id");
		
		// 400 Bad Request
		if (idStr == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		int id = Integer.parseInt(idStr);

		ProductsDAO dao = new ProductsDAO();
		Product product = dao.findById(id);

		// 404 Not Found
		if (product == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		request.setAttribute("product", product);

		// admin判定
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

		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if (loginUser == null || !loginUser.isAdmin()) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}

		String action = request.getParameter("action");
		ProductsDAO dao = new ProductsDAO();

		switch(action) {
		case "update":
			int id = Integer.parseInt(request.getParameter("id"));
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
			request.setAttribute("product", dao.findById(id));
			request.getRequestDispatcher("WEB-INF/jsp/ProductM.jsp")
			.forward(request, response);
			break;

		case "recommend":
			int rid = Integer.parseInt(request.getParameter("id"));
			dao.toggleRecommend(rid);
			request.setAttribute("product", dao.findById(rid));
			request.getRequestDispatcher("WEB-INF/jsp/ProductM.jsp")
			.forward(request, response);
			break;

		case "delete":
			int did = Integer.parseInt(request.getParameter("id"));
			dao.delete(did);
			response.sendRedirect("top");
			break;

		default:
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
	    }
		
	}

}
