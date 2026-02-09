package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.OrderSet;
import model.User;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/order")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loginUser");
		List<OrderSet> orderSets =  logic.GetOrdersLogic.execute(user.getId());
		request.setAttribute("orderSets", orderSets);
		RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/jsp/order.jsp");
		dis.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oidStr = request.getParameter("orderId");
		int oid = 0;
		if(oidStr != null) {
			oid = Integer.parseInt(oidStr);
		}
		boolean result = logic.OrderStatusChangeLogic.execute(oid,"キャンセル");
		String mes = "";
		if(!result) {
			mes = "注文のキャンセルに失敗しました";
			request.setAttribute("mes",mes);
		}
		response.sendRedirect(request.getContextPath() + "/order");;
	}

}
