package com.zh.mail.test;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.junit.Test;

import com.zh.mail.send.MailSend;

public class MailReceiveTest {

	@Test
	public void testSendSingle(){
		MailSend send = new MailSend();
		String subject = "²âÊÔÓÊ¼ş";
		String contect = "Hello , welcome to use javaMail api!!";
		String username = "houxianzhangdaye@163.com";
		String password = "312500";
		InternetAddress add1;
		try {
			add1 = new InternetAddress("houxianzhangdaye@163.com");
			send.sendSingle(subject, contect, username, password, add1);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSendGroup(){
		MailSend send = new MailSend();
		String subject = "²âÊÔÓÊ¼ş";
		String contect = "Hello , welcome to use javaMail api!!";
		String username = "houxianzhangdaye@163.com";
		String password = "312500";
		InternetAddress add1;
		InternetAddress add2;
		try {
			add1 = new InternetAddress("houxianzhangdaye@163.com");
			add2 = new InternetAddress("675990021@qq.com");
			InternetAddress[] addresses = {add1,add2};
			send.sendGroup(subject, contect, username, password, addresses);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
