package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
		String[] spid = request.getParameterValues("pid[]");
		String[] squantity = request.getParameterValues("quantity[]");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loginUser");
		int[] pid = new int[spid.length];
		int[] quantity = new int[squantity.length];
		for(int i = 0;i<pid.length;i++) {
			pid[i] = Integer.parseInt(spid[i]);
			System.out.println(pid[i]);
		}
		for(int i = 0;i<quantity.length;i++) {
			quantity[i] = Integer.parseInt(squantity[i]);
			System.out.println(quantity[i]);
		}
		Boolean result = logic.StockCheckLogic.execute(pid, quantity);
		if(result == true) {
			Boolean r2 = logic.CheckLogic.execute(user.getId(),pid, quantity);
			if(r2 == true) {
				request.setAttribute("flg", "true");
				request.getRequestDispatcher("WEB-INF/jsp/check.jsp").forward(request, response);
			}
		}
		
		
	}

}
