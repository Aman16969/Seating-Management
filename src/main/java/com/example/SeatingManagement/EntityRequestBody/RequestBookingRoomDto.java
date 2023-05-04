package com.example.SeatingManagement.EntityRequestBody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestBookingRoomDto {
    private Integer id;
    @Email
    private String email;
    private LocalDate date;
    private LocalTime fromTime;
    private LocalTime toTime;
    private String roomType;
    private Integer capacity;
    private String description;
    private boolean isActive=true;
    private boolean accepted=false;

}
