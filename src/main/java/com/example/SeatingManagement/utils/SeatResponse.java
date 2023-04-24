package com.example.SeatingManagement.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatResponse {
    private Integer isAvailable;
    private String seatId;
    private String seatName;
    private Integer seatDirection;
}
