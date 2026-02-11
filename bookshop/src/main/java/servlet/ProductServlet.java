package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import dao.BookDAO;
import dao.ProductsDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.Book;
import model.Product;
import model.User;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/product")
@MultipartConfig
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

		String idStr = request.getParameter("pid");
		if (idStr == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		int id = Integer.parseInt(idStr);


		BookDAO bd = new BookDAO();

		Book book = bd.findById(id);

		if (book == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		request.setAttribute("book", book);

		if (isAdmin) {
			request.getRequestDispatcher("WEB-INF/jsp/productM.jsp")
			.forward(request, response);
		} else {
			request.getRequestDispatcher("WEB-INF/jsp/product.jsp")
			.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		ProductsDAO dao = new ProductsDAO();
		BookDAO bd = new BookDAO();


		// ---------- 商品更新 ----------
		if (action.equals("update")) {
			Part filePart = request.getPart("image");
			String fileName = filePart.getSubmittedFileName();
			InputStream input = filePart.getInputStream();
			File file = new File("C:/git/groupwork/bookshop/src/main/webapp/image/" + fileName);
			int id = Integer.parseInt(request.getParameter("id"));

			String title = request.getParameter("title");
			String priceStr = request.getParameter("price");
			String stockStr = request.getParameter("stock");
			String author = request.getParameter("author");
			String description = request.getParameter("description");
			String publisher = request.getParameter("publisher");
			boolean flg = true;
			String mes1 = "";
			String mes2 = "";
			String mes3 = "";
			String mes4 = "";
			String mes5 = "";
			String mes6 = "";
			String mes7 = "";


			if(fileName.equals("")) {
				mes1 = "書影が入力されていません";
				flg = false;
			}
			if(title.equals("")) {
				mes2 = "タイトルが入力されていません";
				flg = false;
			}

			if(author.equals("")) {
				mes3 = "著者が入力されていません";
				flg = false;
			}

			if(publisher.equals("")) {
				mes4 = "出版社が入力されていません";
				flg = false;
			}
			if(priceStr.equals("")) {
				mes5 = "価格が入力されていません";
				flg = false;
			}

			if(stockStr.equals("")) {
				mes6 = "在庫数が入力されていません";
				flg = false;
			}

			if(description.equals("")) {
				mes7 = "商品詳細が入力されていません";
				flg = false;
			}

			request.setAttribute("mes1", mes1);
			request.setAttribute("mes2", mes2);
			request.setAttribute("mes3", mes3);
			request.setAttribute("mes4", mes4);
			request.setAttribute("mes5", mes5);
			request.setAttribute("mes6", mes6);
			request.setAttribute("mes7", mes7);
			Book book = bd.findById(id);
			if(flg) {
				
				if(fileName.equals("")) {
					fileName = book.getImg();
				}else {
					if (!file.exists()) {
						try (FileOutputStream output = new FileOutputStream(file)) {
							input.transferTo(output);
						}
					}
				}


				Product product = new Product(
						id,
						request.getParameter("title"),
						Integer.parseInt(request.getParameter("price")),
						Integer.parseInt(request.getParameter("stock")),
						fileName,
						request.getParameter("author"),
						request.getParameter("description"),
						request.getParameter("publisher"),
						Integer.parseInt(request.getParameter("category"))
						);


				// 更新後は再表示
				dao.update(product);
				book = bd.findById(id);
				request.setAttribute("book", book);
				request.getRequestDispatcher("/WEB-INF/jsp/productM.jsp")
				.forward(request, response);
				return;
			}else {
				request.setAttribute("book", book);
				RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/jsp/productM.jsp");
				dis.forward(request, response);
			}


		}

		// ---------- おすすめ切替 ----------
		if ("reco".equals(action)) {
			System.out.println("reco");
			int id = Integer.parseInt(request.getParameter("id"));
			dao.toggleRecommend(id);
			Book book = bd.findById(id);
			request.setAttribute("book", book);
			request.getRequestDispatcher("/WEB-INF/jsp/productM.jsp")
			.forward(request, response);
			return;
		}

		// ---------- 削除 ----------
		if (action.equals("delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			dao.delete(id);

			response.sendRedirect(request.getContextPath() + "/index");
			return;
		}
	}

}
