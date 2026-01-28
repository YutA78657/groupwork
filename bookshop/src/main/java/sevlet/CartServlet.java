package sevlet;

import java.io.IOException;
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
		String action = (String)request.getAttribute("action");
		
		if(action == "add") {
			int pid = (int)request.getAttribute("pid");
			int quantity = (int)request.getAttribute("quantity");
			cart = AddCart.execute(cart, pid, quantity);
		}else if(action == "update"){
			int[] pid = (int[])request.getAttribute("pid");
			int[] quantity = (int[])request.getAttribute("quantity");
			cart = UpdateCart.execute(pid, quantity);
		}else if(action == "delete") {
			int pid = (int)request.getAttribute("pid");
			cart = DeleteCart.execute(cart,pid);
		}
		session.setAttribute("cart", cart);
		request.getRequestDispatcher("WEB-INF/jsp/cart.jsp").forward(request, response);
	}

}
