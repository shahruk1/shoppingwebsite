package com.example.demo;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Component;



@Component
public class emailsender {
	public void sendmail(String emailid) throws AddressException, MessagingException, IOException {
	   Properties props = new Properties();
	   props.put("mail.smtp.auth", "true");
	   props.put("mail.smtp.starttls.enable", "true");
	   props.put("mail.smtp.host", "smtp.gmail.com");
	   props.put("mail.smtp.port", "587");
	   
	   javax.mail.Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	      protected PasswordAuthentication getPasswordAuthentication() {
	         return new PasswordAuthentication("shahruk2245@gmail.com", "zmdukprnmvvlcmgm");
	      }
	   });
	   Message msg = new MimeMessage(session);
	   msg.setFrom(new InternetAddress("shahruk2245@gmail.com", false));
	
	   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailid));
	   msg.setSubject("INVOICE");
	   msg.setContent("items ", "text/html");
	   msg.setSentDate(new Date());
	
	   MimeBodyPart messageBodyPart = new MimeBodyPart();
	   messageBodyPart.setContent("items you purchased", "text/html");
	
	   Multipart multipart = new MimeMultipart();
	   multipart.addBodyPart(messageBodyPart);
	   MimeBodyPart attachPart = new MimeBodyPart();
	
	   attachPart.attachFile("inv.pdf");
	   multipart.addBodyPart(attachPart);
	   msg.setContent(multipart);
	   Transport.send(msg);   
	}
}

