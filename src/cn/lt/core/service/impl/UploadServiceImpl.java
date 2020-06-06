package cn.lt.core.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.lt.core.dao.SaveWetherDao;
import cn.lt.core.dao.UploadDao;
import cn.lt.core.po.HappyNote;
import cn.lt.core.po.Upload;
import cn.lt.core.po.Weather;
import cn.lt.core.service.UploadService;

@Service("uploadService")
@Transactional
public class UploadServiceImpl implements UploadService {
	@Autowired
	private UploadDao uploadDao;

	@Autowired
	private SaveWetherDao saveWetherDao;

	@Override
	public boolean insertUpload(String uploadimgurl, String uploadby,
			String uploaddt) {
		int check = 0;
		check = uploadDao.insertUpload(uploadimgurl, uploadby, uploaddt);
		if (check > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Upload> selectUpload(String uploadby) {
		List<Upload> login = uploadDao.selectUpload(uploadby);

		return login;
	}

	@Override
	public List<Weather> selectWeather(String date) {
		
		List<Weather> login = saveWetherDao.selectWeather(date);
		return login;
	}

	@Override
	public void delWeather() {
		saveWetherDao.delWeather();
	}

	@Override
	public boolean SaveWether(String date, String ymd, String high,
			String sunrise, String fx, String week, String low, String fl,
			String sunset, String aqi, String type, String notice) {
		int check = 0;
		check = saveWetherDao.SaveWether(date, ymd, high, sunrise, fx, week,low,fl,sunset,aqi,type,notice);
		if (check > 0) {
			return true;
		}
		return false;
	}

}
