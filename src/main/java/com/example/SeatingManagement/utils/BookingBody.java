package com.example.SeatingManagement.utils;

import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.Entity.Seat;
import com.example.SeatingManagement.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingBody {
    private LocalDate date;
    private LocalTime fromTime;
    private LocalTime toTime;
    private String seatId;
    private String userId;
    private Integer locationId;
}