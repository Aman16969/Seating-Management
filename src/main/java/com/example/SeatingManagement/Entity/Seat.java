package com.example.SeatingManagement.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="seats")
public class Seat {
    @Id
    @Column(name="seat_id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "locations", referencedColumnName = "id")
    private Location location;
}
