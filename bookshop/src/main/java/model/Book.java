package model;

public class Book {
	private int pid;
	private String title;
	private int price;
	private int stock;
	private String author;
	private String description;
	private String cname;
	private String publisher;
	private int recommend_flg;
	private String img;
	
	public Book(int pid,String title,int price,int stock,String author,String description,String cname,String publisher,int recommend_flg,String img) {
		this.pid = pid;
		this.title = title;
		this.price = price;
		this.stock = stock;
		this.author = author;
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

	public String getAuthor() {
		return author;
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

	public int getRecommend_flg() {
		return recommend_flg;
	}
	public String getImg() {
		return img;
	}
	
	public boolean isRecommend() {
		return recommend_flg == 1;
	}
}

