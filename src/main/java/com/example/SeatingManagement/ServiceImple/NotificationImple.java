package com.example.SeatingManagement.ServiceImple;

import com.example.SeatingManagement.Entity.Notification;
import com.example.SeatingManagement.Entity.User;
import com.example.SeatingManagement.ExceptionHandling.ResourceNotFound;
import com.example.SeatingManagement.Repository.NotificationRepository;
import com.example.SeatingManagement.Services.NotificationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationImple implements NotificationServices {
    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public Notification addNewNotification(Notification notification) {
        Notification newNotification = this.notificationRepository.save(notification);
        return newNotification;
    }

    @Override
    public List<Notification> getNotificationBasedOnUser(User user) {
        List<Notification> notifications = this.notificationRepository.findAllByUser(user);
        return notifications;
    }

    @Override
    public List<Notification> getAllPendingNotificationBasedOnUser(User user) {
        List<Notification> notifications = this.notificationRepository.findAllByUserAndIsRead(user, false);
        return notifications;
    }

    @Override
    public Notification markNotificationAsRead(Integer notificationId) {
        Notification notification = this.notificationRepository.findById(notificationId).orElseThrow(()->new ResourceNotFound("Notification", "notificationId", notificationId.toString()));
        notification.setRead(true);
        Notification updatedNotification = this.notificationRepository.save(notification);
        return updatedNotification;
    }
}
