/******************************************************************************
 * @file CLog.java
 * @brief
 * @author yaochuan (vk2211@gmail.com)
 * @module CLog
 * @date 2015年8月25日
 * @version 0.1
 * @history v0.1, 2015年8月25日, by yaochuan
 * <p>
 * <p>
 * Copyright (C)
 ******************************************************************************/

package cn.yaochuan.clog;

import java.util.List;

/**
 * This is the base class for Log in projects. This class support original log in android &amp; file log
 *
 * @author yaochuan
 */
public abstract class CLog {
	protected static String CLTAG = "CLog:";
	protected static final int LOG_LEVEL_CRITICAL = 0;
	protected static final int LOG_LEVEL_ERROR = 1;
	protected static final int LOG_LEVEL_WARNING = 2;
	protected static final int LOG_LEVEL_INFO = 3;
	protected static final int LOG_LEVEL_DEBUG = 4;
	protected static final int LOG_LEVEL_VERBOSE = 5;
	private static final int MAX_LOG_LENGTH = 4000;
	private static CLog sInstance = new CLogImpl();

//	public CLog() {
//	}

	/**
	 * Use android original log. If this function not called, there may be NO log.
	 */
	public static void open() {
		sInstance = new CLogImpl();
	}

	/**
	 * Use both android original log &amp; file log. If this function not called, there may be NO log.
	 *
	 * @param filepath the path to save the log
	 * @see #close()
	 */
	public static void open(String filepath) {
		open(filepath, true);
	}

	/**
	 * Use both android original log &amp; file log. If this function not called, there may be NO log.
	 *
	 * @param filepath the path to save the log
	 * @param onlytime true: with no date, false: with date
	 * @see #close()
	 */
	public static void open(String filepath, boolean onlytime) {
		CLog nolog = sInstance; // keep instance, if file log instance not created, this will be restored
		sInstance = new CLogImplEx();
		// if log file cannot be created, ignore file log
		if (!CLogImplEx.init(filepath, onlytime)) {
			sInstance = nolog; // restore no log instance, with less memory assignment
		}
	}

	public static void setGlobalTag(String tag) {
		CLTAG = tag;
	}

	/**
	 * If using file log, memory recyle is necessary. Before stop logging, close() must be called.
	 *
	 * @see #open()
	 */
	public static void close() {
		if (sInstance instanceof CLogImplEx) {
			CLogImplEx.destory();
		}
		sInstance = new NoLog();
	}

	public static void v(String tag, String msg) {
		sInstance.verbose(tag, msg);
	}

	public static void d(String tag, String msg) {
		sInstance.debug(tag, msg);
	}

	public static void i(String tag, String msg) {
		sInstance.info(tag, msg);
	}

	public static void w(String tag, String msg) {
		sInstance.warning(tag, msg);
	}

	public static void e(String tag, String msg) {
		sInstance.error(tag, msg);
	}

	protected abstract int verbose(String tag, String msg);

	protected abstract int debug(String tag, String msg);

	protected abstract int info(String tag, String msg);

	protected abstract int warning(String tag, String msg);

	protected abstract int error(String tag, String msg);

	public static void v(String tag, List msg) {
		for (int i = 0; i < msg.size(); i++) {
			v(tag, msg.get(i).toString());
		}
	}

	public static void d(String tag, List msg) {
		for (int i = 0; i < msg.size(); i++) {
			d(tag, msg.get(i).toString());
		}
	}

	public static void i(String tag, List msg) {
		for (int i = 0; i < msg.size(); i++) {
			i(tag, msg.get(i).toString());
		}
	}

	public static void w(String tag, List msg) {
		for (int i = 0; i < msg.size(); i++) {
			w(tag, msg.get(i).toString());
		}
	}

	public static void f(String tag, String format, Object... args) {
		l(tag, String.format(format, args), LOG_LEVEL_DEBUG);
	}

	public static void f(String tag, int logLevel, String format, Object... args) {
		l(tag, String.format(format, args), logLevel);
	}

	public static void e(String tag, List msg) {
		for (int i = 0; i < msg.size(); i++) {
			e(tag, msg.get(i).toString());
		}
	}

	public static void l(String tag, String longStr, int logLevel) {
		int index = 0;
		int logNum = longStr.length() / MAX_LOG_LENGTH;
		if (logNum > 0) {
			for (int i = 0; i < logNum; i++) {
				String sub = longStr.substring(index, index + MAX_LOG_LENGTH);
				printSub(tag, sub, logLevel);
				index += MAX_LOG_LENGTH;
			}
		} else {
			printSub(tag, longStr, logLevel);
		}
	}

	public static void l(String tag, String longStr) {
		l(tag, longStr, LOG_LEVEL_DEBUG);
	}

	private static void printSub(String tag, String logStr, int logLevel) {
		switch (logLevel) {
		case LOG_LEVEL_ERROR:
			e(tag, logStr);
			break;
		case LOG_LEVEL_WARNING:
			w(tag, logStr);
			break;
		case LOG_LEVEL_INFO:
			i(tag, logStr);
			break;
		case LOG_LEVEL_DEBUG:
			d(tag, logStr);
			break;
		case LOG_LEVEL_VERBOSE:
			v(tag, logStr);
			break;
		}
	}
}
