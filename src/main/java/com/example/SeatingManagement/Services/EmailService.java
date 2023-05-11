package com.example.SeatingManagement.Services;

import com.example.SeatingManagement.utils.EmailBody;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void sendMail(EmailBody emailBody);
}
