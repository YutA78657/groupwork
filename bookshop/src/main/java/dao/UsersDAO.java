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
    	
    	try {
			load();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
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
                    rs.getString("pass"),
                    rs.getString("name"),
                    rs.getString("address"),
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
    	
    	try {
			load();
		} catch (Exception e) {
			e.printStackTrace();
		}

        String sql = "INSERT INTO users(email, pass, name, admin_flg) "
                   + "VALUES(?, ?, ?,0)";

        try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPass());
            ps.setString(3, user.getName());
            //ps.setString(4, user.getAddress());

            int result = ps.executeUpdate();
            return result == 1;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
    // ユーザー情報取得
    public User findById(int id) {
    	
    	try {
			load();
		} catch (Exception e) {
			e.printStackTrace();
		}

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
                    rs.getString("pass"),
                    rs.getString("name"),
                    rs.getString("address"),
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

    	try {
    		load();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}

    	String sql = "SELECT * FROM users ORDER BY id";

    	try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
    			PreparedStatement ps = con.prepareStatement(sql);
    			ResultSet rs = ps.executeQuery()) {

    		while (rs.next()) {
    			User user = new User(
    					rs.getInt("id"),
    					rs.getString("email"),
    					rs.getString("pass"),
    					rs.getString("name"),
    					rs.getString("address"),
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
    	
    	try {
			load();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	String sql = """
			    UPDATE users
			    SET
			        pass = ?,
			        name = ?,
			        address = ?,
			        admin_flg = ?
			""";
    	
    	try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, user.getPass());
			ps.setString(2, user.getName());
			ps.setString(3, user.getAddress());
			ps.setInt(4, user.getAdminFlg());
			
			int result = ps.executeUpdate();
			return result == 1;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
    
    // 削除
    public boolean delete(int id) {

		try {
			load();
		} catch (Exception e) {
			e.printStackTrace();
		}

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
