package com.sinosoft.gypsymoth.utils;

import java.io.File;

/**
 * 常量定义
 * @author lixin
 *
 */
public interface Constants
{
	/**
	 * 基础属性
	 */
    public final static String SEPARATOR = File.separator;  
	/**
	 * 分页数据
	 */
	public final static Integer NUMOFEACHPAGE = 10; //分页中每页显示的数据
//	public final static Integer INDEXNUMBER = 4; //首页显示数据条数
//	public final static Integer NUMOFEACHPAGESP = 1; //分页中每页显示的数据(某些特殊情况)
	
	/**
	 * Session管理属性
	 */
	public final static String ACCOUNT_SESSION = "account";
	public final static String RIGHT_SESSION = "menu_parent";
	public final static String RIGHT_ID_SESSION = "right_id";
	public final static String USER_LOGOUT_FORCE = "session_user_logout_force";
	
	/**	用户的person_id 如 00000055	*/
	public final static String ACCOUNT_PERSON_ID = "person_id";
	/**	用户的所在的机构等级	*/
	public final static String ACCOUNT_PERSON_IS_CHILD = "is_child";
	/**	用户是否是管理员用户 1:是 0:不是 	*/
	public final static String ACCOUNT_ISADMIN = "is_admin";
	/**	用户所属机构	唯一的机构标示	*/
	public final static String ACCOUNT_PERSON_ORG = "person_org";
	/**	用户所属机构简称	*/
	public final static String ACCOUNT_PERSON_ORGNAME = "org_name";
	/**	用户所属机构4位代码 	不唯一的机构标示	*/
	public final static String ACCOUNT_ORG_ID4 = "org_four_id";	//4位的机构号
	/**	用户信息	*/
	public final static String ACCOUNT_PERSON = "person";
	/**	用户类型	0:检查员/1:授权签字人/2:协调员	*/
	public final static String ACCOUNT_ALLOT_LEVEL = "allot_type";
	
	
	/**	客户信息	*/
	public final static String ACCOUNT_CLIENT = "client";
	public final static String ACCOUNT_DEFAULT_PASSWORD = "111111";
	
	/**
	 * 账户类型
	 */
	public final static String ACCOUNTTYPE_PERSON = "1";
	public final static String ACCOUNTTYPE_CLIENT = "2";
	
	/**
	 * 人员类型
	 */

	public final static String USER_ZJ = "1";
	public final static String USER_JT = "2";
	public final static String USER_YZGS = "3";
	public final static String USER_BM = "4";
	public final static String USER_ONE = "5";
	public final static String USER_TWO = "6";
	
	/**
	 * 省、市、国家
	 */
	public final static String PROVINCE = "province";
	public final static String CITY = "city";
	public final static String COUNTRY = "country";
	
	/**
	 * 通过sinosoft.properties文件配置常量
	 * @author lixin
	 *
	 */
	public static interface PropertyKeys
	{	
		public final static String NOT_LOGIN = "not.login";
	    
	    //数据库参数
	    public final static String CON_DRIVER = "connection.driver";
	    public final static String CON_URL = "connection.url";
	    public final static String CON_NAME = "connection.name";
	    public final static String CON_PASS = "connection.password";
	}

}
