package model;

public class OrderItem {
	private int id;		 //明説ID
	private int order_id;	 //注文ID
	private int product_id;	//商品ID
	private int quantity;	 //購入数量
	private int price;		 //注文時の単価

	public OrderItem(int id, int order_id, int product_id, int quantity, int price) {
		this.id = id;
		this.order_id = order_id;
		this.product_id = product_id;
		this.quantity = quantity;
		this.price = price;
	}
	
//insert
	public OrderItem(int order_id, int product_id, int quantity, int price) {
		this.order_id = order_id;
		this.product_id = product_id;
		this.quantity = quantity;
		this.price = price;
	}


	public int getId() {
		return id;
	}

	public int getOrderId() {
		return order_id;
	}

	public int getProductId() {
		return product_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getPrice() {
		return price;
	}
}
