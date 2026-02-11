package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import dao.ProductsDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Product;

/**
 * Servlet implementation class ProductRegisterServlet
 */
@WebServlet("/admin/productRegister")
@MultipartConfig
public class ProductRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/productRegister.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		boolean flg = true;

		Part filePart = request.getPart("image");
		String title = request.getParameter("title");
		String priceStr = request.getParameter("price");
		String stockStr = request.getParameter("stock");
		String author = request.getParameter("author");
		String description = request.getParameter("description");
		String publisher = request.getParameter("publisher");
		String categoryStr = request.getParameter("category");
		String fileName = filePart.getSubmittedFileName();
		
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
		if(flg) {
			int price = Integer.parseInt(priceStr);
			int stock = Integer.parseInt(stockStr);
			int category = Integer.parseInt(categoryStr);
				
			InputStream input = filePart.getInputStream();
			File file = new File("C:/git/groupwork/bookshop/src/main/webapp/image/" + fileName);
			try (FileOutputStream output = new FileOutputStream(file)) {
				input.transferTo(output);
			}

			// オブジェクト生成
			Product product = new Product(
					title,
					price,
					stock,
					fileName,
					author,
					description,
					publisher,
					category
					);

			// 登録処理
			ProductsDAO dao = new ProductsDAO();
			dao.create(product);

			// リダイレクト
			response.sendRedirect(request.getContextPath() + "/index");
		}else {
			request.setAttribute("title", title);
			request.setAttribute("author", author);
			request.setAttribute("publisher", publisher);
			request.setAttribute("price", priceStr);
			request.setAttribute("stock", stockStr);
			request.setAttribute("description",description);
			RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/jsp/productRegister.jsp");
			dis.forward(request, response);
		}

	}

}

