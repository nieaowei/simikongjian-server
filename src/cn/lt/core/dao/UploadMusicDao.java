package cn.lt.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lt.core.po.UploadMusic;

public interface UploadMusicDao {

	public int insertUploadMusic(@Param("uploadmusicurl")String uploadimgurl, @Param("uploadby")String uploadby, @Param("uploaddt")String uploaddt);

	public List<UploadMusic> selectMusic(@Param("uploadby")String uploadby);

}
