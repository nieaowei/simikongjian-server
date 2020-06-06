package cn.lt.core.web.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.lt.core.po.UserInfo;
import cn.lt.core.po.Weather;

public class WeatherUtils {

	/**
	 * @return
	 * @Description:天气获取（可用）
	 */
	public static List<Weather> getTodayWeather2() throws IOException {
		List<Weather> weatherList = new ArrayList<Weather>();
		if (checkStatus()) {
			String cityCode = "101050101";
			URL url = new URL("http://t.weather.sojson.com/api/weather/city/"
					+ cityCode);
			
			URLConnection connectionData = url.openConnection();
			connectionData.setConnectTimeout(1000);
			// Map<String, Object> map = new HashMap<String, Object>();
			
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						connectionData.getInputStream(), "UTF-8"));
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = br.readLine()) != null)
					sb.append(line);
				String datas = sb.toString();
				JSONObject jsonData = JSONObject.fromObject(datas);
				String status = jsonData.getString("status");

				if (status.toString() == "200" || "200".equals(status.toString())) {
					JSONObject data = jsonData.getJSONObject("data");
					JSONArray forecastArray = data.getJSONArray("forecast");
					for (int i = 0; i < 7; i++) {
						JSONObject forecastObj = JSONObject
								.fromObject(forecastArray.getJSONObject(i));
						Weather weather = (Weather) JSONObject.toBean(forecastObj,
								Weather.class);
						weatherList.add(weather);
					}
				}
			} catch (SocketTimeoutException e) {
				/*
				 * Weather weather2 = new Weather(); weather2.setType("没联网想啥呢");
				 * weatherList.add(weather2); return weatherList;
				 */
			} catch (FileNotFoundException e) {
				/*
				 * Weather weather2 = new Weather(); weather2.setType("没联网想啥呢");
				 * weatherList.add(weather2); return weatherList;
				 */
			}
			return weatherList;
		}else {
			Weather weather2 = new Weather(); 
			weather2.setType("没联网想啥呢");
			weatherList.add(weather2); 
			return weatherList;
		}
	}

	public static Boolean checkStatus() {
		URL url = null;
		Boolean bon = false;
		try {
			url = new URL("http://baidu.com/");
			InputStream in = url.openStream();// 打开到此 URL 的连接并返回一个用于从该连接读入的
												// InputStream
			System.out.println("连接正常");
			in.close();// 关闭此输入流并释放与该流关联的所有系统资源。
			bon = true;
		} catch (IOException e) {
			System.out.println("无法连接到：" + url.toString());
			bon = false;
		}
		return bon;
	}
}
