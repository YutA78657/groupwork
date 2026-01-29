package test;

import java.util.List;

import dao.OrderItemsDAO;
import model.Order_item;

public class TestOrderitem {

	public static void main(String[] args) {

		OrderItemsDAO oi = new OrderItemsDAO();

		// order_id を指定
		List<Order_item> list = oi.findByOrderId(5);

		for (Order_item item : list) {
			System.out.println("id=" + item.getId() + ", product_id=" + item.getProductId() + ", quantity=" + item.getQuantity() + ", price=" + item.getPrice());
		}

		// insert
		Order_item newOrder_item = new Order_item( 0, 5, 2, 3, 1200);

		boolean inserted = oi.insert(newOrder_item);
		System.out.println(inserted ? "登録成功!" : "登録失敗…");


		// 削除処理
		if (list.size() > 0) {
			int deleteId = list.get(0).getId();
			System.out.println("削除対象ID = " + deleteId);

			boolean deleted = oi.delete(deleteId);
			System.out.println(deleted ? "削除成功!" : "削除失敗…");
		} else {
			System.out.println("削除対象データがありません");
		}

	    
	}
}