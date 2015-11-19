package com.jc.utils;

import java.io.File;

import android.util.Log;

public class PathUtil {
	public static void isExist(String path) {
		Log.i("path",path);
		File file = new File(path);
		//判断文件夹是否存在,如果不存在则创建文件夹
		if (!file.exists()) {
			file.mkdir();
		}
	}
}
