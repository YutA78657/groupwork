package sevlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.BookDAO;
import dao.CategoriesDAO;
import model.Book;
import model.CategorySet;

/**
 * Servlet implementation class TopServlet
 */
@WebServlet("/index")
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BookDAO bookDao = new BookDAO();
		CategoriesDAO categoryDao = new CategoriesDAO();

		// おすすめ本
		List<Book> recBooks = bookDao.findRecommendBooks(10);
		request.setAttribute("recBooks", recBooks);

		// カテゴリ一覧取得
		List<model.Category> categories = categoryDao.findAll();
		List<CategorySet> categoryBooks = new ArrayList<>();

		for (model.Category c : categories) {
			List<Book> books =
					bookDao.findRandomByCategory(c.getName(), 20);

			if (!books.isEmpty()) {
				CategorySet set =
						new CategorySet(c.getName() + "Books", books);
				categoryBooks.add(set);
			}
		}

		request.setAttribute("CategoryBooks", categoryBooks);

		request.getRequestDispatcher("/WEB-INF/jsp/top.jsp")
		.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
