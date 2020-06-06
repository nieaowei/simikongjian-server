package cn.lt.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lt.core.po.HappyNote;
import cn.lt.core.po.HappyNoteLogin;

public interface HappyNoteDao {
	

	public int happyNoteWrite(@Param("noteuser")String noteuser, @Param("note")String note, @Param("writetime")String writetime, @Param("notetitle")String notetitle,@Param("address")String address,@Param("weather")String weather);

	public List<HappyNote> happyNoteSelect(@Param("noteuser")String noteuser);
}
