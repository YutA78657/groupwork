package model;

import java.sql.Date;

public class Orders {
	private int id;             		// 注文ID
	private int user_Id;        		// ユーザーID
	private Date order_date;			// 注文日時
	private String address_number;
	private String address1;
	private String address2;
	private String address3;
	private String status;			   	// 注文状態

	// 一覧・詳細取得用
	public Orders(int id, int user_Id, Date order_date,String address_number,String address1,String address2,String address3, String status) {
		this.id = id;
		this.user_Id = user_Id;
		this.order_date = order_date;
		this.address_number = address_number;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.status = status;
	}
	//登録用
	public Orders(int id,int user_Id,String address_number,String address1,String address2,String address3) {
		this.id = id;
		this.user_Id = user_Id;
		this.address_number = address_number;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
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

	
	public String getAddress_number() {
		return address_number;
	}
	public String getAddress1() {
		return address1;
	}
	public String getAddress2() {
		return address2;
	}
	public String getAddress3() {
		return address3;
	}
	public String getStatus() {
		return status;
	}
}


