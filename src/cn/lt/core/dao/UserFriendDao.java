package cn.lt.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lt.core.po.Login;
import cn.lt.core.po.UserFriend;
import cn.lt.core.po.UserShare;

public interface UserFriendDao {

	public int insertUserFriend(@Param("username") String username,@Param("friendname") String friendname);

	public List<UserFriend> selectUserFriendByName(@Param("username") String username);

	public int delUserFriendByName(@Param("username") String username,@Param("friendname") String friendname);

	public List<Login> selectAllUser();
	
	public int delUserByNm(@Param("username") String username);
}
