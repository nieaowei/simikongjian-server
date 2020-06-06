package cn.lt.core.po;

import java.io.Serializable;

public class UserFoot implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String username;
	public String userfoot;
	public String dt;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserfoot() {
		return userfoot;
	}
	public void setUserfoot(String userfoot) {
		this.userfoot = userfoot;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	
}
