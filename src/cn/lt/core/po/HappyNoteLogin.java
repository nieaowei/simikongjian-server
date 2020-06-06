package cn.lt.core.po;

import java.io.Serializable;

public class HappyNoteLogin implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String noteuser;
	public String notepass;
	public String getNoteuser() {
		return noteuser;
	}
	public void setNoteuser(String noteuser) {
		this.noteuser = noteuser;
	}
	public String getNotepass() {
		return notepass;
	}
	public void setNotepass(String notepass) {
		this.notepass = notepass;
	}
	

}
