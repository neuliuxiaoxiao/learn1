package com.neu.liuxi.email;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class TestMail {
	 public static void main(String[] args) throws Exception {
	        Properties props = new Properties();
	        props.put("mail.smtp.host", "smtp.qq.com");
	        props.put("mail.smtp.auth", "true");
	        //基本的邮件会话
	        Session session = Session.getInstance(props);
	        //构造信息体
	        MimeMessage message = new MimeMessage(session); 
	        //发件地址
	        Address address = new InternetAddress("1352149308@qq.com");
	        message.setFrom(address);
	        //收件地址
	        Address toAddress = new InternetAddress("1352149308@qq.com");
	        message.setRecipient(MimeMessage.RecipientType.TO, toAddress);
	        
	        //主题
	        message.setSubject("Hello world");
	        //正文
	        message.setText("Hello world");
	                
	        message.saveChanges(); // implicit with send()
	        //Exception in thread "main" javax.mail.NoSuchProviderException: smtp
	        session.setDebug(true);
	        Transport transport = session.getTransport("smtp");
	        //transport.connect("smtp.qq.com", "1352149308@qq.com", "cGZkcHJldGpnY3lnaGhhaA==");
	        transport.connect("smtp.qq.com", "1352149308@qq.com", "d2h5emFkbDEyMw==");
	        //发送
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close(); 

	    }
}