package cn.lt.core.web.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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

import cn.lt.core.po.Login;
import cn.lt.core.po.UserFoot;
import cn.lt.core.po.UserFriend;
import cn.lt.core.po.UserInfo;
import cn.lt.core.po.UserShare;
import cn.lt.core.service.UserInfoService;

@Controller
public class UserInfoController {
	@Autowired
	private UserInfoService userInfoService;

	// 登录接口
		@RequestMapping(value = "/insertInfo", method = RequestMethod.POST)
		@ResponseBody
		public String insertInfo(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException,
				ParseException {
			
			String streamIn = MyUtils.inputStreamToString(new BufferedInputStream(
					request.getInputStream()));
			// System.out.println(streamIn);
			JSONObject object = JSONObject.fromObject(streamIn);
			String userinfo = object.getString("userinfo");
			
			JSONObject jsonObject = JSONObject.fromObject(userinfo);
			UserInfo stu = (UserInfo) JSONObject.toBean(jsonObject, UserInfo.class);
			stu.setUpdatedt(MyUtils.getDate());
			/*List<UserInfo> userInfos = new ArrayList<UserInfo>();
			// 检查注册的账号是否重复
			userInfos = userInfoService.selectInfoByName(stu.getUsername());*/
			boolean result = false;
			/*if (userInfos.size()>0) {
				result = userInfoService.updateInfo(stu);
				if (result) {
					return "success";
				}
				else {
					return "fail";
//				}
			}else {*/
				result = userInfoService.insertInfo(stu);
				if (result) {
					return "success";
				}
				else {
					return "fail";
				}
		/* } */
			
		}
		
		@RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
		@ResponseBody
		public String updateInfo(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException,
				ParseException {
			
			String streamIn = MyUtils.inputStreamToString(new BufferedInputStream(
					request.getInputStream()));
			// System.out.println(streamIn);
			JSONObject object = JSONObject.fromObject(streamIn);
			String userinfo = object.getString("userinfo");
			
			JSONObject jsonObject = JSONObject.fromObject(userinfo);
			UserInfo stu = (UserInfo) JSONObject.toBean(jsonObject, UserInfo.class);
			boolean result = userInfoService.updateInfo(stu);
			if (result) {
				return "success";
			}
			else {
				return "fail";
			}
		}
		
		@RequestMapping(value = "/selectInfoByName", produces="application/json;charset=UTF-8", method = RequestMethod.POST)
		@ResponseBody
		public JSONArray selectInfoByName(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException,
				ParseException{
			response.setCharacterEncoding("UTF-8");//通知response以UTF-8发送  
	        response.setContentType("text/html;charset=UTF-8");//设置浏览器以UTF-8打开 
			String streamIn = MyUtils.inputStreamToString(new BufferedInputStream(
					request.getInputStream()));
			// System.out.println(streamIn);
			JSONObject object = JSONObject.fromObject(streamIn);
			String username = object.getString("username");
			
			List<UserInfo> userInfos = new ArrayList<UserInfo>();
			// 检查注册的账号是否重复
			userInfos = userInfoService.selectInfoByName(username);
			JSONArray jsonArray = JSONArray.fromObject(userInfos);
			String aa = jsonArray.toString();
			System.out.println(aa);

			if (userInfos != null) {
				return jsonArray;
			} else {
				
				return jsonArray;
			}
		}
		
		@RequestMapping(value = "/insertUserFoot", method = RequestMethod.POST)
		@ResponseBody
		public String insertUserFoot(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException,
				ParseException {

			String streamIn = MyUtils.inputStreamToString(new BufferedInputStream(
					request.getInputStream()));
			JSONObject object = JSONObject.fromObject(streamIn);
			String username = object.getString("username");
			String userfoot = object.getString("userfoot");
			String dt = MyUtils.getDate();

			boolean result = false;
			// 已经存在了
				result = userInfoService.insertUserFoot(username, userfoot,dt);
				if (result) {
					return "success";
				} else {
					return "fail";
				}
		}
		
		@RequestMapping(value = "/selectUserFoots", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
		@ResponseBody
		public JSONArray selectUserFoots(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException,
				ParseException {
			response.setCharacterEncoding("UTF-8");// 通知response以UTF-8发送
			response.setContentType("text/html;charset=UTF-8");// 设置浏览器以UTF-8打开
			String streamIn = MyUtils.inputStreamToString(new BufferedInputStream(
					request.getInputStream()));
			JSONObject object = JSONObject.fromObject(streamIn);

			String username = object.getString("username");
			List<UserFoot> happyNotes = new ArrayList<UserFoot>();

			happyNotes = userInfoService.selectUserFoots(username);
			
			Collections.sort(happyNotes, new Comparator<UserFoot>(){  
	            public int compare(UserFoot o1, UserFoot o2) { 
	            	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//注意月份是MM
	                try {
						Date date = simpleDateFormat.parse(o1.dt);
						Date date2 = simpleDateFormat.parse(o2.dt);
						return date2.compareTo(date);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return 0;
	            }             
	        }); 

			// if (happyNotes!=null&&happyNotes.size()>0) {
			JSONArray jsonArray = JSONArray.fromObject(happyNotes);
			// String aa = jsonArray.toString();
			return jsonArray;

		}
		@RequestMapping(value = "/selectHistory", method = RequestMethod.GET)
		@ResponseBody
		public void selectHistory(HttpServletRequest request,
				HttpServletResponse response) throws IOException {
			String username = request.getParameter("username");
			// Food food=null;
			response.setCharacterEncoding("UTF-8");//通知response以UTF-8发送  
	        response.setContentType("text/html;charset=UTF-8");//设置浏览器以UTF-8打开
			
			List<UserInfo> userInfos = userInfoService.selectInfoByNameOderByAtDESC(username);
			JSONObject js = new JSONObject();
			js.put("data", userInfos);
			response.getWriter().write(js.toString());
			response.getWriter().close();
//			return username;
		}
}
