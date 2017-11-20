package cn.book.entity;

/**
 * t_root 管理员表 字段
 * 
 * @author Administrator
 * 
 */

public class Root {
	private long id;
	private String root_name; // 管理员账户名
	private String root_password; // 管理员密码

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoot_name() {
		return root_name;
	}

	public void setRoot_name(String root_name) {
		this.root_name = root_name;
	}

	public String getRoot_password() {
		return root_password;
	}

	public void setRoot_password(String root_password) {
		this.root_password = root_password;
	}

	public Root() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Root(long id, String root_name, String root_password) {
		super();
		this.id = id;
		this.root_name = root_name;
		this.root_password = root_password;
	}

	@Override
	public String toString() {
		return "Root [id=" + id + ", root_name=" + root_name
				+ ", root_password=" + root_password + "]";
	}

}
