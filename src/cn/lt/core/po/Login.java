package cn.lt.core.po;

import java.io.Serializable;

public class Login implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//public int userid;
	
	public String username;
	
	public String password;
	
	public String role;

//	public int getUserid() {
//		return userid;
//	}
//
//	public void setUserid(int userid) {
//		this.userid = userid;
//	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
