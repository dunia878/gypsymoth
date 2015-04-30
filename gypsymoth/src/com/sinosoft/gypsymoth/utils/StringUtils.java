package com.sinosoft.gypsymoth.utils;

import java.io.UnsupportedEncodingException;

/**
 * 字符工具类
 * @author lixin
 *
 */
public class StringUtils extends org.apache.commons.lang.StringUtils
{	
	/**
	 * 编码ISO8859ToGB2312
	 * @param message
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String convertFromISO8859ToGB2312(String message) throws UnsupportedEncodingException
	{
		if (message != null && !message.equals(""))
		{
			byte[] tmpbyte= message.getBytes("ISO8859-1");
			return new String(tmpbyte, "gb2312");
		}
		return "";
	}
	
	/**
	 * 编码UTF8ToGB2312
	 * @param message
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String convertFromUTF8ToGB2312(String message) throws UnsupportedEncodingException
	{
		if (message != null && !message.equals(""))
		{
			byte[] tmpbyte= message.getBytes("UTF-8");
			return new String(tmpbyte, "gb2312");
		}
		return "";
	}

	/**
	 * 编码GB2312ToUTF-8
	 * @param message
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String convertFromGB2312ToUTF8(String message) throws UnsupportedEncodingException
	{
		if (message != null && !message.equals(""))
		{
			byte[] tmpbyte= message.getBytes("gb2312");
			return new String(tmpbyte, "UTF-8");
		}
		return "";
	}
	
	/**
	 * 编码ISO8859ToUTF8
	 * @param message
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String convertFromISO8859ToUTF8(String message) throws UnsupportedEncodingException
	{
		if (message != null && !message.equals(""))
		{
			byte[] tmpbyte= message.getBytes("ISO8859-1");
			return new String(tmpbyte, "UTF-8");
		}
		return "";
	}
	
	/**
	 * 编码UTF8ToISO8859
	 * @param message
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String convertFromUTF8ToISO8859(String message) throws UnsupportedEncodingException
	{
		if (message != null && !message.equals(""))
		{
			byte[] tmpbyte= message.getBytes("UTF-8");
			return new String(tmpbyte, "ISO8859-1");
		}
		return "";
	} 

	/**
	 * 编码ISO8859ToGBK
	 * @param message
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String convertFromISO8859ToGBK(String message) throws UnsupportedEncodingException
	{
		if (message != null && !message.equals(""))
		{
			byte[] tmpbyte= message.getBytes("ISO8859-1");
			return new String(tmpbyte, "GBK");
		}
		return "";
	} 
	

	/**
	 * 编码GBKToISO8859
	 * @param message
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String convertFromGBKToISO8859(String message) throws UnsupportedEncodingException
	{
		if (message != null && !message.equals(""))
		{
			byte[] tmpbyte= message.getBytes("GBK");
			return new String(tmpbyte, "ISO8859-1");
		}
		return "";
	}
	
	/**
	 * 把clob字段中的文字中的回车改为网页中的换行
	 * @param strtemp
	 * @return
	 */
	public static String getStringAddBr(String strtemp){
		StringBuffer sb = new StringBuffer();
		String br = "<br>";
		int j = 0;
        for(int i=0;i<strtemp.length();i++){
        	if(strtemp.charAt(i)=='\r' || strtemp.charAt(i)=='\n'){
        		sb.append(strtemp.substring(j,i+1));
        		sb.append(br);
        		j = i+1;
        	}
        }
		return sb.toString();
	}
	
	
	/**
	 * 转换页面不能显示的字符
	 * @param html
	 * @return
	 */
	public static String codeHtml(String html)
	{
		if(html!=null&&html.indexOf("\"")!=-1){
			html = html.replace("\"", "&#34");
		}
		return html;
	}
	
	/**
	 * 处理特殊字符
	 * @param search
	 * @return
	 */
	public static String processSearch(String search){
		if(search!=null){
			search = search.trim();
			if(search.indexOf("'")!=-1){
				search = search.replace("'", "''");
			}
			if(search.indexOf("\\")!=-1){
				search = search.replace("\\", "\\\\");
			}
			if(search.indexOf("%")!=-1){
				search = search.replace("%", "\\%"); 
			}
		}
		return search; 
	}
	
	public static String leftZero(int num,int para)
	{
		String temp = String.valueOf(para);
		while (temp.length()<num) {
			temp = "0" + temp;
		}
		return temp;
	}
	
	
	/**	加千分符	*/
	public static String tonString(String ton)
	{
		
		int num = ton.length();
		if (num>3) {
			String temp = "";
			for (int i = 0; i <num ; i++) {
				if (i%3==0&&i!=0) {
					temp = "," + temp;
				}
				temp =  ton.charAt(num-i-1) +temp ;
			}
			ton = temp;
		}
		return ton;
	}
	
	/**
	 * 得到去掉前后空字符的字符串，如果为NULL，则返回""
	 * @param inString
	 * @return
	 */
	public static String trimString(String inString)
	{
		String outString  = "";
		if (inString!=null) {
			outString = inString.trim();
		}
		return outString;	
	}
	
}
