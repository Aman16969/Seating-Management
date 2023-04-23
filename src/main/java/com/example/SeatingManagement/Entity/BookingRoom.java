package com.example.SeatingManagement.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime fromTime;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime toTime;
    private String roomType;
    @OneToOne
    @JoinColumn(name = "userAdmin", referencedColumnName = "user_id")
    private User admin;
    @OneToOne
    @JoinColumn(name = "Rooms", referencedColumnName = "id")
    private Room room;
    @OneToOne
    @JoinColumn(name = "users", referencedColumnName = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "locations", referencedColumnName = "id")
    private Location location;
    private boolean isActive=true;

}
