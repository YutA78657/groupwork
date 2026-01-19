package dao;

import java.sql.Connection;
import java.sql.DriverManager;


public class DAO {
	protected Connection getconnection() throws Exception{
	//データベース接続に使用する情報
		String JDBC_URL = "jdbc:mysql://localhost/example";
		String DB_USER = "root";
		String DB_PASS = "";
		
			//JDBCドライバを読み込む
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch(ClassNotFoundException e) {
				throw new IllegalStateException(
				"JDBCドライバを読み込みませんでした");
			}
			//データベースへ接続
			return DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
	}
}
