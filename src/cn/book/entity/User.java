package cn.book.entity;

/**
 * t_user 用户表 字段
 * 
 * @author Administrator
 * 
 */

public class User {
	private long user_id;  //用户id
	private String user_name;  //用户姓名
	private String user_gender; //用户性别
	private String user_phone;  //用户电话

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_gender() {
		return user_gender;
	}

	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(long user_id, String user_name, String user_gender,
			String user_phone) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_gender = user_gender;
		this.user_phone = user_phone;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_name=" + user_name
				+ ", user_gender=" + user_gender + ", user_phone=" + user_phone
				+ "]";
	}

}
