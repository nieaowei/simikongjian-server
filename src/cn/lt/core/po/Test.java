package cn.lt.core.po;

import java.io.Serializable;

public class Test implements Serializable{
	private static final long serialVersionUID = 1L;
//	主键
	private String id;
//	食品名称
	private String name;
//	价钱
	private String price;
//	备注
	private String msg;
//	时间
	private String date;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Food [id=" + id + ", name=" + name + ", price=" + price
				+ ", msg=" + msg + ", date=" + date + "]";
	}
	
}
