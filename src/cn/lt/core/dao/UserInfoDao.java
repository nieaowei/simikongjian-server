package cn.lt.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lt.core.po.Test;
import cn.lt.core.po.UserInfo;

public interface UserInfoDao {
	public int insertInfo(UserInfo userInfo);
	
	public int updateInfo(UserInfo userInfo);

	public List<UserInfo> selectInfoByName(@Param("username")String username);
	public List<UserInfo> selectInfoByNameOderByAtDESC(@Param("username")String username);
}
