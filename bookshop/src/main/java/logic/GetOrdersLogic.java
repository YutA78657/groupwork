package logic;

import java.util.ArrayList;
import java.util.List;

import dao.OrderItemViewDAO;
import dao.OrdersDAO;
import model.OrderSet;
import model.Orders;

public class GetOrdersLogic {
	public static List<OrderSet> execute(int id) {
		List<OrderSet> orderSets = new ArrayList<>();
		OrdersDAO od = new OrdersDAO();
		OrderItemViewDAO oivd = new OrderItemViewDAO();
		List<Orders> orderlist = od.findById(id);
		for(Orders order: orderlist) {
			OrderSet orderSet = new OrderSet(order,oivd.findById(order.getId()));
			orderSets.add(orderSet);
		}
		return orderSets;
	}

}
