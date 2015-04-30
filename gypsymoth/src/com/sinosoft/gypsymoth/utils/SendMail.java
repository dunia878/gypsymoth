package com.sinosoft.gypsymoth.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 发送邮件
 * 
 * @author lixin
 * 
 */
public class SendMail {

	String smtphost = "mail.ccic.com"; // 邮件服务器

	String user = "agm@ccic.com"; // 发件人用户名

	String password = "100028ccic"; // 发件人地址

	String from = "agm@ccic.com"; // 发件人邮箱

	String name = "中国检验认证集团"; // 显示名称

	String to = "agm@ccic.com";// 收件人邮箱

	String subject = "舞毒蛾检验系统注册激活信息";// 邮件主题

	String text = "激活内容!";// 邮件内容

	/**
	 * 发送邮件
	 * 
	 * @param text
	 *            邮件内容
	 * @param to
	 *            收件人邮箱
	 * @param subject
	 *            邮件主题
	 */
	public void send(String text, String to, String subject) {
		this.text = text;
		this.to = to;
		this.subject = subject;

		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", smtphost);
			props.put("mail.smtp.auth", "true");

			Session session = Session.getInstance(props, null);

			MimeMessage message = new MimeMessage(session);

			InternetAddress fromAddress = new InternetAddress(from, name);
			message.setFrom(fromAddress);
			InternetAddress toAddress = new InternetAddress(to);
			message.addRecipient(Message.RecipientType.TO, toAddress);
			message.setSubject(subject);
			message
					.setText(text);

			Transport transport = session.getTransport("smtp");
			transport.connect(smtphost, user, password);
			transport.sendMessage(message, message
					.getRecipients(Message.RecipientType.TO));
			// transport.send(message);
			transport.close();

		} catch (Exception m) {
			m.printStackTrace();
		}
	}

}
