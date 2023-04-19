package com.example.SeatingManagement.EntityRequestBody;

import com.example.SeatingManagement.Entity.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DisscussionRoomDto {
    private String id;
    private String name;
    private boolean isActive=true;
    private Location location;
}
