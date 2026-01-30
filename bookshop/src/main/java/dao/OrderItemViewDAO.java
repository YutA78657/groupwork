package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.OrderItemView;

public class OrderItemViewDAO extends DAO{
	public List<OrderItemView> findById(int orderId){
		List<OrderItemView> list = new ArrayList<>();
		load();
		String sql = "select oi.id as order_item_id,order_id,product_id,title,quantity,oi.price as oi_price,img from order_items oi,product p where oi.product_id = p.id  and oi.order_id = ?";
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1,orderId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				OrderItemView orderItemView = new OrderItemView(rs.getInt("order_item_id"),rs.getInt("order_id"),rs.getInt("product_id"),rs.getString("title"),rs.getInt("quantity"),rs.getInt("oi_price"),rs.getString("img"));
				list.add(orderItemView);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}

