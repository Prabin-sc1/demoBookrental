package com.bookrental.bookrental.service.email;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

import java.util.Properties;
@Service
public class EmailService {
    // apache commons EmailService

    public boolean sendTo(String to, String subject, String message) {

        boolean f = false;

        String from = "prabinshah2018@gmail.com";

        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        System.out.println("Properties:: " + properties);

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Step 1: to get session object
        Session session = Session.getInstance(properties, new Authenticator() {
            //gync atka mogi yris
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("prabinshah2018@gmail.com", "gync atka mogi yris");
            }

        });

        session.setDebug(true);

        // step 2: compose the message text, multimedia

        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(from);
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));

            mimeMessage.setSubject(subject);
//			mimeMessage.setText(message);
            mimeMessage.setContent(message, "text/html");


            // step 3 send message using Transport class
            Transport.send(mimeMessage);
            System.out.println("Sent success....");
            f = true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return f;

    }

}