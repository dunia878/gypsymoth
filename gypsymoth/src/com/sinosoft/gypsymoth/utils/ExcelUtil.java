package com.sinosoft.gypsymoth.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtil {

	
	/**
	 * 生成excel的输出流
	 * @param sheetname sheet名
	 * @param list	参数列表<br/>
	 * 
	 * 	List list = {contentlist,contentlist,contentlist,contentlist}
	 *  contentlist:(美国 , 1 , 1 , 1 , 1)<br/>
	 *  contentlist:(加拿大   , 1 , 1 , 1 , 1)<br/>
	 *  contentlist:(墨西哥   , 1 , 1 , 1 , 1)<br/>
	 *  contentlist:(其他国家, 1 , 1 , 1 , 1)<br/>
	 * 
	 * 
	 * @return 
	 */
	public static HSSFWorkbook toExcel(String sheetname,List list)
	{
		HSSFWorkbook workbook = null;
		try {
			workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet(sheetname);
			if (list!=null&&list.size()>0) {
				for (int i = 0; i < list.size(); i++) {
					List contentlist = (List)list.get(i);
					HSSFRow row = sheet.createRow(i);
					for (int j = 0; j < contentlist.size(); j++) {
						String cellvalue = contentlist.get(j)==null?"":contentlist.get(j).toString();
						row.createCell((short)j).setCellValue(cellvalue);
						sheet.setDefaultColumnWidth((short) 30);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return workbook;
	}
	
	public static void main(String[] args) {
		
		int i = 0;
		for (i = 0; i < 10; i++) 
			;
		System.out.println("first: "+i);
		 
	}
}
