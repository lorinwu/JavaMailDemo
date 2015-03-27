package com.zh.mail.receive;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import org.apache.log4j.Logger;

import com.zh.mail.base.MailInit;

public class MailReceive {

	private static final Logger logger = Logger.getLogger(MailReceive.class);
	
	public void receiveMail(String username,String password){
		String host = MailInit.getPop3ByUserName(username);
		Session session =MailInit.init(host, username, password);
		//����store
		Store store;
		try {
			store = session.getStore();
			store.connect(host,110,username,password);  
			//���� ��ɺ��Folder
			Folder folder = store.getFolder("INBOX"); //INBOX��POP3Ψһ����ʹ�õ��ļ��С����ʹ��IMAP�����������������ļ��С�
			folder.open(Folder.READ_ONLY);
			Message[] messages = folder.getMessages();
			for (Message message : messages) {
				System.out.println(message.getSubject());
			}
			logger.info("�����ʼ��ɹ�����");
		} catch (MessagingException e) {
			String msg = "�����ʼ�ʧ�ܣ���";
			logger.error(msg);
			throw new RuntimeException(msg);
		}
	}
}
