package com.mall.common.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class MyFileUtil {

	private static final Logger log = LoggerFactory.getLogger(MyFileUtil.class);
	private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final Random r = new Random();
	
	
	public static String saveFile(MultipartFile file) throws IOException {
		log.info("属性名："  + file.getName());
		System.out.println("文件名（值）：" + file.getOriginalFilename());
		System.out.println("文件大小：" + file.getSize());
		//根据绝对路径创建文件夹
		makeDirPath(PathUtil.getWinPath());
		//将文件存储到绝对路径下
		File localFile = new File(PathUtil.getWinPath(),
				getRandomFileName() + getFileExtension(file.getOriginalFilename()));
		file.transferTo(localFile);
		//存储完，返回网络路径地址
		return PathUtil.getNetPath() + getRandomFileName() + getFileExtension(file.getOriginalFilename());
	}
	
	/**
	 * 根据绝对路径自动创建
	 * @param targetAddr
	 */
	private static void makeDirPath(String targetAddr) {
		String realFileParentPath = targetAddr;
		File dirPath = new File(realFileParentPath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}

	/**
	 * 获取输入文件流的扩展名
	 * 
	 * @param thumbnail
	 * @return
	 */
	private static String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 生成随机文件名，当前年月日小时分钟秒钟+五位随机数
	 * 
	 * @return
	 */
	public static String getRandomFileName() {
		// 获取随机的五位数
		int rannum = r.nextInt(89999) + 10000;
		String nowTimeStr = sDateFormat.format(new Date());
		return nowTimeStr + rannum;
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println(PathUtil.getWinPath());
	}
}
