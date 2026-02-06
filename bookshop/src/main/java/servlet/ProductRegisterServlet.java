package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import dao.ProductsDAO;
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
		if(title.equals("")) {
			System.out.println("title");
			flg = false;
		}
		if(priceStr.equals("")) {
			System.out.println("price");
			flg = false;
		}
		if(stockStr.equals("")) {
			System.out.println("stock");
			flg = false;
		}
		if(author.equals("")) {
			System.out.println("author");
			flg = false;
		}
		if(description.equals("")) {
			System.out.println("descrption");
			flg = false;
		}
		if(publisher.equals("")) {
			System.out.println("publisher");
			flg = false;
		}
		if(categoryStr.equals("")) {
			System.out.println("category");
			flg = false;
		}
		if(fileName.equals("")) {
			System.out.println("file");
			flg = false;
		}
		if(flg) {
			int price = Integer.parseInt(priceStr);
			int stock = Integer.parseInt(stockStr);
			int category = Integer.parseInt(categoryStr);
				
			InputStream input = filePart.getInputStream();
			File file = new File("C:/git/bookshop/src/main/webapp/image/" + fileName);
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
			response.sendRedirect(request.getContextPath() + "/index");
		}

	}

}

