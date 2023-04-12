package com.example.SeatingManagement.EntityRequestBody;


import com.example.SeatingManagement.Entity.Location;

import com.example.SeatingManagement.Entity.Seat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Location location;
    private Integer isActive = 1;
    private String role = "USER";
    private String password;



}
