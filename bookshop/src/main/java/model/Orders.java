package model;

import java.sql.Date;

public class Orders {
	private int id;             		// 注文ID
	private int user_Id;        		// ユーザーID
	private Date order_date;			// 注文日時
	private String status;			   	// 注文状態

	// 一覧・詳細取得用
	public Orders(int id, int user_Id, Date order_date, String status) {
		this.id = id;
		this.user_Id = user_Id;
		this.order_date = order_date;
		this.status = status;
	}
	//登録用
	public Orders(int user_Id, Date order_date) {
		this.user_Id = user_Id;
		this.order_date = order_date;
	}

	// getter / setter
	public int getId() {
		return id;
	}

	public int getUserId() {
		return user_Id;
	}

	public Date getOrder_date() {
		return order_date;
	}

	public String getStatus() {
		return status;
	}
}


