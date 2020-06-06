package cn.lt.core.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lt.core.po.UserFoot;
import cn.lt.core.po.UserInfo;

public interface UserInfoService {

	public boolean insertInfo(UserInfo userInfo);
	
	public boolean updateInfo(UserInfo userInfo);

	public List<UserInfo> selectInfoByName(String username);

	public boolean insertUserFoot(String username, String userfoot, String dt);

	public List<UserFoot> selectUserFoots(String username);
	public List<UserInfo> selectInfoByNameOderByAtDESC(@Param("username")String username);
}
