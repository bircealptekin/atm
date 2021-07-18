package email;

import java.util.Properties;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	public void sendEmail() {
		String from, to, host;
		Scanner input = new Scanner(System.in);
		System.out.println("Your email address: ");
		from = input.nextLine();
		final String username = from;
		System.out.println("Password: ");
		final String password = input.nextLine();
		System.out.println("to: ");
		to = input.nextLine();
		host = "smtp.gmail.com";
		
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");
		
		// getting the default session object
		 Session session = Session.getInstance(properties,
			      new javax.mail.Authenticator() {
			         protected PasswordAuthentication getPasswordAuthentication() {
			            return new PasswordAuthentication(username, password);
			         }
			      });
		
		try {
			String subject, body;
			System.out.println("Subject: ");
			subject = input.nextLine();
			System.out.println("Body: ");
			body = input.nextLine();
			
			// creating a default MimeMessage object
			MimeMessage message = new MimeMessage(session);
			
			// adding senders email to from field
			message.setFrom(new InternetAddress(from));
			
			// adding recipient's email to from field
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			
			// subject of the email
	        message.setSubject(subject);
	        
	        // set body of the email.
	        message.setText(body);
	        
	        Transport.send(message);
	        System.out.println("Mail successfully sent");
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}