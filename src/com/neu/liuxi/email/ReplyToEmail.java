package com.neu.liuxi.email;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ReplyToEmail {

	/*获取Session对象与POP和SMTP 服务器的细节属性。我们需要 POP 细节来检索信息和SMPT详细信息发送邮件。
	创建POP3存储对象，并连接到存储。
	创建文件夹对象，并在您的邮箱中打开相应的文件夹。
	检索消息。
	遍历的消息，如果你想回复键入“Y”或“y”。
	得到消息的所有信息（收件人，发件人，主题，内容）(To,From,Subject, Content) 。
	建立应答消息，使用Message.reply()方法。这个方法配置一个新的消息与适当的收件人和主题。该方法接受一个布尔参数，指示是否只回复给发送者 (false)或回复给所有人（true）。
	从设置，文本和回复到邮件中，并通过传输对象的实例发送。
	关闭传输，文件夹和存储对象分别。*/
	public static void main(String[] args) {
		Date date = null;
		 Properties properties = new Properties();
	      properties.put("mail.store.protocol", "pop3");
	      properties.put("mail.pop3s.host", "pop.163.com");
	      properties.put("mail.pop3s.port", "995");
	      properties.put("mail.pop3.starttls.enable", "true");
	      properties.put("mail.smtp.auth", "true");
	      properties.put("mail.smtp.starttls.enable", "true");
	      properties.put("mail.smtp.host", "relay.jangosmtp.net");
	      properties.put("mail.smtp.port", "25");
	      Session session = Session.getDefaultInstance(properties);
	      try {
	    	  Store store = session.getStore("pop3s");
	          store.connect("pop.163.com", "neuliuxi@163.com",
	             "liuxi1314");//change the user and password accordingly
	          Folder folder = store.getFolder("inbox");
	          if (!folder.exists()) {
	             System.out.println("inbox not found");
	                System.exit(0);
	          }
	          folder.open(Folder.READ_ONLY);
	          BufferedReader reader = new BufferedReader(new InputStreamReader(
	                  System.in));
	          Message[] messages = folder.getMessages();
	          if (messages.length != 0) {
	        	  int i =2;
	        	 // for (int i = 0, n = messages.length; i < n; i++) {
	        		  Message message = messages[i];
	                  date = message.getSentDate();
	                  String from = InternetAddress.toString(message.getFrom());
	                  if (from != null) {
	                     System.out.println("From: " + from);
	                  }
	                  String replyTo = InternetAddress.toString(message.getReplyTo());
	                  if (replyTo != null) {
                       System.out.println("Reply-to: " + replyTo);
	                  }
	                  String to = InternetAddress.toString(message.getRecipients(Message.RecipientType.TO));
                        if (to != null) {
                           System.out.println("To: " + to);
                        }
                        String subject = message.getSubject();
                        if (subject != null) {
                           System.out.println("Subject: " + subject);
                        }
                        Date sent = message.getSentDate();
                        if (sent != null) {
                           System.out.println("Sent: " + sent);
                        }
                        System.out.print("Do you want to reply [y/n] : ");
                        String ans = reader.readLine();
                        if ("Y".equals(ans) || "y".equals(ans)) {
                        	Message replyMessage = new MimeMessage(session);
                            replyMessage = (MimeMessage) message.reply(false);
                            replyMessage.setFrom(new InternetAddress(to));
                            replyMessage.setText("Thanks");
                            replyMessage.setReplyTo(message.getReplyTo());
                            //replyMessage.setReplyTo(message.getReplyTo());
                            Transport t = session.getTransport("smtp");
                            try {
                   	   	     //connect to the smpt server using transport instance
                   		     //change the user and password accordingly	
                   	             t.connect("abc", "****");
                   	             t.sendMessage(replyMessage,
                                           replyMessage.getAllRecipients());
                             } finally {
                                t.close();
                             }
                            System.out.println("message replied successfully ....");

                            // close the store and folder objects
                            folder.close(false);
                            store.close();
                        }/*else if ("n".equals(ans)) {
                            break;
                        }*/
	        	//  }
	          }else {
	              System.out.println("There is no msg....");
	          }
		} catch (Exception e) {
			  e.printStackTrace();		}
	      
	      
	}
	
}
