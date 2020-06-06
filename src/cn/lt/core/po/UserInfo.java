package cn.lt.core.po;

import java.io.Serializable;

public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String username;
	public String userheight;
	public String userweight;
	public String userfavorite;
	public String updatedt;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserheight() {
		return userheight;
	}
	public void setUserheight(String userheight) {
		this.userheight = userheight;
	}
	public String getUserweight() {
		return userweight;
	}
	public void setUserweight(String userweight) {
		this.userweight = userweight;
	}
	public String getUserfavorite() {
		return userfavorite;
	}
	public void setUserfavorite(String userfavorite) {
		this.userfavorite = userfavorite;
	}
	public String getUpdatedt() {
		return updatedt;
	}
	public void setUpdatedt(String updatedt) {
		this.updatedt = updatedt;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
