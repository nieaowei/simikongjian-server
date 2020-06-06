package cn.lt.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.lt.core.dao.HappyNoteLoginDao;
import cn.lt.core.po.HappyNoteLogin;
import cn.lt.core.service.HappyNoteLoginService;
@Service("happyNoteLoginService")
@Transactional
public class HappyNoteLoginServiceImpl implements HappyNoteLoginService {
	@Autowired
	private HappyNoteLoginDao happyNoteLoginDao;
	
	@Override
	public boolean happyNoteLogin(String noteuser, String notepass) {
		List<HappyNoteLogin> login = happyNoteLoginDao.happyNoteLogin(noteuser, notepass);
		if (login!=null&&login.size()>0) {
			return true;
		} 
		return false;
	}

	@Override
	public boolean updateHappyNoteLogin(String noteuser,String notepass) {
		int check = 0;
		check = happyNoteLoginDao.updateHappyNoteLogin(noteuser,notepass);
		if (check>0) {
			return true;
		} 
		return false;
	}
}
