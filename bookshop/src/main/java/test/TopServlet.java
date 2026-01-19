package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Book;

/**
 * Servlet implementation class TopServlet
 */
@WebServlet("/index")
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Book book = new Book(1,"令和08-09年　応用情報技術者 試験によくでる問題集【科目A】",2640,100,"大滝　みや子","応用情報技術者の科目A試験（旧午前試験）によくでる問題を厳選し、ていねいに解説したテーマ別問題集です。最新の応用情報技術者本試験問題はもちろん、前身であるⅠ種、ソフトウェア開発技術者や高度試験出題問題など、広範な問題を徹底的に分析して頻出傾向問題を選り抜いています。解説には図解を多く配置し、あいまいな部分を残すことなく苦手分野を集中的にトレーニングできます。","資格・試験","技術評論社",false,"image/9784297153335_600.jpg");
		List<Book> books = new ArrayList<Book>();
		books.add(book);
		books.add(book);
		books.add(book);
		books.add(book);
		books.add(book);
		request.setAttribute("books", books);
		RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/jsp/top.jsp");
		dis.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
