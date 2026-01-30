package logic;

import java.util.ArrayList;
import java.util.List;

import dao.OrderItemsDAO;
import dao.OrdersDAO;
import model.OrderSet;
import model.Orders;

public class GetOrders {
	public static List<OrderSet> execute(int id) {
		List<OrderSet> orderSets = new ArrayList<>();
		OrdersDAO od = new OrdersDAO();
		OrderItemsDAO oid = new OrderItemsDAO();
		List<Orders> orderlist = od.findById(id);
		for(Orders order: orderlist) {
			OrderSet orderSet = new OrderSet(order,oid.findByOrderId(order.getId()));
			orderSets.add(orderSet);
		}
		return orderSets;
	}

}
