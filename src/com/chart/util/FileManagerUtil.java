package com.chart.util;

import java.io.File;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class FileManagerUtil {
	
	/**
     * 描述: 判断sdcard是否存在
     * 
     * @return
     */
    public static boolean hasSdcard() {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 描述: 目录创建
     * 
     * @param path
     */
    public static void createPath(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 描述: 文件删除
     * 
     * @param fileName
     */
    public static void deletefile(String fileName) {
        File f = new File(fileName);
        if (f.exists()) {
            f.delete();
        }
    }
    
    /**
     * 描述: byte流转换成bitmap
     * 
     * @param b
     */
    public static Bitmap Bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }

}
