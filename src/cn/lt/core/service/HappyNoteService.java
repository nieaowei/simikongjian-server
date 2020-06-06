package cn.lt.core.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lt.core.po.HappyNote;

public interface HappyNoteService {
	

	public boolean happyNoteWrite(String noteuser, String note, String writetime,String notetitle,String address,String weather);

	public List<HappyNote> happyNoteSelect(String noteuser);
}
