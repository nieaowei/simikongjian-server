package cn.lt.core.po;

import java.io.Serializable;

public class HappyNote implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String noteuser;
	public String notetitle;
	public String note;
	public String writetime;
	public String address;
	public String weather;
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String notetitle) {
		this.address = notetitle;
	}
	
	public String getWeather() {
		return weather;
	}
	public void setWeather(String notetitle) {
		this.weather = notetitle;
	}
	
	public String getNotetitle() {
		return notetitle;
	}
	public void setNotetitle(String notetitle) {
		this.notetitle = notetitle;
	}
	public String getNoteuser() {
		return noteuser;
	}
	public void setNoteuser(String noteuser) {
		this.noteuser = noteuser;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getWritetime() {
		return writetime;
	}
	public void setWritetime(String writetime) {
		this.writetime = writetime;
	}
	
	
	
}
