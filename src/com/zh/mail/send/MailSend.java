package com.zh.mail.send;

import javax.mail.Address;
import javax.mail.Message.RecipientType;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.management.RuntimeErrorException;

import org.apache.log4j.Logger;

import com.sun.activation.registries.MailcapParseException;
import com.zh.mail.base.MailInit;

/**
 * 邮件发送
 * 
 * @author zhanghao 20150327
 * */
public class MailSend {

	private static final Logger logger = Logger.getLogger(MailSend.class);
	
	public static void main(String[] args) throws AddressException {
		String subject = "测试邮件";
		String contect = "Hello , welcome to use javaMail api!!";
		String username = "houxianzhangdaye@163.com";
		String password = "312500";
		InternetAddress add1 = new InternetAddress("houxianzhangdaye@163.com");
		InternetAddress add2 = new InternetAddress();

		new MailSend().sendSingle(subject, contect, username, password, add1);
	}
	
	/**
	 * 邮件发送（单人）
	 * */
	public void sendSingle(String subject,String contect,String username,String password,InternetAddress address){
		String host = MailInit.getSmtpByUserName(username);
		Session session =MailInit.init(host, username, password);
		
		//创建mime邮件
		MimeMessage message = new MimeMessage(session);
		try {
			//设置发件人
			message.setFrom(new InternetAddress(username));
			//设置收件人
			message.addRecipient(Message.RecipientType.TO, address);
			//设置主题
			message.setSubject("邮件主题");
			//设置内容
			message.setContent(contect,"text/html;charset=utf-8");
			//发送邮件
			Transport.send(message);
			logger.info("邮件发送成功！！");
		} catch (MessagingException e) {
			String msg = "邮件发送失败！！";
			logger.error(msg);
			throw new RuntimeException(msg);
		}
	}
	
	/**
	 * 邮件群发
	 * */
	public void sendGroup(String subject,String contect,String username,String password,InternetAddress[] addressGroup){
		String host = MailInit.getSmtpByUserName(username);
		Session session =MailInit.init(host, username, password);
		
		//创建mime邮件
		MimeMessage message = new MimeMessage(session);
		try {
			//设置发件人
			message.setFrom(new InternetAddress(username));
			//设置收件人
			message.addRecipients(RecipientType.TO, addressGroup);
			//设置主题
			message.setSubject("邮件主题");
			//设置内容
			message.setContent(contect,"text/html;charset=utf-8");
			//发送邮件
			Transport.send(message);
		} catch (MessagingException e) {
			String msg = "邮件发送失败！！";
			logger.error(msg);
			throw new RuntimeException(msg);
		}
		
	}
}
