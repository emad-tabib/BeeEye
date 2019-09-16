package com.generic.util;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;


public class SendMail extends SelTestCase{

	public static String readIndexFile(String filePath) throws IOException {
		String content = "";
		BufferedReader in = new BufferedReader(new FileReader(filePath));
	    try {
	        String str;
	        while ((str = in.readLine()) != null) {
	            content +=str;
	        }
	    } catch (IOException e) {
	    } finally{
	    	in.close();
	    }
		return content;
	}
	
	public static void sendSummeryMail(String log_path){
		getCurrentFunctionName(true);
		final String mail_to  = getCONFIG().getProperty("toEmail");
		final String from = getCONFIG().getProperty("fromEmail");
		final String password = getCONFIG().getProperty("fromEmailPassword");
		
		logs.debug(MessageFormat.format(LoggingMsg.SENDING_MAIL_TO, mail_to));
		
		String host = "smtp.gmail.com";
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.starttls.enable", "true");
			
	
			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, password);
				}
			});

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail_to));
			message.setSubject("AutomationReport_"+ReportUtil.now("dd_MMMMM_hhmmss"));
			message.setContent(readIndexFile(log_path),"text/html");

			Transport.send(message);

			logs.debug(LoggingMsg.SUCCESSFULLY_SENT_MSG);
		}
		catch (Throwable e) {
			logs.debug(LoggingMsg.FAILED_SENDING_MSG);
			System.out.println(e.getMessage());
		}
		getCurrentFunctionName(false);
	}
}