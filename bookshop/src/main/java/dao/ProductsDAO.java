package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Product;

public class ProductsDAO extends DAO{
	
	//商品一覧表示
	public List<Product> findAll() {

	    List<Product> list = new ArrayList<>();

	   load();

	    String sql = """
	    		SELECT id, title, price, stock, img, category_id, series_id 
	    		FROM product
	    		""";

	    try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
	         PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            Product p = new Product(
	                rs.getInt("id"),
	                rs.getString("title"),
	                rs.getInt("price"),
	                rs.getInt("stock"),
	                rs.getString("img"),
	                rs.getString("author"),
	                rs.getInt("category_id"),
	                rs.getInt("series_id")
	            );
	            list.add(p);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}
	
	//title, author, description, publisher検索(全体部分一致検索)
	public List<Product> findByword(String word) {

	    List<Product> list = new ArrayList<>();

	    load();

	    String sql = """
	        SELECT id, title, price, stock, img, category_id, series_id
	        FROM product
	        WHERE title LIKE ?
	        OR author LIKE ? 
	        OR IFNULL(description, '') LIKE ? 
	        OR publisher LIKE ?
	    		""";

	    try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, "%" + word + "%");
	        ps.setString(2, "%" + word + "%");
	        ps.setString(3, "%" + word + "%");
	        ps.setString(4, "%" + word + "%");

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            Product p = new Product(
	                rs.getInt("id"),
	                rs.getString("title"),
	                rs.getInt("price"),
	                rs.getInt("stock"),
	                rs.getString("img"),
	                rs.getString("author"),
	                rs.getInt("category_id"),
	                rs.getInt("series_id")
	            );
	            list.add(p);
	        }

	    } catch (Exception e) {
	    	e.printStackTrace();
	    }

	    return list;
	}

	
	
	//idによる取得
	public Product findById(int pid) {
		
		Product product = null;
		
		load();

	    String sql = "select * from product where id = ?";

	    try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, pid);
	        ResultSet rs = ps.executeQuery();
	        while(rs.next()) {
	        	product = new Product(
		                rs.getInt("id"),
		                rs.getString("title"),
		                rs.getInt("price"),
		                rs.getInt("stock"),
		                rs.getString("img"),
		                rs.getString("author"),
		                rs.getInt("category_id"),
		                rs.getInt("series_id")
		            );	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return product;
	}
	
	// 商品登録
	public boolean create(Product product) {
		
		
		load();

        String sql = """
        		INSERT INTO product(
        		title, price, stock, author, description, publisher, recommend_flg, img, category_id, series_id)
        		VALUES(?, ?, ?, ?, ?, ?, 0, ?, ?, ?)
        """;
        
        try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
                PreparedStatement ps = con.prepareStatement(sql)) {
        	
        	ps.setString(1, product.getTitle());
            ps.setInt(2, product.getPrice());
            ps.setInt(3, product.getStock());
            ps.setString(4, product.getAuthor());
            ps.setString(5, product.getDescription());
            ps.setString(6, product.getPublisher());
            ps.setString(7, product.getImg());
            ps.setInt(8, product.getCategory_id());
            ps.setInt(9, product.getSeries_id());
            
            int result = ps.executeUpdate();

            return result == 1;
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return false;
	}
	
	// 更新
	public boolean update(Product product) {

		load();

		String sql = """
				    UPDATE product
				    SET
				        title = ?,
				        price = ?,
				        stock = ?,
				        author = ?,
				        description = ?,
				        publisher = ?,
				        recommend_flg = ?,
				        img = ?,
				        category_id = ?,
				        series_id = ?
				    WHERE id = ?
				""";

		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, product.getTitle());
			ps.setInt(2, product.getPrice());
			ps.setInt(3, product.getStock());
			ps.setString(4, product.getAuthor());
			ps.setString(5, product.getDescription());
			ps.setString(6, product.getPublisher());
			ps.setInt(7, product.getRecommend_flg());
			ps.setString(8, product.getImg());
			ps.setInt(9, product.getCategory_id());
			ps.setInt(10, product.getSeries_id());
			ps.setInt(11, product.getId());

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

		String sql = "DELETE FROM product WHERE id = ?";

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
