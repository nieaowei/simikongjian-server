package cn.lt.core.service;

import java.util.List;

import cn.lt.core.po.Login;
import cn.lt.core.po.UserFriend;
import cn.lt.core.po.UserShare;
import cn.lt.core.po.Weather;

public interface UserFriendService {

	public boolean insertUserFriend(String username,String friendname);

	public List<UserFriend> selectUserFriendByName(String username);
	
	public boolean delUserFriendByName(String username,String friendname);

	public boolean addShares(String username, String shares, String sharesurl,String sharedt);
	
	public List<UserShare> selectSharesByName(String username);
	
	public List<Login> selectAllUser();

	public boolean delUserByNm(String username);
	}
