package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Orders;

public class OrdersDAO extends DAO {

	public List<Orders>findAll(){
		load();
		List<Orders> list = new ArrayList<>();

		String sql = "SELECT id, user_Id, order_date, status FROM orders ORDER BY id";

		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()){

			while(rs.next()) {
				Orders orders = new Orders(rs.getInt("id"), 
						rs.getInt("user_Id"),
						rs.getDate("order_date"),
						rs.getString("status"));
				list.add(orders);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//追加
	public boolean insert(Orders order) {
		load();
		
        String sql = "INSERT INTO orders(user_Id, order_date, status) VALUES(?, ?, ?)";

        try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, order.getUserId());
            ps.setDate(2, order.getOrder_date());
            ps.setString(3, order.getStatus());

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

		String sql = "DELETE FROM orders WHERE id = ?";

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
	
	//ステータス変更
	public boolean statusChange(int id, String status) {
		load();
		
		String sql = "UPDATE orders SET status = ? WHERE id = ?";
		
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement ps = con.prepareStatement(sql)) {
			
			ps.setString(1, status);
			ps.setInt(2, id);
			
			int result = ps.executeUpdate();
			return result == 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}


