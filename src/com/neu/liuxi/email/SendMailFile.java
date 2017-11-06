package com.neu.liuxi.email;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.neu.liuxi.email.SendMailTest.MyAuthenticator;

public class SendMailFile {
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
	
	public static void main(String[] args) throws MessagingException {
		String from = "neuliuxi@163.com";
		String to="imeilige@gmail.com";
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", PROTOCOL);
		props.setProperty("mail.smtp.host", HOST);
		props.setProperty("mail.smtp.port", PORT);
		props.setProperty("mail.smtp.auth", IS_AUTH);
		props.setProperty("mail.debug",IS_ENABLED_DEBUG_MOD);
		// 创建Session实例对象
		Session session = Session.getDefaultInstance(props, new MyAuthenticator());

		// 创建邮件内容
		MimeMessage message = new MimeMessage(session);
		// 邮件主题,并指定编码格式
		message.setSubject("带内嵌图片的HTML邮件", "utf-8");	
		// 发件人
		message.setFrom(new InternetAddress(from));
		// 收件人
		message.setRecipients(RecipientType.TO, InternetAddress.parse(to));
		// 发送时间
		message.setSentDate(new Date());
		// 创建一个MIME子类型为“related”的MimeMultipart对象
				MimeMultipart mp = new MimeMultipart("related");
				// 创建一个表示正文的MimeBodyPart对象，并将它加入到前面创建的MimeMultipart对象中
				MimeBodyPart htmlPart = new MimeBodyPart();
				mp.addBodyPart(htmlPart);
				// 创建一个表示图片资源的MimeBodyPart对象，将将它加入到前面创建的MimeMultipart对象中
				MimeBodyPart imagePart = new MimeBodyPart();
				mp.addBodyPart(imagePart);
				
				// 将MimeMultipart对象设置为整个邮件的内容
				message.setContent(mp);
				
				// 设置内嵌图片邮件体
				DataSource ds = new FileDataSource(new File("C:/Users/Administrator/Pictures/timg.jpg"));
				//DataSource ds = new FileDataSource(new File("resource/firefoxlogo.png"));
				DataHandler dh = new DataHandler(ds);
				imagePart.setDataHandler(dh);
				imagePart.setContentID("firefoxlogo.png");	// 设置内容编号,用于其它邮件体引用
				
				// 创建一个MIME子类型为"alternative"的MimeMultipart对象，并作为前面创建的htmlPart对象的邮件内容
				MimeMultipart htmlMultipart = new MimeMultipart("alternative");
				// 创建一个表示html正文的MimeBodyPart对象
				MimeBodyPart htmlBodypart = new MimeBodyPart();
				// 其中cid=androidlogo.gif是引用邮件内部的图片，即imagePart.setContentID("androidlogo.gif");方法所保存的图片
				htmlBodypart.setContent("<span style='color:red;'>这是带内嵌图片的HTML邮件哦！！！<img src=\"cid:firefoxlogo.png\" /></span>","text/html;charset=utf-8");
				htmlMultipart.addBodyPart(htmlBodypart);
				htmlPart.setContent(htmlMultipart);
				
				// 保存并生成最终的邮件内容
				message.saveChanges();
				
				// 发送邮件
				Transport.send(message);
	}
	/**
	 * 向邮件服务器提交认证信息
	 */
	static class MyAuthenticator extends Authenticator {
		
		private String username = "neuliuxi";
		
		private String password = "liuxi1314";
		
		public MyAuthenticator() {
			super();
		}

		public MyAuthenticator(String username, String password) {
			super();
			this.username = username;
			this.password = password;
		}

		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			
			return new PasswordAuthentication(username, password);
		}
	}
}
