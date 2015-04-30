package com.sinosoft.gypsymoth.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CalendarUtils {

	/**
	 * 得到当前月的第一天
	 * @param calendar
	 * @param dateFormat
	 * @return
	 */
	public static String beginMonthDay(Calendar calendar,SimpleDateFormat dateFormat)
	{
		String beginDay = "";
		String year_begin = String.valueOf(calendar.get(Calendar.YEAR)); //当前年
		String month_begin = String.valueOf(calendar.get(Calendar.MONTH)+1);	//当前月
		beginDay = year_begin+"-"+month_begin+"-01";	//当前月初日期
		
		return beginDay;
	}
	
	/**
	 * 得到当前月的最后一天
	 * @param calendar
	 * @param dateFormat
	 * @return
	 */
	public static String endMonthDay(Calendar calendar,SimpleDateFormat dateFormat) throws ParseException
	{
		String endDay = "";
		String year_begin = String.valueOf(calendar.get(Calendar.YEAR)); //当前年
		String month_begin = String.valueOf(calendar.get(Calendar.MONTH)+1);	//当前月
		String beginDay = year_begin+"-"+month_begin+"-01";	//当前月初日期
		Date aDate = dateFormat.parse(beginDay);
		calendar.setTime(aDate);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		
		String year_end = String.valueOf(calendar.get(Calendar.YEAR));
		String month_end = String.valueOf(calendar.get(Calendar.MONTH)+1);
		endDay = year_begin+"-"+month_begin+"-"+String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));	//当前月末日期
		 
		return endDay;
	}
	
	
	/**
	 * 得到两个日期间的月份顺序列表(包含开始月及结束月) 
	 * 
	 * @param begin	开始日期 如2010-10
	 * @param end	结束日期 如2011-05
	 * @return	List<HashMap> [{month=10, year=2010}, {month=11, year=2010}, {month=12, year=2010}, {month=1, year=2011}, {month=2, year=2011}, {month=3, year=2011}, {month=4, year=2011}, {month=5, year=2011}]
	 * @throws ParseException
	 */
	public static List monthBetweenYear(Date begin,Date end) throws ParseException
	{
		List list = new ArrayList();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(begin);
		 
		while (calendar.getTime().compareTo(end)<=0) {
			HashMap map = new HashMap();
			map.put("year", calendar.get(Calendar.YEAR));
			map.put("month", calendar.get(Calendar.MONTH)+1);
			map.put("name", calendar.get(Calendar.YEAR)+"年"+(calendar.get(Calendar.MONTH)+1)+"月");
			list.add(map);
			calendar.add(Calendar.MONTH, 1);
		}
		return list;
	}
	
	/**
	 * 得到两个日期间的季度顺序列表(包含开始月及结束月) 
	 * 
	 * @param begin	开始日期 如2010-10
	 * @param end	结束日期 如2011-05
	 * @return	List<HashMap> [{year=2010, quarter=4}, {year=2011, quarter=1}, {year=2011, quarter=2}]
	 * @throws ParseException
	 */
	public static List quarterBetweenYear(Date begin,Date end) throws ParseException
	{
		List list = new ArrayList();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(begin);
		 
		int tempquarter = 0;
		while (calendar.getTime().compareTo(end)<=0) {
			int currmonth = calendar.get(Calendar.MONTH)+1;
			int currquarter =  getQuarterByMonth(currmonth);	
			if (currquarter!=tempquarter) {
				HashMap map = new HashMap();
				map.put("year", calendar.get(Calendar.YEAR));
				map.put("quarter",currquarter);
				map.put("name", calendar.get(Calendar.YEAR)+"年 第"+currquarter+"季度");
				list.add(map);
			}
			tempquarter = currquarter;
			calendar.add(Calendar.MONTH, 1);
		}
		return list;
	}
	
	/**
	 * 得到两个日期间的年度顺序列表(包含开始月及结束月) 
	 * 
	 * @param begin	开始日期 如2010-10
	 * @param end	结束日期 如2011-05
	 * @return	List<HashMap> [{year=2010, quarter=4}, {year=2011, quarter=1}, {year=2011, quarter=2}]
	 * @throws ParseException
	 */
	public static List yearBetweenYear(Date begin,Date end) throws ParseException
	{
		List list = new ArrayList();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(begin);
		while (calendar.getTime().compareTo(end)<=0) {
			HashMap map = new HashMap();
			map.put("year", calendar.get(Calendar.YEAR));
			map.put("name", calendar.get(Calendar.YEAR)+"年"); 
			list.add(map);
			calendar.add(Calendar.YEAR, 1);
		} 
		return list;
	}
	
	/**
	 * 根据月份返回季度
	 * @param month
	 * @return
	 */
	public static int getQuarterByMonth(int month){
		if (month==1||month==2||month==3) {
			return 1;
		}else if(month==4||month==5||month==6){
			return 2;
		}else if(month==7||month==8||month==9){
			return 3;
		}else if(month==10||month==11||month==12){
			return 4;
		}
		return 0;
	}
	
}
