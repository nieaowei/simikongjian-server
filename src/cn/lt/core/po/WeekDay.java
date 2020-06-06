package cn.lt.core.po;

import java.io.Serializable;
import java.sql.Date;

public class WeekDay implements Serializable{
	private static final long serialVersionUID = 1L;
	public String shenfen;
	public String birthday;
	public String createby;
	public String getShenfen() {
		return shenfen;
	}
	public void setShenfen(String shenfen) {
		this.shenfen = shenfen;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getCreateby() {
		return createby;
	}
	public void setCreateby(String createby) {
		this.createby = createby;
	}
	
}
