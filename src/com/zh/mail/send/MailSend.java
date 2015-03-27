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
 * �ʼ�����
 * 
 * @author zhanghao 20150327
 * */
public class MailSend {

	private static final Logger logger = Logger.getLogger(MailSend.class);
	
	public static void main(String[] args) throws AddressException {
		String subject = "�����ʼ�";
		String contect = "Hello , welcome to use javaMail api!!";
		String username = "houxianzhangdaye@163.com";
		String password = "312500";
		InternetAddress add1 = new InternetAddress("houxianzhangdaye@163.com");
		InternetAddress add2 = new InternetAddress();

		new MailSend().sendSingle(subject, contect, username, password, add1);
	}
	
	/**
	 * �ʼ����ͣ����ˣ�
	 * */
	public void sendSingle(String subject,String contect,String username,String password,InternetAddress address){
		String host = MailInit.getSmtpByUserName(username);
		Session session =MailInit.init(host, username, password);
		
		//����mime�ʼ�
		MimeMessage message = new MimeMessage(session);
		try {
			//���÷�����
			message.setFrom(new InternetAddress(username));
			//�����ռ���
			message.addRecipient(Message.RecipientType.TO, address);
			//��������
			message.setSubject("�ʼ�����");
			//��������
			message.setContent(contect,"text/html;charset=utf-8");
			//�����ʼ�
			Transport.send(message);
			logger.info("�ʼ����ͳɹ�����");
		} catch (MessagingException e) {
			String msg = "�ʼ�����ʧ�ܣ���";
			logger.error(msg);
			throw new RuntimeException(msg);
		}
	}
	
	/**
	 * �ʼ�Ⱥ��
	 * */
	public void sendGroup(String subject,String contect,String username,String password,InternetAddress[] addressGroup){
		String host = MailInit.getSmtpByUserName(username);
		Session session =MailInit.init(host, username, password);
		
		//����mime�ʼ�
		MimeMessage message = new MimeMessage(session);
		try {
			//���÷�����
			message.setFrom(new InternetAddress(username));
			//�����ռ���
			message.addRecipients(RecipientType.TO, addressGroup);
			//��������
			message.setSubject("�ʼ�����");
			//��������
			message.setContent(contect,"text/html;charset=utf-8");
			//�����ʼ�
			Transport.send(message);
		} catch (MessagingException e) {
			String msg = "�ʼ�����ʧ�ܣ���";
			logger.error(msg);
			throw new RuntimeException(msg);
		}
		
	}
}
