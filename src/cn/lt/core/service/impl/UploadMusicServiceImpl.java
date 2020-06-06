package cn.lt.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.lt.core.dao.UploadDao;
import cn.lt.core.dao.UploadMusicDao;
import cn.lt.core.po.HappyNote;
import cn.lt.core.po.UploadMusic;
import cn.lt.core.service.UploadMusicService;

@Service("uploadMusicService")
@Transactional
public class UploadMusicServiceImpl implements UploadMusicService {
	@Autowired
	private UploadMusicDao uploadMusicDao;

	@Override
	public boolean insertUploadMusic(String uploadmusicurl, String uploadby,
			String uploaddt) {
		int check = 0;
		check = uploadMusicDao.insertUploadMusic(uploadmusicurl, uploadby, uploaddt);
		if (check>0) {
			return true;
		} 
		return false;
	}

	@Override
	public List<UploadMusic> selectMusic(String uploadby) {
		List<UploadMusic> login = uploadMusicDao.selectMusic(uploadby);
		
		return login;
	}

}
