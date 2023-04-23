package com.example.SeatingManagement.EntityRequestBody;

import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.Entity.Room;
import com.example.SeatingManagement.Entity.User;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingRoomDto {
    private Integer id;
    private LocalDate date;
    private LocalTime fromTime;
    private LocalTime toTime;
    private boolean isActive;
    private String roomTime;
    private Room room;
    private User admin;
    private User user;
    private Location location;


}
