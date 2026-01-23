package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

public class CategoriesDAO {

	public List<Category>findAll(){
		List<Category> list = new ArrayList<>();
		
		String sql = "SELECT id ,name FROM categories ORDER BY id";

		try (Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()){

			while(rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				list.add(category);
				
				

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}
}