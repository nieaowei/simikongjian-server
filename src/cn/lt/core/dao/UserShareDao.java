package cn.lt.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lt.core.po.UserShare;

public interface UserShareDao {

	public int addShares(@Param("username")String username,@Param("shares") String shares,@Param("sharesurl") String sharesurl,@Param("sharesdt")String sharedt);

	public List<UserShare> selectSharesByName(@Param("username") String username);
}
