package com.example.SeatingManagement.ServiceImple;

import com.example.SeatingManagement.Entity.Booking;
import com.example.SeatingManagement.Entity.User;
import com.example.SeatingManagement.Repository.BookingRepository;
import com.example.SeatingManagement.Services.EmailService;
import com.example.SeatingManagement.utils.EmailBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Service
public class EmailImple implements EmailService {
    @Autowired
    private BookingRepository bookingRepository;
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

//    @Scheduled(fixedDelay = 60000)
//    public void sendDailyReminder() {
//
//        String recipient = "ss.jain@accolitedigital.com";
//        String subject = "Daily Reminder";
//        String message = "This is your daily reminder.";
//        EmailBody emailBody = new EmailBody(recipient, subject, message);
//        sendMail(emailBody);
//    }
//@Scheduled(fixedDelay = 60000)
@Scheduled(cron = "0 0 8 * * *")


    public void sendDailyReminder() {
    LocalDate currentDate = LocalDate.now();
    List<Booking> bookings = bookingRepository.findByDate(currentDate);
    for (Booking booking : bookings) {
        User user = booking.getUser();
        String recipient = user.getEmail();
        String subject = "Seat Booking Reminder for " + currentDate;
        String message = "Dear " + user.getFirstName() + ",\n" +
                "\n" +
                "This is a reminder that you have a seat booked for today.\n" +
                "Seat: " + booking.getSeat().getName() + "\n" +
                "Location: " + booking.getLocation().getName() + "\n" +
                "Time Slot: " + booking.getFromTime() + " - " + booking.getToTime() + "\n" +
                "\n" +
                "Please ensure you occupy the seat within the specified time slot.\n" +
                "\n" +
                "Thank you for using our seat booking system." +
                "\n" +
                "Best regards,\n" +
                "Accolite Digital";
        sendMail(new EmailBody(recipient, subject, message));
    }
}

}
