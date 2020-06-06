package cn.lt.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lt.core.po.UserFoot;

public interface UserFootDao {
	public int insertUserFoot(@Param("username") String username,@Param("userfoot") String userfoot,@Param("dt") String dt);
	public List<UserFoot> selectUserFootByName(@Param("username") String username);
}
