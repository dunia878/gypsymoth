package com.sinosoft.gypsymoth.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

public interface CommonTool {

	public String convertFromISO8859ToGB2312(String message)
			throws UnsupportedEncodingException;

	public String convertFromUTF8ToGB2312(String message)
			throws UnsupportedEncodingException;

	public String convertFromGB2312ToUTF8(String message)
			throws UnsupportedEncodingException;

	public String convertFromGBKToISO8859(String message)
			throws UnsupportedEncodingException;

	public String convertFromISO8859ToGBK(String message)
			throws UnsupportedEncodingException;

	public String convertFromISO8859ToUTF8(String message)
			throws UnsupportedEncodingException;

	public String convertFromUTF8ToISO8859(String message)
			throws UnsupportedEncodingException;

	public String getStandardTime();

	public BigDecimal getDateSerialNo();

	public String getStringAddBr(String str);

	public String convertFormASCIIToUTF8(String message)
			throws UnsupportedEncodingException;
}
