package com.example.SeatingManagement.Services;

import com.example.SeatingManagement.Entity.Notification;
import com.example.SeatingManagement.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationServices {
    Notification addNewNotification(Notification notification);
    List<Notification> getNotificationBasedOnUser(User user);

    List<Notification> getAllPendingNotificationBasedOnUser(User user);
    Notification markNotificationAsRead(Integer notificationId);
}
