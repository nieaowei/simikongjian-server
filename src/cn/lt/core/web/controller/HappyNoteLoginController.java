package cn.lt.core.web.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lt.core.service.HappyNoteLoginService;
@Controller
public class HappyNoteLoginController {
	@Autowired
	private HappyNoteLoginService happyNoteLoginService;
	/*
	 * 心情日记登录接口
	 */
	@RequestMapping(value = "/loginnote", produces="application/json;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String happyNoteLogin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ParseException{
		response.setCharacterEncoding("UTF-8");//通知response以UTF-8发送  
        response.setContentType("text/html;charset=UTF-8");//设置浏览器以UTF-8打开 
		String streamIn = MyUtils.inputStreamToString(new BufferedInputStream(
				request.getInputStream()));
		// System.out.println(streamIn);
		JSONObject object = JSONObject.fromObject(streamIn);
		
		String noteuser = object.getString("noteuser");
		String notepass = object.getString("notepass");
		
		boolean checklogin = happyNoteLoginService.happyNoteLogin(noteuser, notepass);
		
		if (checklogin) {
			return "success";
		} else {
			
			return "fail";
		}
	}
	
	@RequestMapping(value = "/updatenote", produces="application/json;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String happyNoteUpdate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ParseException{
		response.setCharacterEncoding("UTF-8");//通知response以UTF-8发送  
        response.setContentType("text/html;charset=UTF-8");//设置浏览器以UTF-8打开 
		String streamIn = MyUtils.inputStreamToString(new BufferedInputStream(
				request.getInputStream()));
		// System.out.println(streamIn);
		JSONObject object = JSONObject.fromObject(streamIn);
		
		String noteuser = object.getString("noteuser");
		String oldnotepass = object.getString("oldnotepass");
		String newnotepass = object.getString("newnotepass");
		// 检查一下密码对不对 如果不对 直接报错，对了执行修改
		boolean checklogin = happyNoteLoginService.happyNoteLogin(noteuser, oldnotepass);
		
		if (checklogin) {
			boolean updateScuess = happyNoteLoginService.updateHappyNoteLogin(noteuser,newnotepass);
			if (updateScuess) {
				return "success";
			}
			return "updatefail";
		} else {
			
			return "fail";
		}
	}
}
