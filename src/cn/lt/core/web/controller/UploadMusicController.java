package cn.lt.core.web.controller;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lt.core.po.HappyNote;
import cn.lt.core.po.UploadMusic;
import cn.lt.core.service.UploadMusicService;
import cn.lt.core.service.UploadService;

@Controller
public class UploadMusicController {
	@Autowired
	private UploadMusicService uploadMusicService;

	/**
	 * 上传录音
	 */
	@RequestMapping(value = "/uploadMusic", method = RequestMethod.POST)
	@ResponseBody
	public void uploadMusic(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		InputStream is = request.getInputStream();
		DataInputStream dis = new DataInputStream(is);
			String usernameString = request.getParameter("username");
		String result = "";
		try {
			result = saveMusicFile(dis,usernameString);
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

	/*
	 * 查找录音
	 */
	@RequestMapping(value = "/selectmusic", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray selectMusic(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ParseException {
		response.setCharacterEncoding("UTF-8");// 通知response以UTF-8发送
		response.setContentType("text/html;charset=UTF-8");// 设置浏览器以UTF-8打开
		String streamIn = MyUtils.inputStreamToString(new BufferedInputStream(
				request.getInputStream()));
		// System.out.println(streamIn);
		JSONObject object = JSONObject.fromObject(streamIn);

		String noteuser = object.getString("username");
		List<UploadMusic> happyNotes = new ArrayList<UploadMusic>();

		happyNotes = uploadMusicService.selectMusic(noteuser);

		// if (happyNotes!=null&&happyNotes.size()>0) {
		JSONArray jsonArray = JSONArray.fromObject(happyNotes);
		String aa = jsonArray.toString();
		return jsonArray;
		// }
	}

	@RequestMapping(value = "/selectPlayMusic", method = RequestMethod.POST)
	@ResponseBody
	public List<String> selectPlayMusic(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String streamIn = MyUtils.inputStreamToString(new BufferedInputStream(
				request.getInputStream()));
		// System.out.println(streamIn);
		JSONObject object = JSONObject.fromObject(streamIn);
		
		String musiclUrl = object.getString("musiclUrl");
		String usenrString = object.getString("username");
		//musiclUrl.substring(musiclUrl.length()-7,6);

		List<String> imageList = new ArrayList<String>();
		imageList = PictureUtils.readMicToBytes(musiclUrl,usenrString);
		return imageList;
	}

	/**
	 * 保存文件
	 * 
	 * @param dis
	 * @return
	 */
	private String saveMusicFile(DataInputStream dis,String username) {
		System.out.println(dis);
		String picname = "2020";
		try {
			picname = MyUtils.getUpDate();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String url = "/tmp/music/" + username;
		File file2 = new File(url);
		if (!file2.exists()) {
			file2.mkdirs();
		}

		String fileurl = "/tmp/music/" + username + "/" + picname + ".mp3";

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
		uploadMusicService.insertUploadMusic(fileurl, uploadby, uploaddt);

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

	/**
	 * 查找小说
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/selectStory", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray selectStory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String streamIn = MyUtils.inputStreamToString(new BufferedInputStream(
				request.getInputStream()));
		// System.out.println(streamIn);
		JSONObject object = JSONObject.fromObject(streamIn);

		String musiclUrl = object.getString("musiclUrl");

		List<TextUtils> textUtilsList = new ArrayList<TextUtils>();
		textUtilsList = PictureUtils.selectTextUtils();

		JSONArray jsonArray = JSONArray.fromObject(textUtilsList);

		return jsonArray;
	}
	
	@RequestMapping(value = "/selectStoryDetail", method = RequestMethod.POST)
	@ResponseBody
	public List<String> selectStoryDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String streamIn = MyUtils.inputStreamToString(new BufferedInputStream(
				request.getInputStream()));
		// System.out.println(streamIn);
		JSONObject object = JSONObject.fromObject(streamIn);

		String textname = object.getString("textname");

		List<String> imageList = new ArrayList<String>();
		imageList = PictureUtils.readTextToBytes(textname);
		return imageList;
	}
	
	/**
	 * 查找小说
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/selectStudy", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray selectStudy(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String streamIn = MyUtils.inputStreamToString(new BufferedInputStream(
				request.getInputStream()));
		// System.out.println(streamIn);
		JSONObject object = JSONObject.fromObject(streamIn);

		String musiclUrl = object.getString("musiclUrl");

		List<TextUtils> textUtilsList = new ArrayList<TextUtils>();
		textUtilsList = PictureUtils.selectStudyTextUtils();

		JSONArray jsonArray = JSONArray.fromObject(textUtilsList);

		return jsonArray;
	}
	
	@RequestMapping(value = "/selectStudyDetail", method = RequestMethod.POST)
	@ResponseBody
	public List<String> selectStudyDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String streamIn = MyUtils.inputStreamToString(new BufferedInputStream(
				request.getInputStream()));
		// System.out.println(streamIn);
		JSONObject object = JSONObject.fromObject(streamIn);

		String textname = object.getString("textname");

		List<String> imageList = new ArrayList<String>();
		imageList = PictureUtils.readStudyTextToBytes(textname);
		return imageList;
	}
}
