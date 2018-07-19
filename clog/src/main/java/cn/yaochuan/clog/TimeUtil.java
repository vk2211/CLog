/******************************************************************************
 * @file TimeUtil.java
 * @brief DO NOT USE THE CLOG CLASS IN THIS SCOPE
 * @author YaoChuan (vk2211@gmail.com)
 * @date 2016.4.19
 * @version 0.1
 * @history v0.1, 2014.8.25, by YaoChuan
 * @history v1.0, 2016.4.19, by YaoChuan
 * @history v1.1, 2018.6.26, by YaoChuan
 * <p>
 * Copyright (C) 2014
 ******************************************************************************/

package cn.yaochuan.clog;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class TimeUtil {
	private static final String TAG = "TimeUtil";
	private static String sTimeFormatStr = "HH:mm:ss";
	private static String sDateFormatStr = "yyyy-MM-dd";
	private static String sDateTimeFormatStr = sDateFormatStr + " " + sTimeFormatStr;
	public static final long second1 = 1000L; // 1000 millis
	public static final long minute1 = second1 * 60;
	public static final long hour1 = minute1 * 60;
	public static final long day1 = hour1 * 24;
	public static final long week1 = day1 * 7;
	public static final String[] weekday1 = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

	private static SimpleDateFormat simpleTimeFormat = new SimpleDateFormat(sTimeFormatStr, Locale.getDefault());
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(sDateFormatStr, Locale.getDefault());
	private static SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat(sDateTimeFormatStr, Locale.getDefault());

	public static String getTimeFormat() {
		return sTimeFormatStr;
	}

	public static String getDateFormat() {
		return sDateFormatStr;
	}

	public static String getDateTimeFormat() {
		return sDateTimeFormatStr;
	}

	public static void setTimeFormat(String timeFormatStr) {
		sTimeFormatStr = timeFormatStr;
		sDateTimeFormatStr = sDateFormatStr + " " + sTimeFormatStr;
		simpleTimeFormat = new SimpleDateFormat(sTimeFormatStr, Locale.getDefault());
		simpleDateTimeFormat = new SimpleDateFormat(sDateTimeFormatStr, Locale.getDefault());
	}

	public static void setDateFormat(String dataFormatStr) {
		sDateFormatStr = dataFormatStr;
		sDateTimeFormatStr = sDateFormatStr + " " + sTimeFormatStr;
		simpleDateFormat = new SimpleDateFormat(sDateFormatStr, Locale.getDefault());
		simpleDateTimeFormat = new SimpleDateFormat(sDateTimeFormatStr, Locale.getDefault());
	}

	public static void setStrDateTimeFormat(String timeFormatStr, String dataFormatStr) {
		sTimeFormatStr = timeFormatStr;
		sDateFormatStr = dataFormatStr;
		sDateTimeFormatStr = sDateFormatStr + " " + sTimeFormatStr;
		simpleTimeFormat = new SimpleDateFormat(sTimeFormatStr, Locale.getDefault());
		simpleDateFormat = new SimpleDateFormat(sDateFormatStr, Locale.getDefault());
		simpleDateTimeFormat = new SimpleDateFormat(sDateTimeFormatStr, Locale.getDefault());
	}

	/**
	 * Get local time whose format is yyyy-MM-dd HH:mm:ss.
	 *
	 * @return date &amp; time string
	 */
	public static String getCurrentTimeStr() {
		return getDateTimeStr(getCurrentTime());
	}

	public static String getCurrentTimeStr(SimpleDateFormat simpleDateFormat) {
		return getDateTimeStr(getCurrentTime(), simpleDateFormat);
	}


	public static String getTimeStr(long time) {
		Date date = new Date();
		date.setTime(time);
		return simpleTimeFormat.format(date);
	}

	public static String getDateStr(long time) {
		Date date = new Date();
		date.setTime(time);
		return simpleDateFormat.format(date);
	}

	public static String getYearStr(long time) {
		Date date = new Date();
		date.setTime(time);
		return new SimpleDateFormat("yyyy", Locale.getDefault()).format(date);
	}

	public static String getMonthStr(long time) {
		Date date = new Date();
		date.setTime(time);
		return new SimpleDateFormat("MM", Locale.getDefault()).format(date);
	}

	public static String getDayStr(long time) {
		Date date = new Date();
		date.setTime(time);
		return new SimpleDateFormat("dd", Locale.getDefault()).format(date);
	}

	public static String getHourStr(long time) {
		Date date = new Date();
		date.setTime(time);
		return new SimpleDateFormat("HH", Locale.getDefault()).format(date);
	}

	public static int getYear(long time) {
		return Integer.parseInt(getYearStr(time));
	}

	public static int getMonth(long time) {
		return Integer.parseInt(getMonthStr(time));
	}

	public static int getDay(long time) {
		return Integer.parseInt(getDayStr(time));
	}

	public static int getHour(long time) {
		return Integer.parseInt(getHourStr(time));
	}

	public static String getDateTimeStr(long time) {
		Date date = new Date();
		date.setTime(time);
		return simpleDateTimeFormat.format(date);
	}

	public static String getDateTimeStr(long time, SimpleDateFormat simpleDateFormat) {
		Date date = new Date();
		date.setTime(time);
		return simpleDateFormat.format(date);
	}

	public static String getWeekday(long time) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		int w = c.get(Calendar.DAY_OF_WEEK) - 1;
		return weekday1[w];
	}

	public static long getCurrentTime() {
		return System.currentTimeMillis();
	}

	public static long getCurrentDate() {
		return getCurrentTime() / day1 * day1;
	}

	public static long parseTimeStr(String timeStr) {
		return parseTimeStr(timeStr, simpleTimeFormat);
	}

	public static long parseDateStr(String dateStr) {
		return parseTimeStr(dateStr, simpleDateFormat);
	}

	public static long parseDateTimeStr(String datetimeStr) {
		return parseTimeStr(datetimeStr, simpleDateTimeFormat);
	}

	public static long parseTimeStr(String timeStr, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format, Locale.getDefault());
		return parseTimeStr(timeStr, df);
	}

	public static long parseTimeStr(String timeStr, SimpleDateFormat df) {
		Date date;
		long time = 0;
		try {
			date = df.parse(timeStr);
			time = date.getTime();
		} catch (ParseException e) {
			Log.e(TAG, "Cannot parse the time string!!");
			time = getCurrentTime();
			e.printStackTrace();
		}
		return time;
	}
}
