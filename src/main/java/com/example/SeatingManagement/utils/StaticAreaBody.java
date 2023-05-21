package com.example.SeatingManagement.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StaticAreaBody {
    private Integer locationId;
    private Integer row;
    private Integer col;
    private Integer d;
    private String name;

}
