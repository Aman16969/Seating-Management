package com.example.SeatingManagement.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="locations")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String name;
    private Integer seatingCapacity = 0;
    private Integer boardRoomCapacity = 0;
    private Integer discussionRoomCapacity = 0;
    private Integer rs = 1;
    private Integer cs = 1;
    @Column(columnDefinition = "LONGTEXT")
    private String image;
    private boolean isActive = true;
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Seat> seats = new HashSet<>();
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Room> roomss = new HashSet<>();
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<User> users = new HashSet<>();
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<RequestSeat> requestSeatBodies = new HashSet<>();
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<LocationStaticArea> locationStaticAreas = new HashSet<>();
}
