package cn.lt.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lt.core.po.Test;
import cn.lt.core.po.HappyNoteLogin;

public interface HappyNoteLoginDao {
	// 登录心情日记
		public List<HappyNoteLogin> happyNoteLogin(@Param("noteuser")String noteuser,@Param("notepass")String notepass);
		
		public int updateHappyNoteLogin(@Param("noteuser")String noteuser,@Param("notepass")String notepass);
}
