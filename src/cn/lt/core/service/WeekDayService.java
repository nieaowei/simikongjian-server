package cn.lt.core.service;

import java.util.List;

import cn.lt.core.po.Login;
import cn.lt.core.po.WeekDay;

public interface WeekDayService {
	

	public boolean addWeekDay(String shenfen, String birthday, String createby);
	
	public List<WeekDay> selectWeekDay(String createby);
	
	public List<WeekDay> selectWeekDayToday(String createby);
}
