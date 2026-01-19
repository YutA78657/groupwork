package model;

public class Book {
	private int pid;
	private String title;
	private int price;
	private int stock;
	private String auther;
	private String description;
	private String cname;
	private String publisher;
	private boolean recommend_flg;
	private String img;
	
	public Book(int pid,String title,int price,int stock,String auther,String description,String cname,String publisher,boolean recommend_flg,String img) {
		this.pid = pid;
		this.title = title;
		this.price = price;
		this.stock = stock;
		this.auther = auther;
		this.description = description;
		this.cname = cname;
		this.publisher = publisher;
		this.recommend_flg = recommend_flg;
		this.img = img;
		
	}

	public int getPid() {
		return pid;
	}

	public String getTitle() {
		return title;
	}

	public int getPrice() {
		return price;
	}

	public int getStock() {
		return stock;
	}

	public String getAuther() {
		return auther;
	}

	public String getDescription() {
		return description;
	}

	public String getCname() {
		return cname;
	}

	public String getPublisher() {
		return publisher;
	}

	public boolean getRecommend_flg() {
		return recommend_flg;
	}
}

