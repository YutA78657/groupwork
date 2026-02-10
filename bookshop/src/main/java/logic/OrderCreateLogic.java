package logic;

import java.time.LocalDate;

import dao.OrdersDAO;
import model.Orders;

public class OrderCreateLogic {

	public boolean execute(Orders orders) {

		// 配送予定日を決める（業務ルール）
		LocalDate deliveryDate = LocalDate.now().plusDays(2);
		orders.setDeliveryDate(deliveryDate);

		OrdersDAO dao = new OrdersDAO();
		return dao.insert(orders);
	}
}