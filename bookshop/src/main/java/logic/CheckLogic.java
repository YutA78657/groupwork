package logic;

import java.util.Random;

import dao.OrderItemsDAO;
import dao.OrdersDAO;
import dao.ProductsDAO;
import model.Product;

public class CheckLogic {
	public static Boolean execute(int uid,int[] pid,int[] quantity) {
		Boolean result = true;
		Random rand = new Random();
		int id = Integer.parseInt(String.format("%08d", rand.nextInt(100_000_000)));
		OrdersDAO od = new OrdersDAO();
		OrderItemsDAO oid = new OrderItemsDAO();
		ProductsDAO pd = new ProductsDAO();
		Boolean r1 = od.insert(id,uid);
		if(r1 == true) {			
			for(int i = 0;i<pid.length;i++) {
				Product p = pd.findById(pid[i]);
				Boolean r2 = oid.insert(id,pid[i],quantity[i],p.getPrice());
				if(r2 == false) {
					return false;
				}
			}
		}
		return result;
	}
}
