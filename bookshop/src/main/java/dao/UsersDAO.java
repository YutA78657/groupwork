package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UsersDAO extends DAO {


	// ログイン認証
	public User findByEmailAndPass(String email, String pass) {

		load();

		User user = null;

		String sql = "SELECT * FROM users WHERE email = ? AND pass = ?";

		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, email);
			ps.setString(2, pass);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				user = new User(
						rs.getInt("id"),
						rs.getString("email"),
						rs.getString("name"),
						rs.getString("address_number"),
						rs.getString("address1"),
						rs.getString("address2"),
						rs.getString("address3"),
						rs.getInt("admin_flg")
						);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	// 新規登録
	public boolean create(User user) {

		load();

		String sql = "INSERT INTO users(email, pass, name, admin_flg) "
				+ "VALUES(?, ?, ?,0)";

		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPass());
			ps.setString(3, user.getName());

			int result = ps.executeUpdate();
			return result == 1;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	// ユーザー情報取得
	public User findById(int id) {

		load();

		User user = null;
		String sql = "SELECT * FROM users WHERE id = ?";

		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				user = new User(
						rs.getInt("id"),
						rs.getString("email"),
						rs.getString("name"),
						rs.getString("address_number"),
						rs.getString("address1"),
						rs.getString("address2"),
						rs.getString("address3"),
						rs.getInt("admin_flg")
						);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	// 全ユーザー情報取得
	public List<User> findAll() {

		List<User> userList = new ArrayList<>();

		load();

		String sql = "SELECT * FROM users ORDER BY id";

		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				User user = new User(
						rs.getInt("id"),
						rs.getString("email"),
						rs.getString("name"),
						rs.getString("address_number"),
						rs.getString("address1"),
						rs.getString("address2"),
						rs.getString("address3"),
						rs.getInt("admin_flg")
						);
				userList.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userList;
	}

	// 更新
	public boolean update(User user) {

		load();

		String sql = """
					    UPDATE users
				    SET
				        name = ?,
				        email = ?,
				        address_number = ?,
				        address1 = ?,
				        address2 = ?,
				        address3 = ?
				    WHERE id = ?
				""";

		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getAddressNum());
			ps.setString(4, user.getAddress1());
			ps.setString(5, user.getAddress2());
			ps.setString(6, user.getAddress3());
			ps.setInt(7, user.getId());
			
			int result = ps.executeUpdate();
			return result == 1;

		} catch (Exception e) {
			System.out.println("update_error");
			e.printStackTrace();
		}

		return false;
	}

	// パスワード変更
	public boolean updatePassword(User user) {

		load();

		String sql = """
				    UPDATE users
				    SET
				        pass = ?
				    WHERE id = ?
				""";

		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, user.getPass());
			ps.setInt(2, user.getId());

			int result = ps.executeUpdate();
			return result == 1;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}


	// 削除
	public boolean delete(int id) {

		load();

		String sql = "DELETE FROM users WHERE id = ?";

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
