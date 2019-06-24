package com.zzjy.abgame.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * @author Administrator
 *
 */
public final class DateUtil {
	/**
	 * 时间获取的方法
	 */
	public static void dateUtil() {
		//创建Calendar单例
		Calendar car = Calendar.getInstance();
		//获取年
		int y = car.get(Calendar.YEAR);
		//获取月
		int m = car.get(Calendar.MONTH)+1;
		//获取日
		int d = car.get(Calendar.DATE);
		//获取时
		int hh = car.get(Calendar.HOUR_OF_DAY);
		//获取分
		int mm = car.get(Calendar.MINUTE);
		//获取秒
		int ss = car.get(Calendar.SECOND);
		//输出格式
		System.out.println(y+"-"+m+"-"+d+" "+hh+":"+mm+":"+ss);
		
	}
	
	/**
	 * 日期转字符串格式，用于输出时间
	 * @param time
	 * @param pattern
	 * @return
	 */
	public static String getDateToStr(Date time,String pattern){
		//日期初始化对象并将字符串作为形参
		DateFormat df = new SimpleDateFormat(pattern);
		//返回初始化后的date类型转为String类型的时间
		return df.format(time);
	}
	
	/**
	 * 字符串转日期格式，用于存储时间数据
	 * @param time
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateToString(String time,String pattern) throws ParseException {
		//日期格式化对象并将字符串作为形参
		DateFormat df = new SimpleDateFormat(pattern);
		//返回String类型转为Date类型的时间
		return df.parse(time);
	}
	
	
	
	
}
