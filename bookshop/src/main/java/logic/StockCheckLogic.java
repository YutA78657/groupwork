package logic;

import dao.ProductsDAO;
import model.Product;

public class StockCheckLogic {
	public static Boolean execute(int[] pid,int[] quantity) {
		Boolean result = false;
		ProductsDAO pd = new ProductsDAO();
		for(int i = 0;i<pid.length;i++) {
			Product product =  pd.findById(pid[i]);
			if(product.getStock() < quantity[i]) {
				return result;
			}
		}
		result = true;
		return result;
	}
}
