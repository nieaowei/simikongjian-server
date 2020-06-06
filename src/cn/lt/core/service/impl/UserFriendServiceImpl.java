package cn.lt.core.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.lt.core.dao.UserFriendDao;
import cn.lt.core.dao.UserShareDao;
import cn.lt.core.po.Login;
import cn.lt.core.po.UserFriend;
import cn.lt.core.po.UserShare;
import cn.lt.core.service.UserFriendService;

@Service("userFriendService")
@Transactional
public class UserFriendServiceImpl implements UserFriendService {

	@Autowired
	UserFriendDao UserFriendDao;
	
	@Autowired
	UserShareDao UserShareDao;

	@Override
	public boolean insertUserFriend(String username, String friendname) {
		int check = 0;
		check = UserFriendDao.insertUserFriend(username, friendname);
		if (check > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<UserFriend> selectUserFriendByName(String username) {
		List<UserFriend> userInfos = UserFriendDao.selectUserFriendByName(username);
		return userInfos;
	}

	@Override
	public boolean delUserFriendByName(String username, String friendname) {
		int check = 0;
		check = UserFriendDao.delUserFriendByName(username, friendname);
		if (check > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean addShares(String username, String shares, String sharesurl,
			String sharedt) {
		int check = 0;
		check = UserShareDao.addShares(username, shares, sharesurl, sharedt);
		if (check > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<UserShare> selectSharesByName(String username) {
		List<UserShare> userInfos = UserShareDao.selectSharesByName(username);
		/*Collections.sort(userInfos, new Comparator<UserShare>(){  
            public int compare(UserShare o1, UserShare o2) { 
            	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//注意月份是MM
                try {
					Date date = simpleDateFormat.parse(o1.sharesdt);
					Date date2 = simpleDateFormat.parse(o2.sharesdt);
					return date2.compareTo(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return 0;
            }             
        }); */
		
		return userInfos;
	}

	@Override
	public List<Login> selectAllUser() {
		List<Login> userInfos = UserFriendDao.selectAllUser();
		return userInfos;
	}

	@Override
	public boolean delUserByNm(String username) {
		int check = 0;
		check = UserFriendDao.delUserByNm(username);
		if (check > 0) {
			return true;
		}
		return false;
	}
}
