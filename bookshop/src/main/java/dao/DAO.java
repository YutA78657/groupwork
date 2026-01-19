package dao;

public class DAO {
	//データベース接続に使用する情報
	protected String JDBC_URL = "jdbc:mysql://localhost/example";
	protected String DB_USER = "root";
	protected String DB_PASS = "";
	
	public void load() {
		//JDBCドライバを読み込む
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			throw new IllegalStateException(
			"JDBCドライバを読み込みませんでした");
		}
	}
}
