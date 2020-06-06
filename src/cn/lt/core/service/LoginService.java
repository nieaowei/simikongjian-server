package cn.lt.core.service;

import java.util.List;

import cn.lt.core.po.Test;
import cn.lt.core.po.Login;

public interface LoginService {
	
	public List<Login> login(String username,String password);
	
	public Login findUserById(String username);
	
	public boolean register(String username,String password);
	
	public boolean updatePass(String username,String password);
	
	public boolean validPass(String username,String password);
}
