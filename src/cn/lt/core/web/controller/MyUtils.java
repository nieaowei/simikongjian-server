package cn.lt.core.web.controller;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MyUtils {

	public static String inputStreamToString(InputStream inputStream)
			throws IOException {
		int len = 0;
		byte[] butter = new byte[1024];
		StringBuffer stringBuffer = new StringBuffer();
		while ((len = inputStream.read(butter)) != -1) {
			String s = new String(butter, 0, len, "UTF-8");
			stringBuffer.append(s);
		}
		return stringBuffer.toString();
	}

	public static String getDate() throws ParseException {
		// 获取当前日期
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;
		int day = now.get(Calendar.DAY_OF_MONTH);
		// String dateNowStr = year+"-"+m
		/* long millis = now.getTimeInMillis(); */

		// 日期格式化打印
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
		String dateNowStr = sdf.format(d);
		return dateNowStr;
	}

	public static String getUpDate() throws ParseException {
		Calendar ca = Calendar.getInstance();
		String year = ca.get(Calendar.YEAR) + "";// 年
		int month = ca.get(Calendar.MONTH) + 1;// 月
		int day = ca.get(Calendar.DATE);// 日
		int hour = ca.get(Calendar.HOUR_OF_DAY);// 时
		int minute = ca.get(Calendar.MINUTE);// 分
		int second = ca.get(Calendar.SECOND);// 秒
		String timeString = year + Integer.toString(month)
				+ Integer.toString(day) + Integer.toString(hour)
				+ Integer.toString(minute) + Integer.toString(second);
		return timeString;
	}

	public void writeUrls(String url, String outPath) throws IOException {
		File txt = new File(outPath);
		url = url + "\r\n";
		byte[] bytes = new byte[512];
		bytes = url.getBytes();
		int length = bytes.length;

		FileOutputStream fos = new FileOutputStream(txt, true);
		fos.write(bytes, 0, length);
		fos.flush();
		fos.close();
	}

	public static byte[] inputStreamToBytes(InputStream ins) {
		byte[] data = null;
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			int i = -1;
			int BUFFER_SIZE = 255;
			byte[] buf = new byte[BUFFER_SIZE];
			while ((i = ins.read(buf)) != -1) {
				baos.write(buf, 0, i);
			}
			data = baos.toByteArray();
		} catch (IOException e) {
			// TODO: 错误处理

		}

		return data;
	}

	public static List<File> searchFiles(File folder, final String keyword) {
		 List<File> result = new ArrayList<File>();
		 if (folder.isFile())
		 result.add(folder);

		 File[] subFolders = folder.listFiles(new FileFilter() {
			 @Override
			 public boolean accept(File file) {
				 if (file.isDirectory()) {
					 return true;
				 }
				 if (file.getName().toLowerCase().contains(keyword)) {
					 return true;
				 }
				 return false;
			 }
		 });
		 if (subFolders != null) {
			 for (File file : subFolders) {
				 if (file.isFile()) {
					 // 如果是文件则将文件添加到结果列表中
					 result.add(file);
				 } else {
					 // 如果是文件夹，则递归调用本方法，然后把所有的文件加到结果列表中
				     result.addAll(searchFiles(file, keyword));
				 }
			 }
		 }
		 return result;
	}
	
	/**
     * 保存文件
     * @param dis
     * @return
     */
    public static String saveHeadFile(DataInputStream dis) {
    	/*System.out.println(dis);
    	String picname = "2020";
    	try {
    		picname = MyUtils.getUpDate();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
    	String url = "D:/mywork/userhead/"+"user";
    	File file2 = new File(url);
        if (!file2.exists()) {
            file2.mkdirs();
        }
    	
        String fileurl = "D:/mywork/userhead/"+"user"+"/"+"myhead"+".jpg";
        
        File file = new File(fileurl);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        FileOutputStream fps = null;
        try {
            fps = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        int length = -1;

        try {
            while ((length = dis.read(buffer)) != -1) {
                fps.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fps.flush();
            fps.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
    
    ///保存衣服
    public static String saveClothFile(DataInputStream dis) {
    	String picname = "2020";
    	try {
    		picname = MyUtils.getUpDate();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
        String fileurl = "D:/mywork/userCloth/"+"chun"+"/"+picname+".jpg";
        
        File file = new File(fileurl);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        FileOutputStream fps = null;
        try {
            fps = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        int length = -1;

        try {
            while ((length = dis.read(buffer)) != -1) {
                fps.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fps.flush();
            fps.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
