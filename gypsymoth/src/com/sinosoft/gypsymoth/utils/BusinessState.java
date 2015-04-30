package com.sinosoft.gypsymoth.utils;

public class BusinessState {
	
	/** 
	 * 业务单暂存 0
	 */
	public static String BUSINESS_TEMP = "0";		
	
	/**
	 * 业务单待受理 1
	 */
	public static String BUSINESS_ACCEPTWAIT = "1";			
	
	/**
	 * 业务单未通过 2
	 */
	public static String BUSINESS_ACCEPTNOT = "2";			 
	
	/**
	 * 业务单已受理 3
	 */
	public static String BUSINESS_ACCEPTSUCCESS = "3";		
	
	/**
	 * 业务单已分配 4
	 */
	public static String Business_ALLOTED = "4";				
	
	/**
	 * 业务单已提交检查结果 5
	 */
	public static String Business_CHECKED = "5";				
	
	
	/**	分配状态初始状态，待分配 0	*/
	public static String Allot_FIRST = "0";	
	/**	已分配给二级协调员	1 */
	public static String Allot_SECOND_COOR = "1";	
	/**	已分配给二级检查员及授权签字人 2	*/
	public static String Allot_SECOND_INS = "2";	
	/**	已分配给三级协调员 3	*/
	public static String Allot_THIRD_COOR = "3";	
	/**	已分配给三级检查员及授权签字人 4	*/
	public static String Allot_THIRD_INS = "4";	
	
	/** 有效分配 0	 */
	public static String ASSIGNMENT_APPLY = "0";
	/** 无效分配 1	 */
	public static String ASSIGNMENT_NOAPPLY = "1";
	
	
	/**	业务单预-览本地国家	*/
	public static String APPLY_CHINA = "CHINA";
	
	public static long LONG_ZERO = 0;
	public static long LONG_ONE = 1;
	public static long LONG_TWO = 2;
	public static long LONG_THREE = 3;
	
}
