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
