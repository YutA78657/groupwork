package model;

public class OrderItemView {
	private int id;		 //明説ID
	private int order_id;	 //注文ID
	private int product_id;	//商品ID
	private String name;
	private int quantity;	 //購入数量
	private int price;	//注文時の単価
	private String img;

	public OrderItemView(int id, int order_id, int product_id,String name, int quantity, int price,String img) {
		this.id = id;
		this.order_id = order_id;
		this.product_id = product_id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.img = img;
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
	
	public String getName(){
		return name;
	}
	public int getQuantity() {
		return quantity;
	}

	public int getPrice() {
		return price;
	}
	
	public String getImg() {
		return img;
	}
}
