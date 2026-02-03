package logic;

import java.util.List;

import dao.ProductsDAO;
import model.Cart;
import model.Product;

public class StockCheckLogic {
	public static Boolean execute(List<Cart> cartlist) {
		Boolean result = false;
		ProductsDAO pd = new ProductsDAO();
		for(Cart cart:cartlist) {
			Product product =  pd.findById(cart.getProduct().getId());
			if(product.getStock() < cart.getQuantity()) {
				return result;
			}
		}
		result = true;
		return result;
	}
}
