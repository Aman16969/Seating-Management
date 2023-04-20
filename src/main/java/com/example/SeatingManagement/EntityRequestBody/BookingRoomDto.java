package com.example.SeatingManagement.EntityRequestBody;

import com.example.SeatingManagement.Entity.BoardRoom;
import com.example.SeatingManagement.Entity.DisscussionRoom;
import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.Entity.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.time.LocalTime;

public class BookingRoomDto {
    private Integer id;
    private LocalDate date;
    private LocalTime fromTime;
    private LocalTime toTime;
    private boolean isActive;
    private BoardRoom boardRoom;
    private DisscussionRoom disscussionRoom;
    private User admin;
    private User user;
    private Location location;


}
