package logic;

import dao.OrdersDAO;

public class OrderStatusChangeLogic {
	public static boolean execute(int oid,String status) {
		boolean result = false;
		OrdersDAO od = new OrdersDAO();
		result = od.statusChange(oid,status);
		return result;
	}
}
