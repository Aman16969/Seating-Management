package com.example.SeatingManagement.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
    @OneToOne
    @JoinColumn(name = "seats", referencedColumnName = "seat_id")
    private Seat seat;
    @OneToOne
    @JoinColumn(name = "users", referencedColumnName = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "locations", referencedColumnName = "id")
    private Location location;
    private boolean isActive=true;
    private LocalTime fromTime;
    private LocalTime toTime;
}
