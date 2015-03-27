package com.zh.mail.base;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class MailInit {

	/**
	 * 初始化邮件收发Session
	 * @author zhanghao
	 * 20150327
	 * */
	public static Session init(String host,final String username,final String password){
		
		//初始化邮件相关配置信息
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.transport.protocol", "smtp"); //校验有关？
		properties.setProperty("mail.store.protocol", "pop3"); //收邮件
		
		//完成邮箱用户名、密码校验
		Authenticator authenticator = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(username, password);
			}
		};
		//创建收发邮件Session
		Session session = Session.getInstance(properties, authenticator);
		return session;
	}
	
	/**
	 * smtp解析工具
	 * @author zhanghao
	 * 20150327
	 * */
	public static String getSmtpByUserName(String username){
		return "smtp." + username.split("@")[1];
	}
	
	/**
	 * pop3解析工具
	 * @author zhanghao
	 * 20150327
	 * */
	public static String getPop3ByUserName(String username){
		return "pop3." + username.split("@")[1];
	}
}
