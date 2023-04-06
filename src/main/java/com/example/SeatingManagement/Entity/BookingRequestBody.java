package com.example.SeatingManagement.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestBody {
    private Integer location_id;
    private String user_id;
    private String seat_id;
    private Date date;
}
