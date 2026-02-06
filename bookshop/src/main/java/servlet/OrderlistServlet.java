package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.OrderSet;

/**
 * Servlet implementation class OrderlistServlet
 */
@WebServlet("/admin/order")
public class OrderlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderlistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<OrderSet> orderSets = logic.GetAllOrdersLogic.execute();
		request.setAttribute("orderSets", orderSets);
		RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/jsp/orderM.jsp");
		dis.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oidStr = request.getParameter("orderId");
		String status = request.getParameter("status");
		int oid = 0;
		if(oidStr != null) {
			oid = Integer.parseInt(oidStr);
		}
		boolean result = logic.OrderStatusChangeLogic.execute(oid,status);
		String mes = "";
		if(!result) {
			mes = "注文のステータス変更に失敗しました";
			request.setAttribute("mes",mes);
		}
		response.sendRedirect(request.getContextPath() + "/order");;
	}

}
