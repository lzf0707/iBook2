package com.mall.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PathUtil {
	private static String winPath;
	private static String netPath;

	@Value("${file.base.winpath}")
	public void setWinPath(String winPath) {
		PathUtil.winPath = winPath;
	}
	
	public static String getWinPath() {
		return winPath;
	}
	
	@Value("${file.base.netpath}")
	public void setNetPath(String netPath) {
		PathUtil.netPath = netPath;
	}
	
	public static String getNetPath() {
		return netPath;
	}
}
