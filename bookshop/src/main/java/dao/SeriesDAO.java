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

		String sql = "SELECT id ,series_name FROM series ORDER BY id";

		try (Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()){

			while(rs.next()) {
				Series series = new Series(rs.getInt("id"),rs.getString("series_name"));
				list.add(series);


			}

		} catch (SQLException e) {
			System.out.print("!");
			e.printStackTrace();
		}
		return list;
	}	
	//idを指定してそのidだけを表示する処理（SELECT）
	public Series findById(int id) {
		load();

		String sql = "SELECT id, series_name FROM series WHERE id = ?";

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new Series(
							rs.getInt("id"),
							rs.getString("series_name")
							);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null; // 見つからなかったとき
	}
	// 追加：id と series_name を指定して登録（INSERT）
	public boolean insertWithId(Series series) {
		load();

		String sql = "INSERT INTO series (id, series_name) VALUES (?, ?)";

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, series.getId());
			ps.setString(2, series.getName());

			return ps.executeUpdate() == 1;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	// 追加：シリーズを削除（id指定）
	public boolean deleteById(int id) {
		load();

		String sql = "DELETE FROM series WHERE id = ?";

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, id);

			return ps.executeUpdate() == 1; // 1行消えたら成功

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	// 追加：id指定で series_name を更新（UPDATE）
	public boolean updateNameById(int id, String newName) {
		load();

		String sql = "UPDATE series SET series_name = ? WHERE id = ?";

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, newName); // ① 変更後の名前
			ps.setInt(2, id);         // ② 対象のid

			return ps.executeUpdate() == 1; // 1行更新できたら成功

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
