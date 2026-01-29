package model;

public class Product {
	private int id;
	private String title;
	private int price;
	private int stock;
	private String author;
	private String description;
	private String publisher;
	private int recommend_flg;
	private String img;
	private int category_id;
	private int series_id;
	
	//一覧表示
	public Product(int id, String title, int price, int stock, String img,String author, int category_id, int series_id) {
		this.id = id;
		this.title = title;
		this.price = price;
		this.stock = stock;
		this.img = img;
		this.author = author;
		this.category_id = category_id;
		this.series_id = series_id;
	}


	//DB取得
	public Product(int id, String title, int price, int stock,String author,   String description, String publisher, int recommend_flg, String img, int category_id, int series_id) {
		this(id, title, price, stock, img, author, category_id, series_id);
		this.description = description;
		this.publisher = publisher;
		this.recommend_flg = recommend_flg;
	}
	
	// 登録用
	public Product(String title, int price, int stock, String img, String author, String description, String publisher, int category_id) {
		this.title = title;
		this.price = price;
		this.stock = stock;
		this.img = img;
		this.author = author;
		this.description = description;
		this.publisher = publisher;
		this.category_id = category_id;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}


	public void setRecommend_flg(int recommend_flg) {
		this.recommend_flg = recommend_flg;
	}


	public void setImg(String img) {
		this.img = img;
	}


	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}


	public void setSeries_id(int series_id) {
		this.series_id = series_id;
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

	public String getPublisher() {
		return publisher;
	}

	public int getRecommend_flg() {
		return recommend_flg;
	}

	public String getImg() {
		return img;
	}
	
	public int getCategory_id() {
		return category_id;
	}

	public int getSeries_id() {
		return series_id;
	}
	
	public boolean isRecommend() {
        return recommend_flg == 1;
    }
}
