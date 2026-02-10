package logic;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import dao.OrderItemsDAO;
import dao.OrdersDAO;
import dao.ProductsDAO;
import model.Cart;
import model.OrderItem;
import model.Orders;
import model.Product;
import model.User;

public class CheckLogic {
	public static Boolean execute(User user,List<Cart> cartlist) {
		Boolean result = true;
		Random rand = new Random();
		Boolean flg = true;
		OrdersDAO od = new OrdersDAO();
		OrderItemsDAO oid = new OrderItemsDAO();
		ProductsDAO pd = new ProductsDAO();
		int id = 0;
		
		while(flg) {
			StringBuilder sb = new StringBuilder(8);
			for (int i = 0; i < 8; i++) {
				sb.append(rand.nextInt(10)); // 0〜9
			}
			id = Integer.parseInt(sb.toString());
			List<Orders> list = od.findById(id);
			if(list.size() == 0) {
				flg = false;
			}
		}
         
        Orders order = new Orders(id,user.getId(),user.getAddressNum(),user.getAddress1(),user.getAddress2(),user.getAddress3());
        LocalDate deliveryDate = LocalDate.now().plusDays(2);
		order.setDeliveryDate(deliveryDate);
		
		Boolean r1 = od.insert(order);
		if(r1 == true) {			
			for(Cart cart:cartlist) {
				Product p = pd.findById(cart.getProduct().getId());
				OrderItem orderItem = new OrderItem(id,p.getId(),cart.getQuantity(),p.getPrice());
				Boolean r2 = oid.insert(orderItem);
				if(r2 == false) {
					return false;
				}
				Boolean r3 = pd.stockChange(cart.getProduct().getId(),p.getStock()-cart.getQuantity());
				if(r3 == false) {
					return false;
				}
			}
		}
		return result;
	}
}
