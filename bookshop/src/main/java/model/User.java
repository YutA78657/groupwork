package model;

import java.io.Serializable;

public class User implements Serializable {
	private int id;
    private String email;
    private String pass;
    private String name;
    private String address;
    private int adminFlg;
    
    // ログイン用
    public User(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }
    
    // DB取得用
    public User(int id, String email, String name, String address, int adminFlg) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.address = address;
        this.adminFlg = adminFlg;
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

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAdminFlg(int adminFlg) {
		this.adminFlg = adminFlg;
	}

	// User登録
    public User(String email, String pass, String name) {
        this.email = email;
        this.pass = pass;
        this.name = name;
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

	public String getAddress() {
		return address;
	}

	public int getAdminFlg() {
		return adminFlg;
	}

	public boolean isAdmin() {
        return adminFlg == 1;
    }
}
