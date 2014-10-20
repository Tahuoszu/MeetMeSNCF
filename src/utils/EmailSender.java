package utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
	
	private static final String SENDER_ADDRESS = "selma.sadouk@gmail.com";
	private static final String SENDER_NAME = "MeetMeSNCF";
	private static final String EMAIL_SUBJECT = 
			"Confirmation de votre inscription";
	private static final String EMAIL_MESSAGE = 
			"Nous vous confirmons votre inscription à MeetMeSCNF.";

	
	public static void sendConfirmationEmail(String recipientAddress) {
	    
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		try {
		    
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(SENDER_ADDRESS, SENDER_NAME));
			
			message.addRecipient(
			        Message.RecipientType.TO,
					new InternetAddress(recipientAddress));
			
			message.setSubject(EMAIL_SUBJECT);
			message.setText(EMAIL_MESSAGE);
			
			Transport.send(message);
			
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
