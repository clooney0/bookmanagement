package cn.book.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.book.entity.Book;
import cn.book.util.JdbcUtil;

public class BookDao {

	// 创建一个JDBC工具对象
	private JdbcUtil util = new JdbcUtil();

	public BookDao() {
		util.getConnection();
	}

	// // 添加书籍
	// public List<Object> querybooks() {
	// // TODO Auto-generated method stub
	// return null;
	// }

	// 查询所有书籍信息
	public List<Book> queryallbook() {
		String sql = "SELECT * FROM t_book ";
		ArrayList<Object> params = new ArrayList<Object>();

		List<Book> list;
		try {
			list = util.findMoreRefResult(sql, params, Book.class);
			util.releaseConn();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 添加书籍 新增
	public boolean addBook(Book book) {

		String sql = "INSERT INTO t_book (book_name,book_stock,book_category,book_detail,book_location) VALUES(?,?,?,?,?)";

		ArrayList<Object> params = new ArrayList<Object>();
		params.add(book.getBook_name());
		params.add(book.getBook_stock());
		params.add(book.getBook_category());
		params.add(book.getBook_detail());
		params.add(book.getBook_location());

		boolean flag;
		try {
			flag = util.updateByPreparedStatement(sql, params);
			util.releaseConn();
			return flag;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	// 模糊查询所有数据
	public List<Book> queryBookByLike(String textstr) {
		String sql = "SELECT * FROM t_book WHERE book_name like ? or book_category like ? "
				+ "or book_detail like ? or book_location like ?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(textstr);
		params.add(textstr);
		params.add(textstr);
		params.add(textstr);
		List<Book> list;
		try {
			list = util.findMoreRefResult(sql, params, Book.class);
			util.releaseConn();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 按照书名查询
	public List<Book> queryBookByBookname(String bookname) {
		String sql = "SELECT * FROM t_book WHERE book_name =? ";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(bookname);
		List<Book> list;
		try {
			list = util.findMoreRefResult(sql, params, Book.class);
			util.releaseConn();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	

	// 删除book
	public boolean deleteBook(long id) {
		String sql = "delete from t_book where book_id = ?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(id);
		boolean flag;
		try {
			flag = util.updateByPreparedStatement(sql, params);
			util.releaseConn();
			return flag;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	// 更新book
	public boolean updateBook(Book book) {
		String sql = "update t_book set book_name=?,book_stock=?,book_category=?,book_detail=?,book_location=? where book_id =?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(book.getBook_name());
		params.add(book.getBook_stock());
		params.add(book.getBook_category());
		params.add(book.getBook_detail());
		params.add(book.getBook_location());
		params.add(book.getBook_id());
		boolean flag;

		try {
			flag = util.updateByPreparedStatement(sql, params);
			util.releaseConn();
			return flag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
	
	
	
	

}
