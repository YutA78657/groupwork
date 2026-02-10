package dao;

import java.sql.Connection;
import java.sql.Date;
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

		String sql = "SELECT * FROM orders ORDER BY order_date desc";

		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()){

			while(rs.next()) {
				Orders orders = new Orders(
						rs.getInt("id"),
						rs.getInt("user_id"),
						rs.getDate("order_date"),
						rs.getString("address_number"),
						rs.getString("address1"),
						rs.getString("address2"),
						rs.getString("address3"),
						rs.getString("status"),
						rs.getDate("delivery_date").toLocalDate()
						);
				list.add(orders);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//id検索
	public List<Orders> findById(int id){
		load();
		List<Orders> list = new ArrayList<>();

		String sql = "SELECT * FROM orders where user_id = ? ORDER BY order_date desc";

		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
				PreparedStatement ps = conn.prepareStatement(sql);
				){
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Orders orders = new Orders(
						rs.getInt("id"), 
						rs.getInt("user_Id"),
						rs.getDate("order_date"),
						rs.getString("address_number"),
						rs.getString("address1"),
						rs.getString("address2"),
						rs.getString("address3"),
						rs.getString("status"),
						rs.getDate("delivery_date").toLocalDate()
						);
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
		
        String sql = "INSERT INTO orders(user_Id, order_date, delivery_date, address_number,address1,address2,address3, status) VALUES(?, CURRENT_TIMESTAMP,?,?,?,?,?,'発送準備中')";

        try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, order.getUserId());
            ps.setDate(2, Date.valueOf(order.getDeliveryDate()));
            ps.setString(3,order.getAddress_number());
            ps.setString(4,order.getAddress1());
            ps.setString(5,order.getAddress2());
            ps.setString(6,order.getAddress3());

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


