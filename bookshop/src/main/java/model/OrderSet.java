package model;

import java.util.List;

public class OrderSet {
		private Orders order;
		private List<OrderItem> orderItem;
		public OrderSet(Orders order,List<OrderItem> orderItem) {
			this.order = order;
			this.orderItem = orderItem;
		}
		public Orders getOrder() {
			return order;
		}
		public List<OrderItem> getOrderItem() {
			return orderItem;
		}
}
