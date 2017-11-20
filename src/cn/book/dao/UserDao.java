package cn.book.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import cn.book.entity.User;
import cn.book.util.JdbcUtil;

public class UserDao {

	private JdbcUtil util = new JdbcUtil();

	public UserDao() {
		util.getConnection();
	}

	// 添加读者
	public boolean addUser(User user)  {

		String sql = "INSERT t_user (user_name,user_gender,user_phone) VALUES(?,?,?)";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(user.getUser_name());
		params.add(user.getUser_gender());
		params.add(user.getUser_phone());
		boolean is;
		try {
			is = util.updateByPreparedStatement(sql, params);
			util.releaseConn();
			return is;
		} catch (SQLException e) {
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, "手机号码存在！");
			return false;
		}
		
	}

	// 查询全部读者
	public List<User> queryUser()  {
		String sql = "SELECT * FROM t_user ";
		ArrayList<Object> params = new ArrayList<Object>();

		List<User> list;
		try {
			list = util.findMoreRefResult(sql, params, User.class);
			util.releaseConn();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	// 查询模糊读者名字
	public List<User> queryUserByname(String name)  {
		String sql = "SELECT * FROM t_user WHERE user_name LIKE ?	 ";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(name);
		List<User> list;
		try {
			list = util.findMoreRefResult(sql, params, User.class);
			util.releaseConn();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	// 查询模糊读者手机号
	public List<User> queryUserByphone(String shoujihao)  {
		String sql = "SELECT * FROM t_user WHERE user_phone LIKE ?	 ";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(shoujihao);
		List<User> list;
		try {
			list = util.findMoreRefResult(sql, params, User.class);
			util.releaseConn();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	// 删除读者信息
	public boolean deleteUser(Long user_id)  {
		String sql = "DELETE FROM t_user WHERE user_id=? ";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(user_id);
		try {
			boolean is = util.updateByPreparedStatement(sql, params);
			util.releaseConn();
			return is;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public boolean updateUser(User user) {
		String sql ="Update t_user Set user_name=?,user_gender=?,user_phone=? Where user_id =? ";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(user.getUser_name());
		params.add(user.getUser_gender());
		params.add(user.getUser_phone());
		params.add(user.getUser_id());
		
		try {
			boolean flag = util.updateByPreparedStatement(sql, params);
			util.releaseConn();
			return flag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, "手机号已存在！");
			return false;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
