package cn.lt.core.po;

import java.io.Serializable;

import javax.websocket.Decoder.Binary;

public class Upload implements Serializable {

	private static final long serialVersionUID = 1L;
	public String uploadimgurl;
	public String uploadby;
	public String uploaddt;
	public String getUploadimgurl() {
		return uploadimgurl;
	}
	public void setUploadimgurl(String uploadimgurl) {
		this.uploadimgurl = uploadimgurl;
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
