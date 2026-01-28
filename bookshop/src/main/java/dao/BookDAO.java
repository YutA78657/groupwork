package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Book;

public class BookDAO extends DAO{
	
	// recommend_flg = true の本を取得
	public List<Book> findRecommendBooks(int limit) {
		
		List<Book> list = new ArrayList<>();

		load();

		String sql = """
				SELECT
				        p.id,
				        p.title,
				        p.price,
				        p.stock,
				        p.author,
				        p.description,
				        c.category_name AS cname,
				        p.publisher,
				        p.recommend_flg,
				        p.img
				    FROM product p
				    JOIN category c ON p.category_id = c.id
				    WHERE p.recommend_flg = true
				    ORDER by id
				    LIMIT ?
				""";

		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, limit);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Book book = new Book(
						rs.getInt("id"),
						rs.getString("title"),
						rs.getInt("price"),
						rs.getInt("stock"),
						rs.getString("author"),
						rs.getString("description"),
						rs.getString("cname"),
						rs.getString("publisher"),
						rs.getBoolean("recommend_flg"),
						rs.getString("img")
						);

				list.add(book);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}


	// カテゴリ名でランダム取得
	public List<Book> findRandomByCategory(String categoryName, int limit) {

		List<Book> list = new ArrayList<>();

		load();

		String sql = """
				    SELECT
				        p.id,
				        p.title,
				        p.price,
				        p.stock,
				        p.author,
				        p.description,
				        c.category_name AS cname,
				        p.publisher,
				        p.recommend_flg,
				        p.img
				    FROM product p
				    JOIN category c ON p.category_id = c.id
				    WHERE c.category_name = ?
				    ORDER BY RAND()
				    LIMIT ?
				""";

		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, categoryName);
			ps.setInt(2, limit);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Book book = new Book(
						rs.getInt("id"),
						rs.getString("title"),
						rs.getInt("price"),
						rs.getInt("stock"),
						rs.getString("author"),
						rs.getString("description"),
						rs.getString("cname"),
						rs.getString("publisher"),
						rs.getBoolean("recommend_flg"),
						rs.getString("img")
						);
				
				list.add(book);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	// Search用
	public List<Book> search(String categoryName, String word) {

	    List<Book> list = new ArrayList<>();
	    load();

	    StringBuilder sql = new StringBuilder("""
	        SELECT
	            p.id,
	            p.title,
	            p.price,
	            p.stock,
	            p.author,
	            p.description,
	            c.category_name AS cname,
	            p.publisher,
	            p.recommend_flg,
	            p.img
	        FROM product p
	        JOIN category c ON p.category_id = c.id
	        WHERE 1=1
	    """);

	    if (categoryName != null) {
	        sql.append(" AND c.category_name = ? ");
	    }

	    if (word != null) {
	        sql.append(" AND (p.title LIKE ? OR p.author LIKE ?) ");
	    }

	    try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
	         PreparedStatement ps = con.prepareStatement(sql.toString())) {

	        int idx = 1;

	        if (categoryName != null) {
	            ps.setString(idx++, categoryName);
	        }

	        if (word != null) {
	            ps.setString(idx++, "%" + word + "%");
	            ps.setString(idx++, "%" + word + "%");
	        }

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            list.add(new Book(
	                rs.getInt("id"),
	                rs.getString("title"),
	                rs.getInt("price"),
	                rs.getInt("stock"),
	                rs.getString("author"),
	                rs.getString("description"),
	                rs.getString("cname"),
	                rs.getString("publisher"),
	                rs.getBoolean("recommend_flg"),
	                rs.getString("img")
	            ));
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}
}
