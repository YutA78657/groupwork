package model;

import java.util.List;

public class OrderSet {
		private Orders order;
		private List<OrderItemView> orderItem;
		public OrderSet(Orders order,List<OrderItemView> orderItem) {
			this.order = order;
			this.orderItem = orderItem;
		}
		public Orders getOrder() {
			return order;
		}
		public List<OrderItemView> getOrderItem() {
			return orderItem;
		}
}
