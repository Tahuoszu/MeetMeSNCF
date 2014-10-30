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
    private static final String CONF_SUBJECT = 
            "Confirmation de votre inscription";
    private static final String CONF_MESSAGE = 
            "Nous vous confirmons votre inscription à MeetMeSCNF.";
    private static final String CONTACT_SUBJECT = 
            "[MeetMeSCNF] Contact";
    
    public static void sendConfirmationEmail(String recipientAddress) {
        
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        try {
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER_ADDRESS, SENDER_NAME));
            
            message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(recipientAddress));
            
            message.setSubject(CONF_SUBJECT);
            message.setText(CONF_MESSAGE);
            
            Transport.send(message);
            
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void sendContactEmail(String senderName, String senderAddress, String messageText) {
        
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        try {
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER_ADDRESS, SENDER_NAME));
            
            message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(SENDER_ADDRESS));
            
            String subject = CONTACT_SUBJECT;
            if(!senderName.isEmpty())
                subject += "-" + senderName;
            message.setSubject(subject);
            message.setText(
                    "From: "+senderName + "\n" + 
                    "Email: " + senderAddress + "\n" +
                    "Message: " + "\n" + messageText);
            
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
