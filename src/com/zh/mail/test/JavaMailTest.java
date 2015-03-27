package com.zh.mail.test;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailTest {

	public static void main(String[] args) throws Exception {
		JavaMailTest javaMailTest = new JavaMailTest();
		/*for (int i = 0; i < 10; i++) {
			String context = "测试内容";
			javaMailTest.testSendMail(context);
		}*/
		
		javaMailTest.receiveMail();
		
//		String smtpHostName = "smtp." + "houxianzhangdaye@163.com".split("@")[1];
//		System.out.println(smtpHostName);
	}

	public void testSendMail(String context) throws MessagingException {
		String host = "smtp.163.com";
		String to = "houxianzhangdaye@163.com";
		final String userName = "houxianzhangdaye@163.com";
		final String password = "312500";

		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.transport.protocol", "smtp"); //
		properties.setProperty("mail.store.protocol", "pop3"); //
		properties.setProperty("mail.smtp.host", host);
		
		Authenticator authenticator = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(userName, password);
			}
		};
		
		
		Session session = Session.getInstance(properties,authenticator);

		//创建mime类型邮件
		MimeMessage message = new MimeMessage(session);
		//设置发件人
		message.setFrom(new InternetAddress(userName));
		//设置收件人
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		//设置主题
		message.setSubject("邮件主题");
		//设置内容
		message.setText(context);
		message.setContent(context,"text/html;charset=utf-8");
		//发送邮件
		Transport.send(message);
		
	}
	
	public void receiveMail() throws Exception{
		String host = "pop.163.com";
		final String userName = "houxianzhangdaye@163.com";
		final String password = "312500";

		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", "true");
		//properties.setProperty("mail.transport.protocol", "smtp"); //
		properties.setProperty("mail.store.protocol", "pop3"); //
		properties.setProperty("mail.smtp.host", host);
		
		Authenticator authenticator = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(userName, password);
			}
		};
		
		
		Session session = Session.getInstance(properties,authenticator);
		
		Store store = session.getStore();
		store.connect(host,110,userName,password);  
		
		//连接 完成后打开Folder
		Folder folder = store.getFolder("INBOX"); //INBOX是POP3唯一可以使用的文件夹。如果使用IMAP，还可以用其它的文件夹。
		folder.open(Folder.READ_ONLY);
		Message[] messages = folder.getMessages();
		
		for (Message message : messages) {
			System.out.println(message.getSubject());
		}
	}

}
