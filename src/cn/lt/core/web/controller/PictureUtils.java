package cn.lt.core.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;

public class PictureUtils {
	// 图片转成字节流
	public static List<String> readPicToBytes() {
		// 图片转化为二进制
		File file = new File("D:/mywork/picture/user");
		File[] files = file.listFiles();

		List<String> aaaList = new ArrayList<>();
		for (File f : files) {
			try {
				if (f.getName().contains(".jpg")) {
					// 读取图片转换为流
					FileInputStream fis = new FileInputStream(f);
					byte[] data = MyUtils.inputStreamToBytes(fis);
					String base64str = Base64.encodeBase64String(data);
					String img = base64str;
					aaaList.add(img);
				}
			} catch (Exception e) {
				// logger.warn("读取上传异常",e);
				
				continue;
			}
		}
		return aaaList;

	}
	
	public static List<String> readClothToBytes(String dates) {
		String aaa= "";
		if (dates.equals("111")) {
			aaa="hot";
		}else {
			aaa="cool";
		}
		// 图片转化为二进制
		File file = new File("/tmp/picturecloth/user/"+aaa);
		File[] files = file.listFiles();

		List<String> aaaList = new ArrayList<>();
		for (File f : files) {
			try {
				if (f.getName().contains(".jpg")) {
					// 读取图片转换为流
					FileInputStream fis = new FileInputStream(f);
					byte[] data = MyUtils.inputStreamToBytes(fis);
					String base64str = Base64.encodeBase64String(data);
					String img = base64str;
					aaaList.add(img);
				}
			} catch (Exception e) {
				// logger.warn("读取上传异常",e);
				continue;
			}
		}
		return aaaList;

	}
	
	public static List<String> readHeadToBytes(String date) {
		// 图片转化为二进制
		String urlString = "D:/mywork/userhead/"+date;
		File file = new File(urlString);
		File[] files = file.listFiles();

		List<String> aaaList = new ArrayList<>();
		for (File f : files) {
			try {
				if (f.getName().contains(".jpg")) {
					// 读取图片转换为流
					FileInputStream fis = new FileInputStream(f);
					byte[] data = MyUtils.inputStreamToBytes(fis);
					String base64str = Base64.encodeBase64String(data);
					String img = base64str;
					aaaList.add(img);
				}
			} catch (Exception e) {
				// logger.warn("读取上传异常",e);
				continue;
			}
		}
		return aaaList;

	}

	// 音频转成字节流
	public static List<String> readMicToBytes(String musiclUrl,String usernmaeString) {
		// 图片转化为二进制
		List<String> aaaList = new ArrayList<>();
		try {
			File file = new File(musiclUrl);
			
			FileInputStream fileInputStream = new FileInputStream(file);
			byte[] data = MyUtils.inputStreamToBytes(fileInputStream);
			String base64str = Base64.encodeBase64String(data);
			String img = base64str;
			aaaList.add(img);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
//		File file = new File("/tmp/music/"+musiclUrl+"/");
//		File[] files = file.listFiles();
//		String url = musiclUrl.split(".")[1];
//		List<String> aaaList = new ArrayList<>();
//		for (File f : files) {
//			try {
//				if (f.getName().contains(url)) {
//					// 读取图片转换为流
//					FileInputStream fis = new FileInputStream(f);
//					byte[] data = MyUtils.inputStreamToBytes(fis);
//					String base64str = Base64.encodeBase64String(data);
//					String img = base64str;
//					aaaList.add(img);
//				}
//			} catch (Exception e) {
//				// logger.warn("读取上传异常",e);
//				continue;
//			}
//		}
		return aaaList;

	}

	// 查小说名和作者
	public static List<TextUtils> selectTextUtils() {
		// 图片转化为二进制
		String path = "/tmp/story";
		File file = new File(path);
		File[] files = file.listFiles();
		List<TextUtils> textUtilsList = new ArrayList<TextUtils>();
		
		for (int i = 0; i < files.length; i++) {
			TextUtils textUtils = new TextUtils();
            if (files[i].isFile()) {
            	textUtils.setTextpath(files[i].getPath());
            	textUtils.setTextname(files[i].getName());
            }
            textUtilsList.add(textUtils);
        }
		return textUtilsList;

	}
	
	public static List<String> readTextToBytes(String textname) {
		
		List<String> aaaList = new ArrayList<>();
		List<File> files = MyUtils.searchFiles(new File("/tmp/story"), textname);
		for (File file : files) {
			try {
				if (file.getName().contains(".txt")) {
					// 读取图片转换为流
					FileInputStream fis = new FileInputStream(file);
					byte[] data = MyUtils.inputStreamToBytes(fis);
					String base64str = Base64.encodeBase64String(data);
					String img = base64str;
					aaaList.add(img);
				}
			} catch (Exception e) {
				// logger.warn("读取上传异常",e);
				continue;
			}
		
		}
		return aaaList;
	}
	
	// 查小说名和作者
		public static List<TextUtils> selectStudyTextUtils() {
			// 图片转化为二进制
			String path = "/tmp/study";
			File file = new File(path);
			File[] files = file.listFiles();
			List<TextUtils> textUtilsList = new ArrayList<TextUtils>();
			
			for (int i = 0; i < files.length; i++) {
				TextUtils textUtils = new TextUtils();
	            if (files[i].isFile()) {
	            	textUtils.setTextpath(files[i].getPath());
	            	textUtils.setTextname(files[i].getName());
	            }
	            textUtilsList.add(textUtils);
	        }
			return textUtilsList;

		}
	
public static List<String> readStudyTextToBytes(String textname) {
		
		List<String> aaaList = new ArrayList<>();
		List<File> files = MyUtils.searchFiles(new File("/tmp/study"), textname);
		for (File file : files) {
			try {
				if (file.getName().contains(".txt")) {
					// 读取图片转换为流
					FileInputStream fis = new FileInputStream(file);
					byte[] data = MyUtils.inputStreamToBytes(fis);
					String base64str = Base64.encodeBase64String(data);
					String img = base64str;
					aaaList.add(img);
				}
			} catch (Exception e) {
				// logger.warn("读取上传异常",e);
				continue;
			}
		
		}
		return aaaList;
	}
}
