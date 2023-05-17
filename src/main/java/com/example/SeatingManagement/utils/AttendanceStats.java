package com.example.SeatingManagement.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceStats {
    private Integer totalBookings;
    private Integer bookedAndPresent;
    private Integer bookedAndAbsent;
}
