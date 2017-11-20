package cn.book.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import cn.book.entity.Log;
import cn.book.util.JdbcUtil;

public class LogDao {

	private JdbcUtil jdbc = new JdbcUtil();

	public LogDao() {
		jdbc.getConnection();
	}

	/**
	 * 查询日志表全部
	 * 
	 * @throws Exception
	 * 
	 */
	public List<Log> showAllLog() {
		String sql = "select * from t_log";
		List<Log> list;
		try {
			list = jdbc.findMoreRefResult(sql, null, Log.class);
			jdbc.releaseConn();
			return list;
		} catch (Exception e) {
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, "图书库存不能为空！！");
			return null;
		}

	}

	/**
	 * 日志查询   借阅人姓名模糊查询
	 * 
	 * @throws Exception
	 */
	public List<Log> queryLogByusername(Log log) {
		String sql = "select * from t_log where log_username like ?";
		List<Object> params = new ArrayList<Object>();
		params.add(log.getLog_username());
		List<Log> list;
		try {
			list = jdbc.findMoreRefResult(sql, params, Log.class);
			jdbc.releaseConn();
			return list;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}

	}
	
	/**
	 * 日志查询   书名模糊查询
	 * 
	 * @throws Exception
	 */
	public List<Log> queryLogBybookname(Log log) {
		String sql = "select * from t_log where log_bkname like ? ";
		List<Object> params = new ArrayList<Object>();
		params.add(log.getLog_bkname());
		List<Log> list;
		try {
			list = jdbc.findMoreRefResult(sql, params, Log.class);
			jdbc.releaseConn();
			return list;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}

	}

	/**
	 * 日志按编号删除
	 * 
	 * @throws SQLException
	 * 
	 */
	public boolean deleteLog(long id) {
		String sql = "delete from t_log where id = ?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(id);
		boolean flag;
		try {
			flag = jdbc.updateByPreparedStatement(sql, params);
			jdbc.releaseConn();
			return flag;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 添加日志 (借阅信息)
	 * 
	 * @throws SQLException
	 * 
	 */
	public boolean addLog(Log log) {
		String sql = "Insert t_log(log_username,log_bkname,log_brotime,log_rtntime) Values(?,?,?,null)";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(log.getLog_username());
		params.add(log.getLog_bkname());
		params.add(log.getLog_brotime());
		boolean flag;
		try {
			flag = jdbc.updateByPreparedStatement(sql, params);
			jdbc.releaseConn();
			return flag;
		} catch (SQLException e) {
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, "登记失败,借阅人或书名不存在！");
			return false;
		}
	}

	// 借阅成功 库存-1
	public boolean reduceBookStock(String bookname) {
		String sql = "Update t_book Set book_stock=book_stock-1 Where book_name = ?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(bookname);

		try {
			boolean flag = jdbc.updateByPreparedStatement(sql, params);
			jdbc.releaseConn();
			return flag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	// 归还成功 库存+1
	public boolean addBookStock(String bookname) {
		String sql = "Update t_book Set book_stock=book_stock+1 Where book_name = ?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(bookname);
		try {
			boolean flag = jdbc.updateByPreparedStatement(sql, params);
			jdbc.releaseConn();
			return flag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}


	// 修改log_bkstock 日志表图书库存
	public boolean updateLogStock(Log log) {
		String sql = "Update t_log set log_bkstock =? Where log_bkname = ?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(log.getLog_bkstock());
		params.add(log.getLog_bkname());
		try {
			boolean flag = jdbc.updateByPreparedStatement(sql, params);
			jdbc.releaseConn();
			return flag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	// 修改log
	public boolean updateLog(Log log) {
		String sql = "update t_log set log_username=?,log_bkname=?,log_brotime=?,log_rtntime=? where id =?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(log.getLog_username());
		params.add(log.getLog_bkname());
		params.add(log.getLog_brotime());
		params.add(log.getLog_rtntime());
		params.add(log.getId());
		boolean flag;
		try {
			flag = jdbc.updateByPreparedStatement(sql, params);
			jdbc.releaseConn();
			return flag;
		} catch (SQLException e) {
			if (e instanceof MySQLIntegrityConstraintViolationException) {
		          // duplicate record or alike problem
				 JOptionPane.showMessageDialog(null, "借阅人或书籍不存在！！");
		    }
			return false;
		}
	}
}
