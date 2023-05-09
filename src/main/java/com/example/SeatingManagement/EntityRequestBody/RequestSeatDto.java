package com.example.SeatingManagement.EntityRequestBody;

import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.Entity.Seat;
import com.example.SeatingManagement.Entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestSeatDto {
    private Integer id;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Location location;
    private User user;
    private User admin;
    private LocalDate date;
    private LocalTime fromTime;
    private LocalTime toTime;
    private String status;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Seat seat;
    private boolean isActive = true;
}
