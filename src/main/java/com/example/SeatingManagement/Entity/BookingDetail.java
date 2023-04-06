package com.example.SeatingManagement.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date date;
    @OneToOne
    @JoinColumn(name = "seats", referencedColumnName = "seat_id")
    private Seat seat;
    @OneToOne
    @JoinColumn(name = "users", referencedColumnName = "accolite_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "locations", referencedColumnName = "id")
    private Location location;


    public BookingDetail(Date date, Seat seat, User user, Location location) {
        this.date=date;
        this.location=location;
        this.user=user;
        this.seat=seat;
    }
}
