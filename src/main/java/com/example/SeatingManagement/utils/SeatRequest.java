package com.example.SeatingManagement.utils;

import com.example.SeatingManagement.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatRequest {
    private String email;
    private LocalDate date;
    private LocalTime fromTime;
    private LocalTime toTime;
    private String description;
    private Integer locationId;

}
