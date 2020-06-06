package cn.lt.core.po;

import java.io.Serializable;

public class UserShare implements Serializable {

	private static final long serialVersionUID = 1L;

	public String username;
	public String shares;
	public String sharesurl;
	public String sharesdt;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getShares() {
		return shares;
	}

	public void setShares(String shares) {
		this.shares = shares;
	}

	public String getSharesurl() {
		return sharesurl;
	}

	public void setSharesurl(String sharesurl) {
		this.sharesurl = sharesurl;
	}

	public String getSharesdt() {
		return sharesdt;
	}

	public void setSharesdt(String sharesdt) {
		this.sharesdt = sharesdt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
