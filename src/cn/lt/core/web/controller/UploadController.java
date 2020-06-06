package cn.lt.core.web.controller;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.it.core.config.Config;
import cn.lt.core.po.Upload;
import cn.lt.core.po.Weather;
import cn.lt.core.service.UploadService;

@Controller
public class UploadController {
	@Autowired
	private UploadService uploadService;

	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
	@ResponseBody
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		InputStream is = request.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		String username = request.getParameter("username");

		String result = "";
		try {
			result = saveFile(dis,username);
			System.out.println("upload image success");
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
			result = "uploaderror";
		}
		request.getSession().invalidate();
		response.setContentType("text/html;charset=UTF-8");
		ObjectOutputStream dos = new ObjectOutputStream(
				response.getOutputStream());
		dos.writeObject(result);
		dos.flush();
		dos.close();
		dis.close();
		is.close();
	}

	@RequestMapping(value = "/selectImage", method = RequestMethod.POST)
	@ResponseBody
	public String selectImage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
//		List<String> imageList = new ArrayList<String>();
		String res = "";
		List<Upload> uploads = uploadService.selectUpload(username);
		for (Upload upload : uploads) {
			res+=upload.getUploadimgurl()+",";
		}
//		imageList = PictureUtils.readPicToBytes();
		return res;
	}

	@RequestMapping(value = "/selectCloths", method = RequestMethod.POST)
	@ResponseBody
	public List<String> selectCloths(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<String> imageList = new ArrayList<String>();
		String streamIn = MyUtils.inputStreamToString(new BufferedInputStream(
				request.getInputStream()));
		JSONObject object = JSONObject.fromObject(streamIn);

		String date = object.getString("param");
		imageList = PictureUtils.readClothToBytes(date);

		return imageList;
	}

	@RequestMapping(value = "/selectHead", method = RequestMethod.POST)
	@ResponseBody
	public String selectHead(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<String> imageList = new ArrayList<String>();
		String streamIn = MyUtils.inputStreamToString(new BufferedInputStream(
				request.getInputStream()));
		JSONObject object = JSONObject.fromObject(streamIn);

		String username = object.getString("username");
//		imageList = PictureUtils.readHeadToBytes(date);
		
		String url = Config.uploadUrl+username;
		File file2 = new File(url);
		if (!file2.exists()) {
			file2.mkdirs();
		}

		String fileurl = url +"/" + "head.jpg";

		File file = new File(fileurl);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
		return Config.siteUrl+Config.uploadMapUrl+username+"/head.jpg";
	}

	@RequestMapping(value = "/uploadHead", method = RequestMethod.POST)
	@ResponseBody
	public String uploadHead(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		InputStream is = request.getInputStream();
		DataInputStream dis = new DataInputStream(is);

		String result = "";
		try {
			result = MyUtils.saveHeadFile(dis);
		} catch (Exception e) {
			e.printStackTrace();
			result = "uploaderror";
		}
		request.getSession().invalidate();
		response.setContentType("text/html;charset=UTF-8");
		ObjectOutputStream dos = new ObjectOutputStream(
				response.getOutputStream());
		dos.writeObject(result);
		dos.flush();
		dos.close();
		dis.close();
		is.close();
		return result;
	}

	@RequestMapping(value = "/uploadCloth", method = RequestMethod.POST)
	@ResponseBody
	public String uploadCloth(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		InputStream is = request.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		/*
		 * String streamIn = MyUtils.inputStreamToString(new
		 * BufferedInputStream(is)); JSONObject object =
		 * JSONObject.fromObject(streamIn); String date =
		 * object.getString("username");
		 */

		String result = "";
		try {
			result = MyUtils.saveClothFile(dis);
		} catch (Exception e) {
			e.printStackTrace();
			result = "uploaderror";
		}
		request.getSession().invalidate();
		response.setContentType("text/html;charset=UTF-8");
		ObjectOutputStream dos = new ObjectOutputStream(
				response.getOutputStream());
		dos.writeObject(result);
		dos.flush();
		dos.close();
		dis.close();
		is.close();
		return result;
	}

	@RequestMapping(value = "/saveWether", method = RequestMethod.POST)
	@ResponseBody
	public String saveWether() throws ServletException, IOException {
		if (WeatherUtils.checkStatus()) {
			uploadService.delWeather();
		}
		// List<Map<String,Object>> list = WeatherUtils.getTodayWeather2();
		List<Weather> weatherList = WeatherUtils.getTodayWeather2();
		if (weatherList.get(0).getType().equals("没联网想啥呢")) {
			return "fail";
		} else {
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

				uploadService.SaveWether(date, ymd, high, sunrise, fx, week,
						low, fl, sunset, aqi, type, notice);
			}
		}
		return "success";
	}

	@RequestMapping(value = "/selectWether", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray selectWether(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ParseException {
		response.setCharacterEncoding("UTF-8");// 通知response以UTF-8发送
		response.setContentType("text/html;charset=UTF-8");// 设置浏览器以UTF-8打开
		String streamIn = MyUtils.inputStreamToString(new BufferedInputStream(
				request.getInputStream()));
		// System.out.println(streamIn);
		JSONObject object = JSONObject.fromObject(streamIn);

		String username = object.getString("username");
		String date = MyUtils.getDate().substring(0, 10);

		List<Weather> list = new ArrayList<Weather>();
		list = uploadService.selectWeather(date);
		JSONArray jsonArray = JSONArray.fromObject(list);

		return jsonArray;
	}

	/**
	 * 保存文件
	 * 
	 * @param dis
	 * @return
	 */
	private String saveFile(DataInputStream dis,String username) {
		System.out.println(dis);
		String picname = "2020";
		try {
			picname = MyUtils.getUpDate();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String url = Config.uploadUrl+username;
		File file2 = new File(url);
		if (!file2.exists()) {
			file2.mkdirs();
		}

		String fileurl = url +"/" + picname + ".jpg";

		File file = new File(fileurl);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		String uploadby = username;
		String uploaddt = "";
		try {
			uploaddt = MyUtils.getDate();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		uploadService.insertUpload(Config.siteUrl+Config.uploadMapUrl+username+"/"+picname+".jpg", uploadby, uploaddt);

		FileOutputStream fps = null;
		try {
			fps = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int bufferSize = 1024;
		byte[] buffer = new byte[bufferSize];
		int length = -1;

		try {
			while ((length = dis.read(buffer)) != -1) {
				fps.write(buffer, 0, length);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fps.flush();
			fps.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "success";
	}
}
