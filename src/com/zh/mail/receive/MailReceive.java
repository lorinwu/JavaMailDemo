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
		//创建store
		Store store;
		try {
			store = session.getStore();
			store.connect(host,110,username,password);  
			//连接 完成后打开Folder
			Folder folder = store.getFolder("INBOX"); //INBOX是POP3唯一可以使用的文件夹。如果使用IMAP，还可以用其它的文件夹。
			folder.open(Folder.READ_ONLY);
			Message[] messages = folder.getMessages();
			for (Message message : messages) {
				System.out.println(message.getSubject());
			}
			logger.info("接收邮件成功！！");
		} catch (MessagingException e) {
			String msg = "接收邮件失败！！";
			logger.error(msg);
			throw new RuntimeException(msg);
		}
	}
}
