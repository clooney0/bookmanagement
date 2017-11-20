package cn.book.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.book.entity.Root;
import cn.book.util.JdbcUtil;

public class RootDao {
	/**
	 * 登录
	 *
	 * @param username,pwd
	 */
	JdbcUtil jdbc = new JdbcUtil();

	public RootDao() {
		jdbc.getConnection();
	}

	/**
	 * 登录
	 * 
	 * @param name,pwd
	 */
	public Root login(String name, String pwd) {

		String sql = "select * from t_root where root_name=? and root_password=?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(name);
		params.add(pwd);

		Root res;
		try {
			res = jdbc.findSimpleRefResult(sql, params, Root.class);
			jdbc.releaseConn();
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 注册
	 * 
	 * @param username,pwd
	 */
	public boolean addRoot(String username, String pwd) {
		String sql = "INSERT t_root(root_name,root_password)VALUES(?,?)";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(username);
		params.add(pwd);

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
	 * 查询单条记录
	 * @param username
	 */
	public Root queryByName(String name) {
		String sql = "select * from t_root where root_name=?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(name);

		Root obj;
		try {
			obj = jdbc.findSimpleRefResult(sql, params, Root.class);
			jdbc.releaseConn();
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 通过ID查询用户
	 * 
	 * @param id
	 * */
	public Root queryByID(Long id) {
		String sql = "select * from t_root where id=?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(id);

		Root obj;
		try {
			obj = jdbc.findSimpleRefResult(sql, params, Root.class);
			jdbc.releaseConn();
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 显示所有用户信息
	 */
	public List<Root> showAllInfo() {
		String sql = "select * from t_root";

		List<Root> list;
		try {
			list = jdbc.findMoreRefResult(sql, null, Root.class);
			jdbc.releaseConn();
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 删除用户信息
	 * 
	 */
	public boolean delRoot(long id) {
		String sql = "delete from t_root where id=?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(id);

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

	/**
	 * 修改用户姓名信息
	 * 
	 * */
	public boolean modifyRoot(String name, long id) {
		String sql = "update t_root set root_name=? where id=?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(name);
		params.add(id);
		try {
			boolean flag = jdbc.updateByPreparedStatement(sql, params);
			jdbc.releaseConn();
			return flag;
		} catch (SQLException e) {
			// e.printStackTrace();
			return false;
		}
	}

	/**
	 * 修改用户姓名信息
	 * 
	 * */
	public boolean modifyPwd(String pwd, long id) {
		String sql = "update t_root set root_password=? where id=?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(pwd);
		params.add(id);
		try {
			boolean flag = jdbc.updateByPreparedStatement(sql, params);
			jdbc.releaseConn();
			return flag;
		} catch (SQLException e) {
			// e.printStackTrace();
			return false;
		}
	}

}
