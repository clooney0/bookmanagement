package cn.book.entity;

/**
 * t_book 图书表 字段
 * 
 * @author Administrator
 * 
 */

public class Book {

	private long book_id; // 图书编号
	private String book_name; // 书名
	private long book_stock; // 图书库存
	private String book_category; // 图书分类
	private String book_detail; // 图书详情
	private String book_location; // 图书位置

	public long getBook_id() {
		return book_id;
	}

	public void setBook_id(long book_id) {
		this.book_id = book_id;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public long getBook_stock() {
		return book_stock;
	}

	public void setBook_stock(long book_stock) {
		this.book_stock = book_stock;
	}

	public String getBook_category() {
		return book_category;
	}

	public void setBook_category(String book_category) {
		this.book_category = book_category;
	}

	public String getBook_detail() {
		return book_detail;
	}

	public void setBook_detail(String book_detail) {
		this.book_detail = book_detail;
	}

	public String getBook_location() {
		return book_location;
	}

	public void setBook_location(String book_location) {
		this.book_location = book_location;
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(long book_id, String book_name, long book_stock,
			String book_category, String book_detail, String book_location) {
		super();
		this.book_id = book_id;
		this.book_name = book_name;
		this.book_stock = book_stock;
		this.book_category = book_category;
		this.book_detail = book_detail;
		this.book_location = book_location;
	}

	@Override
	public String toString() {
		return "Book [book_id=" + book_id + ", book_name=" + book_name
				+ ", book_stock=" + book_stock + ", book_category="
				+ book_category + ", book_detail=" + book_detail
				+ ", book_location=" + book_location + "]";
	}

}
