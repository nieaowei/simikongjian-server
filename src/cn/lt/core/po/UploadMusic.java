package cn.lt.core.po;

import java.io.Serializable;

public class UploadMusic implements Serializable {

	private static final long serialVersionUID = 1L;
	public String uploadmusicurl;
	public String uploadby;
	public String uploaddt;
	public String getUploadmusicurl() {
		return uploadmusicurl;
	}
	public void setUploadmusicurl(String uploadmusicurl) {
		this.uploadmusicurl = uploadmusicurl;
	}
	public String getUploadby() {
		return uploadby;
	}
	public void setUploadby(String uploadby) {
		this.uploadby = uploadby;
	}
	public String getUploaddt() {
		return uploaddt;
	}
	public void setUploaddt(String uploaddt) {
		this.uploaddt = uploaddt;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
