package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Series;



public class SeriesDAO extends DAO{
	
	public List<Series>findAll(){
		load();
		List<Series> list = new ArrayList<>();
		
		String sql = "SELECT id ,name FROM categories ORDER BY id";

		try (Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()){
			
			

			while(rs.next()) {
				Series series = new Series(rs.getInt("id"),rs.getString("name"));
				list.add(series);
				
				

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

}