package com.example.SeatingManagement.Controller;

import com.example.SeatingManagement.Entity.Notification;
import com.example.SeatingManagement.Entity.User;
import com.example.SeatingManagement.ExceptionHandling.ResourceNotFound;
import com.example.SeatingManagement.Repository.UserRepository;
import com.example.SeatingManagement.Services.NotificationServices;
import com.example.SeatingManagement.Services.UserService;
import com.example.SeatingManagement.utils.NotificationBody;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
@CrossOrigin (origins= "*", allowedHeaders = "*")

public class NotificationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotificationServices notificationServices;

    @PostMapping("/")
    public ResponseEntity<Notification> addNewNotification(@RequestBody NotificationBody notificationBody){
        User user = this.userRepository.findById(notificationBody.getUserId()).orElseThrow(()->new ResourceNotFound("User", "user_id", notificationBody.getUserId().toString()));
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setMessage(notificationBody.getMessage());
        Notification newNotification = this.notificationServices.addNewNotification(notification);
        return new ResponseEntity<>(newNotification, HttpStatus.OK);
    }

    @GetMapping("email/{email}")
    public ResponseEntity<List<Notification>> getAllNotificationByUser(@PathVariable("email") String email){
        User user = this.userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFound("User", "email", email));
        List<Notification> notifications = this.notificationServices.getNotificationBasedOnUser(user);
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @GetMapping("/pending/{id}")
    public ResponseEntity<List<Notification>> getAllPendingNotificationByUser(@PathVariable("id") Integer userId){
        User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFound("User", "user_id", userId.toString()));
        List<Notification> notifications = this.notificationServices.getAllPendingNotificationBasedOnUser(user);
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @PutMapping("/markAsRead/{id}")
    public ResponseEntity<Notification> markNotificationAsRead(@PathVariable("id") Integer notificationId){
        Notification notification = this.notificationServices.markNotificationAsRead(notificationId);
        return new ResponseEntity<>(notification, HttpStatus.OK);
    }
}
