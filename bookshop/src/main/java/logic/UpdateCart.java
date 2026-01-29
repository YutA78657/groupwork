package logic;

import java.util.ArrayList;
import java.util.List;

import dao.ProductsDAO;
import model.Cart;
import model.Product;

public class UpdateCart {
	public static List<Cart> execute(int[] pid,int[] quantity){
		List<Cart> cartlist = new ArrayList<>();
		ProductsDAO pd = new ProductsDAO();
		for(int i = 0;i < pid.length;i++) {
			Product product = pd.findById(pid[i]);
			Cart cart = new Cart(product,quantity[i]);
			cartlist.add(cart);
		}
		return cartlist;
	}
}
