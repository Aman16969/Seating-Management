package com.example.SeatingManagement.Repository;

import com.example.SeatingManagement.Entity.Notification;
import com.example.SeatingManagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findAllByUser(User user);
    List<Notification> findAllByUserAndIsRead(User user, boolean isRead);
}
