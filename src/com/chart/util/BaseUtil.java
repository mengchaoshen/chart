package com.chart.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BaseUtil {

	/**
	 * 描述:获取当前时间
	 * 
	 * @param time
	 * @return
	 */
	public static String getCurrentTime(long time) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.CHINA);
		return dateFormat.format(time);
	}

	/**
	 * 获取日期
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return long
	 */
	public static long getDate(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		return calendar.getTimeInMillis();
	}

	/**
	 * 描述:获取当前日期,精确到天
	 * 
	 * @param time
	 * @return
	 */
	public static String getCurrentDate(long time) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		return dateFormat.format(time);
	}

	/**
	 * 描述:获取当前日期,精确到月
	 * 
	 * @param time
	 * @return
	 */
	public static String getCurrentMonth(long time) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM", Locale.CHINA);
		return dateFormat.format(time);
	}

	/**
	 * 描述:获取当前月份的第一天日期
	 * 
	 * @param time
	 * @return
	 */
	public static String getCurrentMonthFirstDay(long time) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM", Locale.CHINA);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(dateFormat.format(time));
		stringBuilder.append("-");
		stringBuilder.append(1);
		return stringBuilder.toString();
	}

	/**
	 * 描述:获取年份
	 * 
	 * @param time
	 * @return
	 */
	public static String getYear(long time) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy", Locale.CHINA);
		return dateFormat.format(time);
	}

	/**
	 * 描述:获取月份
	 * 
	 * @param time
	 * @return
	 */
	public static String getMonth(long time) {
		DateFormat dateFormat = new SimpleDateFormat("MM", Locale.CHINA);
		return dateFormat.format(time);
	}

	/**
	 * 描述:获取月份
	 * 
	 * @param time
	 * @return
	 */
	public static String getDay(long time) {
		DateFormat dateFormat = new SimpleDateFormat("dd", Locale.CHINA);
		return dateFormat.format(time);
	}

	/**
	 * 描述:主界面显示时间
	 * 
	 * @param time
	 * @return
	 */
	public static String getCurrentServerTime(long time) {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.CHINA);
		return dateFormat.format(time);
	}

	/**
	 * 描述: 根据年，月，日组装日期的字符串
	 * 
	 * @param year
	 * @param monthOfYear
	 * @param dayOfMonth
	 * @return
	 */
	public static String dateToString(int year, int monthOfYear, int dayOfMonth) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(year);
		stringBuilder.append("-");
		stringBuilder.append(monthOfYear);
		stringBuilder.append("-");
		stringBuilder.append(dayOfMonth);
		return stringBuilder.toString();
	}

	/**
	 * 身份证号码加密显示
	 * 
	 * @param text
	 * @return
	 */
	public static String documentIdEncryption(String text) {
		if (null == text) {
			return null;
		}
		StringBuilder stringBuilder = new StringBuilder();
		String[] strs = text.split("");
		stringBuilder.append(strs[1]);
		stringBuilder.append(strs[2]);
		for (int i = strs.length - 5; i > 0; i--) {
			stringBuilder.append("*");
		}
		stringBuilder.append(strs[strs.length - 2]);
		stringBuilder.append(strs[strs.length - 1]);
		return stringBuilder.toString();
	}

	/**
	 * 真实姓名加密显示
	 * 
	 * @param text
	 * @return
	 */
	public static String realNameEncryption(String text) {
		if (null == text) {
			return null;
		}
		StringBuilder stringBuilder = new StringBuilder();
		String[] strs = text.split("");
		stringBuilder.append(strs[1]);
		for (int i = strs.length - 2; i > 0; i--) {
			stringBuilder.append("*");
		}
		return stringBuilder.toString();
	}

	/**
	 * 用户名加密显示
	 * 
	 * @param text
	 * @return
	 */
	public static String userNameEncryption(String text) {
		if (null == text) {
			return null;
		}
		StringBuilder stringBuilder = new StringBuilder();
		String[] strs = text.split("");
		stringBuilder.append(strs[1]);
		stringBuilder.append(strs[2]);
		for (int i = strs.length - 3; i > 0; i--) {
			stringBuilder.append("*");
		}
		return stringBuilder.toString();
	}

	/**
	 * 手机号加密显示
	 * 
	 * @param text
	 * @return
	 */
	public static String phoneNumEncryption(String text) {
		if (null == text) {
			return null;
		}
		StringBuilder stringBuilder = new StringBuilder();
		String[] strs = text.split("");
		stringBuilder.append(strs[1]);
		stringBuilder.append(strs[2]);
		stringBuilder.append(strs[3]);
		for (int i = strs.length - 8; i > 0; i--) {
			stringBuilder.append("*");
		}
		stringBuilder.append(strs[strs.length - 4]);
		stringBuilder.append(strs[strs.length - 3]);
		stringBuilder.append(strs[strs.length - 2]);
		stringBuilder.append(strs[strs.length - 1]);
		return stringBuilder.toString();
	}

	/**
	 * Email加密显示
	 * 
	 * @param text
	 * @return
	 */
	public static String emailEncryption(String text) {
		if (null == text) {
			return null;
		}
		StringBuilder stringBuilder = new StringBuilder();
		String[] strs = text.split("");
		stringBuilder.append(strs[1]);
		stringBuilder.append(strs[2]);
		boolean hasAt = false;
		for (int i = 3; i < strs.length; i++) {
			if (!hasAt && !strs[i].equals("@")) {
				stringBuilder.append("*");
			} else if (strs[i].equals("@")) {
				hasAt = true;
				stringBuilder.append("@");
			} else {
				stringBuilder.append(strs[i]);
			}
		}
		return stringBuilder.toString();
	}
	
	/**
	 * 获取字符串的最后一个字符
	 * @param name
	 * @return
	 */
	public static String getHead(String name){
		char c=name.charAt(name.length()-1);
		StringBuilder sb = new StringBuilder();
		sb.append(c);
		return sb.toString();
	}

}
