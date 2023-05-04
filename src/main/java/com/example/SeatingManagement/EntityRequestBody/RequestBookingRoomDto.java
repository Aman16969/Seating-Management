package com.example.SeatingManagement.EntityRequestBody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestBookingRoomDto {
    private Integer id;
    @Email
    private String email;
    private String description;
    private boolean isActive=true;
    private boolean accepted=false;
    private String date;
    private String fromTime;
    private String toTime;
    private String roomType;
}
