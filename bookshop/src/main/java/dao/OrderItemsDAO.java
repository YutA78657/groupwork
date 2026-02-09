package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.OrderItem;

public class OrderItemsDAO extends DAO{
	// order_idで明細取得
	public List<OrderItem> findByOrderId(int order_id) {
		load();
		List<OrderItem> list = new ArrayList<>();

		String sql = "SELECT * FROM order_items WHERE order_id = ?";

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, order_id);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					OrderItem item = new OrderItem(
							rs.getInt("id"),
							rs.getInt("order_id"),
							rs.getInt("product_id"),
							rs.getInt("quantity"),
							rs.getInt("price"));
					list.add(item);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	//追加
	public boolean insert(OrderItem orderItems) {
		load();

		String sql = "INSERT INTO order_items(order_id, product_id, quantity, price) VALUES(?, ?, ?, ?)";

		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1,orderItems.getOrderId());
			ps.setInt(2,orderItems.getProductId());
			ps.setInt(3,orderItems.getQuantity());
			ps.setInt(4,orderItems.getPrice());

			int result = ps.executeUpdate();
			return result == 1;

		} catch (SQLException e) {
			return false;
		}

	}

	//削除
	public boolean delete(int id) {
		load();

		String sql = "DELETE FROM order_items WHERE id = ?";

		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);

			int result = ps.executeUpdate();
			return result == 1;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	//注文単位の合計金額の取得
	public int getTotalPrice(int order_id) {
		load();
		int totalPrice = 0;

		String sql = "SELECT SUM(price) FROM order_items WHERE order_id = ? ";

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, order_id);
			ResultSet rs = ps.executeQuery();
			totalPrice = rs.getInt("SUM(price)");
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalPrice;
	}
	
	
}
