package cn.lt.core.web.controller;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import cn.lt.core.po.WeekDay;
import cn.lt.core.service.WeekDayService;

@Controller
public class WeekDayController {
	@Autowired
	private WeekDayService weekDayService;

	@RequestMapping(value = "/weekdayBirthday", method = RequestMethod.POST)
	@ResponseBody
	public String doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ParseException {

		String streamIn = inputStreamToString(new BufferedInputStream(
				request.getInputStream()));
		// System.out.println(streamIn);
		JSONObject object = JSONObject.fromObject(streamIn);

		String birthday = object.getString("birthday");
		String shenfen = object.getString("shenfen");
		String createby = object.getString("createby");
		String weekday = object.getString("weekday");

		JSONObject jsonObject = JSONObject.fromObject(weekday);
		WeekDay stu = (WeekDay) JSONObject.toBean(jsonObject, WeekDay.class);

		boolean register = weekDayService.addWeekDay(shenfen, birthday,createby);
		// ע��ɹ�
		if (register) {
			return "1";
		}// ע��ʧ��
		else {
			return "2";
		}
		// JSONObject userInfo =
		// JSONObject.fromObject(object.getString("weekday"));
	}

	public String inputStreamToString(InputStream inputStream)
			throws IOException {
		int len = 0;
		byte[] butter = new byte[1024];
		StringBuffer stringBuffer = new StringBuffer();
		while ((len = inputStream.read(butter)) != -1) {
			String s = new String(butter, 0, len, "UTF-8");
			stringBuffer.append(s);
		}
		return stringBuffer.toString();
	}
	
	// 注册
		@RequestMapping(value = "/selectWeekDay", produces="application/json;charset=UTF-8", method = RequestMethod.POST)
		@ResponseBody
		public JSONArray selectWeekDay(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException,
				ParseException{
			response.setCharacterEncoding("UTF-8");//通知response以UTF-8发送  
	        response.setContentType("text/html;charset=UTF-8");//设置浏览器以UTF-8打开 
			String streamIn = inputStreamToString(new BufferedInputStream(
					request.getInputStream()));
			// System.out.println(streamIn);
			JSONObject object = JSONObject.fromObject(streamIn);
			String createby = object.getString("createby");
			
			List<WeekDay> weekDay = new ArrayList<WeekDay>();
			// 检查注册的账号是否重复
			weekDay = weekDayService.selectWeekDay(createby);
			JSONArray jsonArray = JSONArray.fromObject(weekDay);
			String aa = jsonArray.toString();
			System.out.println(aa);
			boolean register;
			if (weekDay == null) {
				return jsonArray;
			} else {
				
				return jsonArray;
			}
		}
		
		@RequestMapping(value = "/getMydayByToday", method = RequestMethod.GET)
		@ResponseBody
		public String login(String username) {
			// Food food=null;
			List<WeekDay> weekDays = weekDayService.selectWeekDayToday(username);
			if (weekDays != null && weekDays.size() > 0) {
				JSONObject js = new JSONObject();
				js.put("data", weekDays);
				return js.toString();
			} else {
				return "2";
			}
		}
}
