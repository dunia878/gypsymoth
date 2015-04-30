package com.sinosoft.gypsymoth.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CommonToolImpl implements CommonTool {

	public String convertFromISO8859ToGB2312(String message)
			throws UnsupportedEncodingException {

		return StringUtils.convertFromISO8859ToGB2312(message);
	}

	public String convertFromUTF8ToGB2312(String message)
			throws UnsupportedEncodingException {

		return StringUtils.convertFromUTF8ToGB2312(message);
	}

	public String convertFromGB2312ToUTF8(String message)
			throws UnsupportedEncodingException {
		return StringUtils.convertFromGB2312ToUTF8(message);
	}

	public String convertFromGBKToISO8859(String message)
			throws UnsupportedEncodingException {
		return StringUtils.convertFromGBKToISO8859(message);
	}

	public String convertFromISO8859ToGBK(String message)
			throws UnsupportedEncodingException {
		return StringUtils.convertFromISO8859ToGBK(message);
	}

	public String convertFromUTF8ToISO8859(String message)
			throws UnsupportedEncodingException {
		return StringUtils.convertFromUTF8ToISO8859(message);
	}

	public String getStandardTime() {
		Date createDates = new Date();
		String createDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(createDates);
		return createDate;
	}

	// 14位顺序号
	public BigDecimal getDateSerialNo() {

		Random random = new Random();
		Integer randNumber = random.nextInt();
		randNumber = Math.abs(randNumber);
		String result = Integer.toString(randNumber).substring(0, 6);

		Date createDates = new Date();
		String createDate = new SimpleDateFormat("yyyyMMdd")
				.format(createDates);
		createDate = createDate + result;
		BigDecimal serialno = new BigDecimal(createDate);

		return serialno;
	}

	public String getStringAddBr(String str) {

		return StringUtils.getStringAddBr(str);
	}

	public String convertFormASCIIToUTF8(String message)
			throws UnsupportedEncodingException {

		String tempstr = "";
		String[] result;
		StringBuffer sb = new StringBuffer();

		int i;
		int AsciiCode;
		result = message.split(" ");
		for (i = 1; i < result.length; i++) {
			AsciiCode = Integer.parseInt(result[i]);
			if (AsciiCode < 0) {
				int ii = 65536 + AsciiCode;
				byte temp[] = { (byte) (ii / 256), (byte) (ii % 256) };
				tempstr = new String(temp, "gb2312");
				sb.append(tempstr);
			} else {
				sb.append((char) AsciiCode);
			}
		}
		return sb.toString();
	}

	public String convertFromISO8859ToUTF8(String message)
			throws UnsupportedEncodingException {
		return StringUtils.convertFromISO8859ToUTF8(message);
	}

}
