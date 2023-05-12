package com.example.SeatingManagement.Controller;

import com.example.SeatingManagement.Services.EmailService;
import com.example.SeatingManagement.utils.EmailBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sendEmail")
@CrossOrigin(origins= "*", allowedHeaders = "*")
public class EmailController {
    @Autowired
    private EmailService emailService;
    @PostMapping("/send")
    public ResponseEntity<?> sendMail(@RequestBody EmailBody emailBody){
        this.emailService.sendMail(emailBody);
        return ResponseEntity.ok("Mail Sent successfully");
    }

}
