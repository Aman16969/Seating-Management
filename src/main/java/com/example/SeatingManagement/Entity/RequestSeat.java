package com.example.SeatingManagement.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="seat_requests")
public class RequestSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location",nullable = true, referencedColumnName = "id")
    private Location location;
    @OneToOne
    @JoinColumn(name = "admin", referencedColumnName = "user_id")
    private User admin;
    @OneToOne
    @JoinColumn(name = "user", referencedColumnName = "user_id")
    private User user;
    private LocalDate date;
    private LocalTime fromTime;
    private LocalTime toTime;
    private String status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat",nullable = true, referencedColumnName = "seat_id")
    private Seat seat;
    private boolean isActive = true;
}
