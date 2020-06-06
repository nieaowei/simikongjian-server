package cn.lt.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.lt.core.dao.HappyNoteLoginDao;
import cn.lt.core.dao.UserFootDao;
import cn.lt.core.dao.UserInfoDao;
import cn.lt.core.po.UserFoot;
import cn.lt.core.po.UserInfo;
import cn.lt.core.po.UserShare;
import cn.lt.core.po.WeekDay;
import cn.lt.core.service.UserInfoService;

@Service("userInfoService")
@Transactional
public class UserInfoServiceImpl implements UserInfoService {
	@Autowired
	private UserInfoDao userInfoDao;
	
	@Autowired
	private UserFootDao userFootDao;

	@Override
	public boolean insertInfo(UserInfo userInfo) {
		int check = 0;
		check = userInfoDao.insertInfo(userInfo);
		if (check > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateInfo(UserInfo userInfo) {
		int check = 0;
		check = userInfoDao.updateInfo(userInfo);
		if (check > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<UserInfo> selectInfoByName(String username) {
		List<UserInfo> userInfos = new ArrayList<UserInfo>();
		if (null != userInfoDao.selectInfoByName(username)) {
			userInfos = userInfoDao.selectInfoByName(username);
		}

		return userInfos;
	}

	@Override
	public boolean insertUserFoot(String username, String userfoot, String dt) {
		int check = 0;
		check = userFootDao.insertUserFoot(username, userfoot, dt);
		if (check > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<UserFoot> selectUserFoots(String username) {
		List<UserFoot> userInfos = userFootDao.selectUserFootByName(username);
		return userInfos;
	}
	@Override
	public List<UserInfo> selectInfoByNameOderByAtDESC(@Param("username")String username){
		List<UserInfo> userInfos = userInfoDao.selectInfoByNameOderByAtDESC(username);
		return userInfos;
	}

}
