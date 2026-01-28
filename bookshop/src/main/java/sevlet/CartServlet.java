package sevlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import logic.AddCart;
import logic.DeleteCart;
import logic.UpdateCart;
import model.Cart;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		request.getRequestDispatcher("WEB-INF/jsp/cart.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		List<Cart> cart = (List<Cart>)session.getAttribute("cart");;
		if(cart == null) {
			cart = new ArrayList<>();
		}
		String action = (String)request.getParameter("action");
		System.out.println(action);
		System.out.println(request.getParameter("pid"));
		
		if(action.equals("add")) {
			System.out.println("!!!");
			int pid = Integer.parseInt(request.getParameter("pid"));
			cart = AddCart.execute(cart, pid);
		}else if(action.equals("update")){
			String[] spid = request.getParameterValues("pid[]");
			String[] squantity = request.getParameterValues("quantity[]");
			int[] pid = new int[spid.length];
			int[] quantity = new int[squantity.length];
			for(int i = 0;i<pid.length;i++) {
				pid[i] = Integer.parseInt(spid[i]);
			}
			for(int i = 0;i<quantity.length;i++) {
				quantity[i] = Integer.parseInt(squantity[i]);
			}
			cart = UpdateCart.execute(pid, quantity);
		}else if(action.equals("delete")) {
			int pid = (int)request.getAttribute("pid");
			cart = DeleteCart.execute(cart,pid);
		}
		session.setAttribute("cart", cart);
		request.getRequestDispatcher("WEB-INF/jsp/cart.jsp").forward(request, response);
	}

}
