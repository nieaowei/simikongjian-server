package cn.lt.core.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lt.core.po.Test;
import cn.lt.core.po.Login;
import cn.lt.core.service.FoodService;
import cn.lt.core.service.LoginService;

@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;

	// 登录接口
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ResponseBody
	public String login(String username, String password) {
		// Food food=null;
		System.out.println(username + password);
		
		Login login = new Login();
		login = loginService.findUserById(username);
		if(login!=null&&login.getRole()!=null&&login.getRole().equals("1")){
			List<Login> Logins = new ArrayList<Login>();
			Logins = loginService.login(username, password);
			if (Logins != null && Logins.size() > 0) {
				return "adminLoginSuccess";
			} else {
				return "loginFail";
			}
		}
		List<Login> Logins = new ArrayList<Login>();
		Logins = loginService.login(username, password);
		if (Logins != null && Logins.size() > 0) {
			return "1";
		} else {
			return "2";
		}
	}

	// 注册
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	@ResponseBody
	public String register(String username, String password) {
		Login login = new Login();
		// 检查注册的账号是否重复
		login = loginService.findUserById(username);

		boolean register;
		if (login == null) {
			register = loginService.register(username, password);
			// 注册成功
			if (register) {
				return "1";
			}// 注册失败
			else {
				return "2";
			}
		} else {
			// 账号重复了
			return "3";
		}
	}

	// 注册
	@RequestMapping(value = "/updatePass", method = RequestMethod.GET)
	@ResponseBody
	public String updatePass(String username, String password,String oldpassword) {

		boolean register;
		
		List<Login> Logins = new ArrayList<Login>();
		Logins = loginService.login(username, oldpassword);
		if (Logins != null && Logins.size() > 0) {
			register = loginService.updatePass(username, password);
			if(register) {
				return "1";
			}
		} 
		return "2";
	}
	@RequestMapping(value = "/validPass", method = RequestMethod.GET)
	@ResponseBody
	public String validPass(String username, String password) {

		boolean valid;
		valid = loginService.validPass(username, password);
		// 注册成功
		if (valid) {
			return "1";
		}// 注册失败
		else {
			return "2";
		}
	}

}
