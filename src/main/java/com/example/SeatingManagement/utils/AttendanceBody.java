package com.example.SeatingManagement.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceBody {
    private String empID;
    private String name;
    private LocalDate date;
    private LocalTime inTime;
    private LocalTime outTime;

    @Override
    public String toString() {
        return "AttendanceBody{" +
                "empID='" + empID + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", inTime=" + inTime +
                ", outTime=" + outTime +
                '}';
    }
}