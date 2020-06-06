package cn.lt.core.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.lt.core.dao.WeekDayDao;
import cn.lt.core.po.Login;
import cn.lt.core.po.WeekDay;
import cn.lt.core.service.WeekDayService;
@Service("weekDayService")
@Transactional
public class WeekDayServiceImpl implements WeekDayService {
	@Autowired
	private WeekDayDao weekDayDao;

	@Override
	public boolean addWeekDay(String shenfen, String birthday, String createby) {
		int check = 0;
		check = weekDayDao.addWeekDay(shenfen, birthday,createby);
		if (check>0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public List<WeekDay> selectWeekDay(String createby) {
		
		List<WeekDay> login = weekDayDao.selectWeekDay(createby);
		
		return login;
	}
	@Override
	public List<WeekDay> selectWeekDayToday(String createby){
		SimpleDateFormat df = new SimpleDateFormat("MM-dd");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		List<WeekDay> login = weekDayDao.selectWeekDayToday(createby,"%"+df.format(new Date()));
		
		return login;
	}

}
