package cn.lt.core.service;

import java.util.List;

import cn.lt.core.po.Upload;
import cn.lt.core.po.Weather;

public interface UploadService {
	
	public boolean insertUpload(String uploadimgurl, String uploadby, String uploaddt);

	public List<Upload> selectUpload(String uploadby);
	
	public List<Weather> selectWeather(String date);
	
	public void delWeather();

	public boolean SaveWether(String date, String ymd, String high,
			String sunrise, String fx, String week, String low, String fl,
			String sunset, String aqi, String type, String notice);
}
