package com.bookrental.bookrental.service.email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

// apache commons EmailService
@Service
public class NewEmailService {
    public boolean sendEmail(String to, String subject, String message) {
        boolean sent = false;
        try {
            Email email = new SimpleEmail();
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
//            email.setAuthenticator(new DefaultAuthenticator("prabinshah2018@gmail.com", "$1PrabinForgotShah#0"));
            email.setAuthenticator(new DefaultAuthenticator("prabinshah2018@gmail.com", "gync atka mogi yris"));
//
            email.setSSLOnConnect(true);
            email.setFrom("prabinshah2018@gmail.com");
            email.setSubject(subject);
            email.setMsg(message);
            email.addTo(to);
            email.send();
            sent = true;
            System.out.println("email sent");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error occured while sending email");
        }
        return sent;
    }
}
