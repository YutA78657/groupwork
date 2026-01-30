package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Category;

public class CategoriesDAO extends DAO{

	public List<Category>findAll(){
		load();
		List<Category> list = new ArrayList<>();

		String sql = "SELECT id ,category_name FROM category ORDER BY id";

		try (Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()){

			while(rs.next()) {
				Category category = new Category(rs.getInt("id"),rs.getString("category_name"));
				list.add(category);

			}
		} catch (SQLException e) {
			System.out.println("!");
			e.printStackTrace();
		}
		return list;

	}
	public boolean create(Category category) {
		load();

		String sql = "INSERT INTO category (category_name) VALUES(?)";

		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, category.getName());

			int result = ps.executeUpdate();
			return result == 1; // 1件登録できたらtrue

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Category category) {
		load();

		String sql = "UPDATE category SET category_name = ? WHERE id = ?";

		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement ps = conn.prepareStatement(sql)){

			ps.setString(1, category.getName());
			ps.setInt(2, category.getId());

			int result = ps.executeUpdate();
			return result == 1;//1件更新出来たらtrue

		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean delete(int id) {
		load();

		String sql = "DELETE FROM category WHERE id = ?";

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, id);
			int result = ps.executeUpdate();
			return result == 1; // 1件削除できたらtrue
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}



	}
}

