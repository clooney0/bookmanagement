package cn.book.entity;

/**
 * t_log 日志表 字段
 * 
 * @author Administrator
 * 
 */

public class Log {
	
	private long id;
	private String log_username; // 用户名字
	private String log_bkname; // 图书名字
	private long log_bkstock; // 图书库存
	private String log_brotime; // 借书日期
	private String log_rtntime; // 还书日期

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLog_username() {
		return log_username;
	}

	public void setLog_username(String log_username) {
		this.log_username = log_username;
	}

	public String getLog_bkname() {
		return log_bkname;
	}

	public void setLog_bkname(String log_bkname) {
		this.log_bkname = log_bkname;
	}

	public long getLog_bkstock() {
		return log_bkstock;
	}

	public void setLog_bkstock(long log_bkstock) {
		this.log_bkstock = log_bkstock;
	}

	public String getLog_brotime() {
		return log_brotime;
	}

	public void setLog_brotime(String log_brotime) {
		this.log_brotime = log_brotime;
	}

	public String getLog_rtntime() {
		return log_rtntime;
	}

	public void setLog_rtntime(String log_rtntime) {
		this.log_rtntime = log_rtntime;
	}

	public Log() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Log(long id, String log_username, String log_bkname,
			long log_bkstock, String log_brotime, String log_rtntime) {
		super();
		this.id = id;
		this.log_username = log_username;
		this.log_bkname = log_bkname;
		this.log_bkstock = log_bkstock;
		this.log_brotime = log_brotime;
		this.log_rtntime = log_rtntime;
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", log_username=" + log_username
				+ ", log_bkname=" + log_bkname + ", log_bkstock=" + log_bkstock
				+ ", log_brotime=" + log_brotime + ", log_rtntime="
				+ log_rtntime + "]\n";
	}

}
