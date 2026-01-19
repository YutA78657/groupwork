package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;

public class UsersDAO extends DAO {

    // ログイン認証
    public User findByEmailAndPass(String email, String pass) {

        User user = null;

        String sql = "SELECT * FROM users WHERE email = ? AND pass = ?";

        try (Connection con = getConnection();
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

        String sql = "INSERT INTO users(email, pass, name, address, admin_flg) "
                   + "VALUES(?, ?, ?, ?, 0)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPass());
            ps.setString(3, user.getName());
            ps.setString(4, user.getAddress());

            int result = ps.executeUpdate();
            return result == 1;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
    // ユーザー情報取得
    public User findById(int id) {

        User user = null;
        String sql = "SELECT * FROM users WHERE id = ?";

        try (Connection con = getConnection();
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
    
}