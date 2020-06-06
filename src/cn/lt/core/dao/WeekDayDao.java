package cn.lt.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lt.core.po.Login;
import cn.lt.core.po.WeekDay;

public interface WeekDayDao {
	// ע��
		public int addWeekDay(@Param("shenfen")String shenfen,@Param("birthday")String birthday, @Param("createby")String createby);
		
		public List<WeekDay> selectWeekDay(@Param("createby")String createby);
		
		public List<WeekDay> selectWeekDayToday(@Param("createby")String createby,@Param("birthday")String birthday);
}
