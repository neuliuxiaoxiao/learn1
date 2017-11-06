package com.neu.liuxi.email;

import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	// 邮件发送协议
		private final static String PROTOCOL = "smtp";
		
		// SMTP邮件服务器
		private final static String HOST = "smtp.163.com";
		
		// SMTP邮件服务器默认端口
		private final static String PORT = "25";
		
		// 是否要求身份认证
		private final static String IS_AUTH = "true";
		
		// 是否启用调试模式（启用调试模式可打印客户端与服务器交互过程时一问一答的响应消息）
		private final static String IS_ENABLED_DEBUG_MOD = "true";
	/*获取一个Session
	创建一个默认 MimeMessage 对象，并设置发件人，收件人，主题（From, To, Subject）在消息中。 
	将实际的消息为：
	message.setText("your text goes here");
	发送使用传输对象的消息。*/
	public static void main(String[] args) throws AddressException, MessagingException {
		String from = "neuliuxi@163.com";
		String to="imeilige@gmail.com";
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", PROTOCOL);
		props.setProperty("mail.smtp.host", HOST);
		props.setProperty("mail.smtp.port", PORT);
		props.setProperty("mail.smtp.auth", IS_AUTH);
		props.setProperty("mail.debug",IS_ENABLED_DEBUG_MOD);
		// 创建Session实例对象
		Session session = Session.getDefaultInstance(props);
		// 创建MimeMessage实例对象
				MimeMessage message = new MimeMessage(session);
				// 设置发件人
				message.setFrom(new InternetAddress(from));
				// 设置邮件主题
				message.setSubject("11-03使用javamail发送简单文本邮件");
				// 设置收件人
				message.setRecipient(RecipientType.TO, new InternetAddress(to));
				// 设置发送时间
				message.setSentDate(new Date());
				// 设置纯文本内容为邮件正文
				message.setText("11-03使用POP3协议发送文本邮件测试!!!");
				// 保存并生成最终的邮件内容
				message.saveChanges();
				// 获得Transport实例对象
				Transport transport = session.getTransport();
				// 打开连接
				transport.connect("neuliuxi", "liuxi1314");
				// 将message对象传递给transport对象，将邮件发送出去
				transport.sendMessage(message, message.getAllRecipients());
				// 关闭连接
				transport.close();
	}
}
