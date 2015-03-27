package com.zh.mail.base;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class MailInit {

	/**
	 * ��ʼ���ʼ��շ�Session
	 * @author zhanghao
	 * 20150327
	 * */
	public static Session init(String host,final String username,final String password){
		
		//��ʼ���ʼ����������Ϣ
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.transport.protocol", "smtp"); //У���йأ�
		properties.setProperty("mail.store.protocol", "pop3"); //���ʼ�
		
		//��������û���������У��
		Authenticator authenticator = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(username, password);
			}
		};
		//�����շ��ʼ�Session
		Session session = Session.getInstance(properties, authenticator);
		return session;
	}
	
	/**
	 * smtp��������
	 * @author zhanghao
	 * 20150327
	 * */
	public static String getSmtpByUserName(String username){
		return "smtp." + username.split("@")[1];
	}
	
	/**
	 * pop3��������
	 * @author zhanghao
	 * 20150327
	 * */
	public static String getPop3ByUserName(String username){
		return "pop3." + username.split("@")[1];
	}
}
