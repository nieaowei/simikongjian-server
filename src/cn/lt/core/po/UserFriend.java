package cn.lt.core.po;

import java.io.Serializable;

public class UserFriend implements Serializable {

	private static final long serialVersionUID = 1L;

	public String username;
	public String friendname;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFriendname() {
		return friendname;
	}

	public void setFriendname(String friendname) {
		this.friendname = friendname;
	}

}
