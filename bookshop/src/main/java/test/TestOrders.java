package test;

import java.util.List;

import dao.OrdersDAO;
import model.Orders;

public class TestOrders {
    public static void main(String args[]) {
	    OrdersDAO od = new OrdersDAO();

	    //findAllのテスト
	    List<Orders> list = od.findAll();

	    for (Orders o : list) {
	    	System.out.println( o.getId() + " / " + o.getUserId() + " / " + o.getOrder_date() + " / " + o.getStatus());
	    }

	    // insert
	    Orders newOrder = new Orders( 0, 1, new java.sql.Date(System.currentTimeMillis()), "NEW" );

	    boolean inserted = od.insert(newOrder);
	    System.out.println(inserted ? "登録成功!" : "登録失敗…");


	    //更新処理
	    int updateId= list.get(list.size() - 1).getId();
	    System.out.println("更新対象ID = " + updateId);

	    boolean updated = od.statusChange(updateId, "!");

	    if (updated) {
	    	System.out.println("更新成功!");
	    } else {
	    	System.out.println("更新失敗…IDが存在しない");
	    }
/*
	    // 削除処理
	    if (list.size() > 0) {
	    	int deleteId = list.get(0).getId();
	    	System.out.println("削除対象ID = " + deleteId);

	    	boolean deleted = od.delete(deleteId);
	    	System.out.println(deleted ? "削除成功!" : "削除失敗…");
	    } else {
	    	System.out.println("削除対象データがありません");
	    }

	    list = od.findAll();
	    for (Orders o : list) {
	    	System.out.println( o.getId() + " / " + o.getUserId() + " / " + o.getOrder_date() + " / " + o.getStatus());
	    }
   
    */
    }
}


