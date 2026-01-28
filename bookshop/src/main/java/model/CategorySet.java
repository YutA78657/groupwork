package model;

import java.util.List;

public class CategorySet {
	private String categoryName;
	private List<Book> bookList;

	public CategorySet(String categoryName, List<Book> books) {
		this.categoryName = categoryName;
		this.bookList = books;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public List<Book> getBookList() {
		return bookList;
	}
}
