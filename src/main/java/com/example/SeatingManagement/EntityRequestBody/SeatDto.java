package com.example.SeatingManagement.EntityRequestBody;

import com.example.SeatingManagement.Entity.Location;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatDto {
    @NotNull
    private String id;
    @NotNull
    @Pattern(regexp = "^([A-z][0-9]+)$", message="Seat Naming Convention Should Be Like eg:A1")
    private String name;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Location location;
}
