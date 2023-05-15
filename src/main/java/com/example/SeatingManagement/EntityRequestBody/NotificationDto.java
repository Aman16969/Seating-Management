package com.example.SeatingManagement.EntityRequestBody;

import com.example.SeatingManagement.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
    private Integer id;
    private User user;
    private String message;
    private boolean isRead = false;
}
