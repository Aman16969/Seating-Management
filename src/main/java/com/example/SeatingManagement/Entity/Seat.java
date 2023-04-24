package com.example.SeatingManagement.Entity;
import javax.persistence.CascadeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.stream.events.StartElement;

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
    private String name;
    private Integer r;
    private Integer c;
    private Integer d;
    private boolean isActive=true;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false)
    private Location location;

}
