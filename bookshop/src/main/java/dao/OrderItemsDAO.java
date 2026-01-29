package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Order_item;

public class OrderItemsDAO extends DAO{
	// order_idで明細取得
	public List<Order_item> findByOrderId(int order_id) {
		load();
		List<Order_item> list = new ArrayList<>();

		String sql = "SELECT * FROM order_item WHERE order_id = ?";

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, order_id);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Order_item item = new Order_item(
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
	public boolean insert(Order_item order_item) {
		load();

		String sql = "INSERT INTO order_item(order_id, product_id, quantity, price) VALUES(?, ?, ?, ?)";

		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, order_item.getOrderId());
			ps.setInt(2, order_item.getProductId());
			ps.setInt(3, order_item.getQuantity());
			ps.setInt(4, order_item.getPrice());

			int result = ps.executeUpdate();
			return result == 1;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	//削除
	public boolean delete(int id) {
		load();

		String sql = "DELETE FROM order_item WHERE id = ?";

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
}
