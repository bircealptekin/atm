package email;

import java.util.Properties;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	public void sendEmail(String sender) {
		String recipient, host;
		Scanner input = new Scanner(System.in);
		System.out.println("to: ");
		recipient = input.nextLine();
		host = "localhost";
		
		// getting system properties
		Properties properties = System.getProperties();
		
		// setting up the mail server
		properties.setProperty("mail.smtp.host", host);
		
		// getting the default session object
		Session session = Session.getDefaultInstance(properties);
		
		try {
			String subject, body;
			System.out.println("Subject: ");
			subject = input.nextLine();
			System.out.println("Body: ");
			body = input.nextLine();
			
			// creating a default MimeMessage object
			MimeMessage message = new MimeMessage(session);
			
			// adding senders email to from field
			message.setFrom(new InternetAddress(sender));
			
			// adding recipient's email to from field
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			
			// subject of the email
	        message.setSubject(subject);
	        
	        // set body of the email.
	        message.setText(body);
	        
	        Transport.send(message);
	        System.out.println("Mail successfully sent");
			
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}