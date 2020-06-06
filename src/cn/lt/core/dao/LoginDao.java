package cn.lt.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lt.core.po.Test;
import cn.lt.core.po.Login;

public interface LoginDao {
	// ��¼
	public List<Login> login(@Param("username")String username,@Param("password")String password);
	
	// ע��ǰ����û����Ƿ����
	public Login findUserById(@Param("username")String username);
	
	// ע��
	public int register(@Param("username")String username,@Param("password")String password);
	
	public int updatePass(@Param("username")String username,@Param("password")String password);
	
	public Login validPass(@Param("username")String username,@Param("password")String password);
}
