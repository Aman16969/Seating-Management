package com.example.SeatingManagement.ServiceImple;

import com.example.SeatingManagement.Services.EmailService;
import com.example.SeatingManagement.utils.EmailBody;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailImple implements EmailService {
    @Override
    public void sendMail(EmailBody emailBody) {

        String toEmail=emailBody.getToEmail();
        String subject=emailBody.getSubject();
        String message=emailBody.getMessage();

        //smtp host
        String host="smtp.gmail.com";

        Properties properties=System.getProperties();

        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //secret not to be given to any one and its my personal email crediential
                return new PasswordAuthentication("aman.ranjan@accolitedigital.com","hrziphrvliavtopq");
            }
        });
        //for any error
        session.setDebug(true);
//        message body class
        MimeMessage m=new MimeMessage(session);


        try{
            // from email
            m.setFrom("ranjan.aman540@gmail.com");
            //recipent
            m.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(toEmail)});

            //subject
            m.setSubject(subject);
            //message
            m.setText(message);
            //send
            Transport.send(m);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
