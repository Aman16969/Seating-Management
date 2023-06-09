package com.example.SeatingManagement.EntityRequestBody;


import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.Entity.Seat;
import com.example.SeatingManagement.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    private Integer id;
    private LocalDate date;
    private boolean isActive=true;
    private Seat seat;
    private User user;
    private Location location;

}
