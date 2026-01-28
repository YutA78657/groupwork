package logic;

import java.util.List;

import dao.ProductsDAO;
import model.Cart;
import model.Product;

public class AddCart {
		public static List<Cart> execute(List<Cart> cartlist,int pid,int quantity){
			boolean flg = true;
			for(Cart cart:cartlist) {
				if(cart.getProduct().getId() == pid) {
					cart.addQuantity(quantity);
					flg = false;
					break;
				}
			}
			
			if(flg) {
				ProductsDAO pd = new ProductsDAO();
				Product product = pd.findById(pid);
				Cart cart = new Cart(product,quantity);
				cartlist.add(cart);
			}
			return cartlist;
		}

}
