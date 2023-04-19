package com.example.SeatingManagement.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingRoomBody {
    private LocalDate date;
    private LocalTime fromTime;
    private LocalTime toTime;
    private String adminEmail;
    private String userEmail;
    private String disscussionRoom_id;
    private String boardRoom_id;
    private Integer location_id;
}
