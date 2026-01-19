package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeriesDAO {
	
	public List<Series>findAll(){
		List<Series> list = new ArrayList<>();
		
		String sql = "SELECT id ,name FROM categories ORDER BY id";

		try (Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()){

			while(rs.next()) {
				Series series = new Series();
				series.setId(rs.getInt("id"));
				series.setName(rs.getString("name"));
				list.add(series);
				
				

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

}