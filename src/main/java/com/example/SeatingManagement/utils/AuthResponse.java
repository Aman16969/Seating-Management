package com.example.SeatingManagement.utils;

import com.example.SeatingManagement.Entity.Location;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
    private String accessToken;
    private String accoliteId;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Location location;
}
