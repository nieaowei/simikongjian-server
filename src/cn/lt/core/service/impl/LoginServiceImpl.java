package cn.lt.core.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.lt.core.dao.FoodDao;
import cn.lt.core.dao.LoginDao;
import cn.lt.core.po.Test;
import cn.lt.core.po.Login;
import cn.lt.core.service.FoodService;
import cn.lt.core.service.LoginService;
/**
 * d��¼
 */
@Service("loginService")
@Transactional
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginDao loginDao;

	@Override
	public List<Login> login(String username,String password) {
		return this.loginDao.login(username,password);
	}

	@Override
	public Login findUserById(String username) {
		Login login = new Login();
		login = loginDao.findUserById(username);
		
		return login;
	}

	@Override
	public boolean register(String username, String password) {
		int check = 0;
		check = loginDao.register(username, password);
		if (check>0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updatePass(String username, String password) {
		int check = 0;
		check = loginDao.updatePass(username, password);
		if (check>0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean validPass(String username, String password) {
		Login check;
		check = loginDao.validPass(username, password);
		if (check!=null) {
			return true;
		} else {
			return false;
		}
	}

}
