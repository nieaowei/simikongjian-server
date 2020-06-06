package cn.lt.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.lt.core.dao.HappyNoteDao;
import cn.lt.core.dao.HappyNoteLoginDao;
import cn.lt.core.dao.WeekDayDao;
import cn.lt.core.po.HappyNote;
import cn.lt.core.po.HappyNoteLogin;
import cn.lt.core.po.WeekDay;
import cn.lt.core.service.HappyNoteService;
import cn.lt.core.service.WeekDayService;

@Service("happyNoteService")
@Transactional
public class HappyNoteServiceImpl implements HappyNoteService {

	@Autowired
	private HappyNoteDao happyNoteDao;

	@Override
	public boolean happyNoteWrite(String noteuser, String note, String writetime,String notetitle,String address,String weather) {
		int check = 0;
		check = happyNoteDao.happyNoteWrite(noteuser, note,writetime,notetitle,address,weather);
		if (check>0) {
			return true;
		} 
		return false;
	}

	@Override
	public List<HappyNote> happyNoteSelect(String noteuser) {
		List<HappyNote> login = happyNoteDao.happyNoteSelect(noteuser);
		
		return login;
	}

}
