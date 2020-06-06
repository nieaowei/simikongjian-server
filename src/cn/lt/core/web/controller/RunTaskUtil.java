package cn.lt.core.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;

import cn.lt.core.po.Weather;
import cn.lt.core.service.UploadService;

public class RunTaskUtil extends TimerTask {

	@Autowired
	private UploadService uploadService;

	private static boolean isRunning = false;

	private ServletContext context = null;

	public RunTaskUtil() {
		super();
	}

	public RunTaskUtil(ServletContext context) {
		this.context = context;
	}

	@Override
	public void run() {
		if (!isRunning) {

			context.log("开始执行指定任务");
			/**
			 * 自己的业务代码
			 */
			try {
				System.out.println("hhhhhhhhhh");
				uploadService.delWeather();
				// List<Map<String,Object>> list =
				// WeatherUtils.getTodayWeather2();
				List<Weather> weatherList = WeatherUtils.getTodayWeather2();
				for (int i = 0; i < weatherList.size(); i++) {
					String date = weatherList.get(i).getDate();// 29
					String ymd = weatherList.get(i).getYmd();// 2020-03-29
					String high = weatherList.get(i).getHigh();// 高温 14℃
					String sunrise = weatherList.get(i).getSunrise();// 05:22
					String fx = weatherList.get(i).getFx();// 西南风
					String week = weatherList.get(i).getWeek();// 星期日
					String low = weatherList.get(i).getLow();// 低温 1℃
					String fl = weatherList.get(i).getFl();// 5-6级
					String sunset = weatherList.get(i).getSunset();// 17:57
					String aqi = weatherList.get(i).getAqi();// 58
					String type = weatherList.get(i).getType();// 晴
					String notice = weatherList.get(i).getNotice();// 愿你拥有比阳光明媚的心情

					uploadService.SaveWether(date, ymd, high, sunrise, fx,
							week, low, fl, sunset, aqi, type, notice);
				}
				// WeatherUtils.getTodayWeather2();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			isRunning = false;
			context.log("指定任务执行结束");
		} else {
			context.log("上一次任务执行还未结束");
		}

	}

}
