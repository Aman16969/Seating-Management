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
public class DisscussionRoom {
    @Id
    @Column(name="disscussionRoom_id")
    private String id;
    private String name;
    private boolean isActive=true;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false)
    private Location location;
}
