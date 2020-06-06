package cn.lt.core.service;

import java.util.List;

import cn.lt.core.po.HappyNote;
import cn.lt.core.po.UploadMusic;

public interface UploadMusicService {

	public boolean insertUploadMusic(String fileurl, String uploadby, String uploaddt);

	public List<UploadMusic> selectMusic(String noteuser);

}
