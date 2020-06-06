package cn.lt.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lt.core.po.Weather;

public interface SaveWetherDao {
	
	public void delWeather();
	
	public List<Weather> selectWeather(@Param("ymd")String ymd);

	public int SaveWether(@Param("date")String date, @Param("ymd")String ymd, @Param("high")String high, @Param("sunrise")String sunrise,
			@Param("fx")String fx, @Param("week")String week, @Param("low")String low, @Param("fl")String fl, @Param("sunset")String sunset,
			@Param("aqi")String aqi, @Param("type")String type, @Param("notice")String notice);
}
