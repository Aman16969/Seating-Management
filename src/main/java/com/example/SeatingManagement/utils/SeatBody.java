package com.example.SeatingManagement.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatBody {
    private Integer locationId;
    private Integer row;
    private Integer col;
    private Integer dir;
    private String name;
}
