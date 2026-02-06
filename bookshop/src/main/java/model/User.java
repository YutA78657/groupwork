package model;

import java.io.Serializable;

public class User implements Serializable {
	private int id;
    private String email;
    private String pass;
    private String name;
    private String addressNum;
    private String address1;
    private String address2;
    private String address3;
    private int adminFlg;
    
    // ログイン用
    public User(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }
    
    // DB取得用
    public User(int id, String email, String name, String addressNum,String address1,String address2,String address3, int adminFlg) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.addressNum = addressNum;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.adminFlg = adminFlg;
    }
    // User登録
    public User(String email, String pass, String name) {
        this.email = email;
        this.pass = pass;
        this.name = name;
    }
    //update用
    public User(int id,String email, String name, String addressNum,String address1,String address2,String address3) {
    	this.id = id;
        this.email = email;
        this.name = name;
        this.addressNum = addressNum;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
    }
    
    // パスワード変更用
    public User(int id, String pass) {
        this.id = id;
        this.pass = pass;
    }
    

	public void setId(int id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddressNum(String addressNum) {
		this.addressNum = addressNum;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public void setAdminFlg(int adminFlg) {
		this.adminFlg = adminFlg;
	}

	public boolean isAdmin() {
        return adminFlg == 1;
    }

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPass() {
		return pass;
	}

	public String getName() {
		return name;
	}

	public String getAddressNum() {
		return addressNum;
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

	public int getAdminFlg() {
		return adminFlg;
	}
}
