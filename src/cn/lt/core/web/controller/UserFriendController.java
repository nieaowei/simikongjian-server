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

import cn.lt.core.po.HappyNote;
import cn.lt.core.po.Login;
import cn.lt.core.po.UserFriend;
import cn.lt.core.po.UserInfo;
import cn.lt.core.po.UserShare;
import cn.lt.core.po.Weather;
import cn.lt.core.service.LoginService;
import cn.lt.core.service.UserFriendService;
import cn.lt.core.service.UserInfoService;

@Controller
public class UserFriendController {
	@Autowired
	private UserFriendService userFriendService;
	@Autowired
	private LoginService loginService;

	/**
	 * 加好友
	 */
	@RequestMapping(value = "/addUserFriends", method = RequestMethod.POST)
	@ResponseBody
	public String addUserFriends(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ParseException {

		String streamIn = MyUtils.inputStreamToString(new BufferedInputStream(
				request.getInputStream()));
		JSONObject object = JSONObject.fromObject(streamIn);
		String username = object.getString("username");
		String friendname = object.getString("friendname");

		Login login = new Login();
		// 检查注册的账号是否重复
		login = loginService.findUserById(friendname);
		if (login==null) {
			return "nobody";
		}
		boolean result = false;
		// 已经存在了
			result = userFriendService.insertUserFriend(username, friendname);
			if (result) {
				return "success";
			} else {
				return "fail";
			}
	}

	/**
	 * 好友列表
	 */
	@RequestMapping(value = "/selectUserFriends", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray selectUserFriends(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ParseException {
		response.setCharacterEncoding("UTF-8");// 通知response以UTF-8发送
		response.setContentType("text/html;charset=UTF-8");// 设置浏览器以UTF-8打开
		String streamIn = MyUtils.inputStreamToString(new BufferedInputStream(
				request.getInputStream()));
		JSONObject object = JSONObject.fromObject(streamIn);

		String username = object.getString("username");
		List<UserFriend> happyNotes = new ArrayList<UserFriend>();

		happyNotes = userFriendService.selectUserFriendByName(username);

		// if (happyNotes!=null&&happyNotes.size()>0) {
		JSONArray jsonArray = JSONArray.fromObject(happyNotes);
		// String aa = jsonArray.toString();
		return jsonArray;

	}

	/**
	 * 删好友
	 */
	@RequestMapping(value = "/delUserFriends", method = RequestMethod.POST)
	@ResponseBody
	public String delUserFriends(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ParseException {

		String streamIn = MyUtils.inputStreamToString(new BufferedInputStream(
				request.getInputStream()));
		JSONObject object = JSONObject.fromObject(streamIn);
		String username = object.getString("username");
		String friendname = object.getString("friendname");

		boolean result = false;
		result = userFriendService.delUserFriendByName(username, friendname);
		if (result) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	/**
	 * 上传分享
	 */
	@RequestMapping(value = "/addShares", method = RequestMethod.POST)
	@ResponseBody
	public String addShares(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ParseException {

		String streamIn = MyUtils.inputStreamToString(new BufferedInputStream(
				request.getInputStream()));
		JSONObject object = JSONObject.fromObject(streamIn);
		String username = object.getString("username");
		String shares = object.getString("shares");
		String sharesurl  = object.getString("sharesurl");
		String sharedt = MyUtils.getDate();

		boolean result = false;
		// 已经存在了
			result = userFriendService.addShares(username, shares,sharesurl,sharedt);
			if (result) {
				return "success";
			} else {
				return "fail";
			}
	}
	
	/**
	 * 好友列表
	 */
	@RequestMapping(value = "/selectSharesByName", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray selectSharesByName(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ParseException {
		response.setCharacterEncoding("UTF-8");// 通知response以UTF-8发送
		response.setContentType("text/html;charset=UTF-8");// 设置浏览器以UTF-8打开
		String streamIn = MyUtils.inputStreamToString(new BufferedInputStream(
				request.getInputStream()));
		JSONObject object = JSONObject.fromObject(streamIn);

		String username = object.getString("username");
		
		List<UserFriend> userFriends = new ArrayList<UserFriend>();

		userFriends = userFriendService.selectUserFriendByName(username);
		List<UserShare> userShares = new ArrayList<UserShare>();
		
		for (int i = 0; i < userFriends.size(); i++) {
			String userfriendsname = userFriends.get(i).getFriendname();
			List<UserShare> happyNotes = new ArrayList<UserShare>();
			UserShare userShare = new UserShare();
			happyNotes = userFriendService.selectSharesByName(userfriendsname);
			for (int j = 0; j < happyNotes.size(); j++) {
				userShare = happyNotes.get(j);
				userShares.add(userShare);
			}
			
		}
		
		Collections.sort(userShares, new Comparator<UserShare>(){  
            public int compare(UserShare o1, UserShare o2) { 
            	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//注意月份是MM
                try {
					Date date = simpleDateFormat.parse(o1.sharesdt);
					Date date2 = simpleDateFormat.parse(o2.sharesdt);
					return date2.compareTo(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return 0;
            }             
        }); 
		// if (happyNotes!=null&&happyNotes.size()>0) {
		JSONArray jsonArray = JSONArray.fromObject(userShares);
		// String aa = jsonArray.toString();
		return jsonArray;

	}
	
	/**
	 * 好友列表
	 */
	@RequestMapping(value = "/selectAllUser", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray selectAllUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ParseException {
		response.setCharacterEncoding("UTF-8");// 通知response以UTF-8发送
		response.setContentType("text/html;charset=UTF-8");// 设置浏览器以UTF-8打开
		String streamIn = MyUtils.inputStreamToString(new BufferedInputStream(
				request.getInputStream()));
		JSONObject object = JSONObject.fromObject(streamIn);
		
		List<Login> userFriends = new ArrayList<Login>();

		userFriends = userFriendService.selectAllUser();
		
		// if (happyNotes!=null&&happyNotes.size()>0) {
		JSONArray jsonArray = JSONArray.fromObject(userFriends);
		// String aa = jsonArray.toString();
		return jsonArray;

	}
	
	/**
	 * 删好友
	 */
	@RequestMapping(value = "/delUserByNm", method = RequestMethod.POST)
	@ResponseBody
	public String delUserByNm(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ParseException {

		String streamIn = MyUtils.inputStreamToString(new BufferedInputStream(
				request.getInputStream()));
		JSONObject object = JSONObject.fromObject(streamIn);
		String username = object.getString("username");

		boolean result = false;
		result = userFriendService.delUserByNm(username);
		if (result) {
			return "success";
		} else {
			return "fail";
		}
	}
}
