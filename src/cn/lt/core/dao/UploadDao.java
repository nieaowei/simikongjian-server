package cn.lt.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lt.core.po.Upload;

public interface UploadDao {
	public int insertUpload(@Param("uploadimgurl")String uploadimgurl, @Param("uploadby")String uploadby, @Param("uploaddt")String uploaddt);

	public List<Upload> selectUpload(@Param("uploadby")String uploadby);
}
