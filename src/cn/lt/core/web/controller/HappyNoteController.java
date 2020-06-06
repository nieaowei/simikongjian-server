package cn.lt.core.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
import cn.lt.core.po.WeekDay;
import cn.lt.core.service.HappyNoteService;

@Controller
public class HappyNoteController {
	@Autowired
	private HappyNoteService happyNoteService;
	
	/*
	 * 记录日记
	 */
	@RequestMapping(value = "/writenote", produces="application/json;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String happyNoteWrite(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ParseException{
		response.setCharacterEncoding("UTF-8");//通知response以UTF-8发送  
        response.setContentType("text/html;charset=UTF-8");//设置浏览器以UTF-8打开 
		String streamIn = MyUtils.inputStreamToString(new BufferedInputStream(
				request.getInputStream()));
		// System.out.println(streamIn);
		JSONObject object = JSONObject.fromObject(streamIn);
		
		String noteuser = object.getString("noteuser");
		String notetitle = object.getString("notetitle");
		String note = object.getString("note");
		String address = object.getString("address");
		String weather = object.getString("weather");
		String writetime = MyUtils.getDate();
		boolean checkWrite = happyNoteService.happyNoteWrite(noteuser, note,writetime,notetitle,address,weather);
		
		if (checkWrite) {
			return "success";
		} else {
			
			return "fail";
		}
	}
	
	@RequestMapping(value = "/selectnote", produces="application/json;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray happyNoteSelect(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ParseException{
		response.setCharacterEncoding("UTF-8");//通知response以UTF-8发送  
        response.setContentType("text/html;charset=UTF-8");//设置浏览器以UTF-8打开 
		String streamIn = MyUtils.inputStreamToString(new BufferedInputStream(
				request.getInputStream()));
		// System.out.println(streamIn);
		JSONObject object = JSONObject.fromObject(streamIn);
		
		String noteuser = object.getString("username");
		List<HappyNote> happyNotes = new ArrayList<HappyNote>();
		
		happyNotes = happyNoteService.happyNoteSelect(noteuser);
		
//		if (happyNotes!=null&&happyNotes.size()>0) {
			JSONArray jsonArray = JSONArray.fromObject(happyNotes);
			String aa = jsonArray.toString();
			return jsonArray;
//		} 
	}
	
	@RequestMapping(value = "/findIndex", method = RequestMethod.GET)
	public String idnex(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
	ParseException{
		// Food food=null;
		response.setCharacterEncoding("UTF-8");//通知response以UTF-8发送  
        response.setContentType("text/html;charset=UTF-8");//设置浏览器以UTF-8打开 
		
		String strUrl = "http://whois.pconline.com.cn/ipJson.jsp?json=true&ip="+request.getRemoteAddr();
		 System.out.println(request.getRemoteAddr()+request.getRemoteHost());
        try {
            URL url = new URL(strUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
//            httpURLConnection.setRequestProperty("Accept", "charset=utf-8");
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setReadTimeout(60000);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200){
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream(), "GB2312"));
                String line = null;
                StringBuffer stringBuffer = new StringBuffer();
                while ((line = reader.readLine()) != null){
                    stringBuffer.append(line);
                }
                System.out.println(stringBuffer.toString());
            
                reader.close();
                httpURLConnection.disconnect();
             
                response.getWriter().append(stringBuffer.toString());
                response.getWriter().close();
            }
            
        }catch (Exception e){
            e.printStackTrace();
           return "";
        }
        return "";
		
	}
}
