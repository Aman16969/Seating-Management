package com.example.SeatingManagement.utils;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AreaResponse {
    private Integer isAvailable;
    private String areaId;
    private String areaName;
    private Integer d;
}
