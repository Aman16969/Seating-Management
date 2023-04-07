package com.example.SeatingManagement.Entity;

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
public class UserRequestBody {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;
    private Integer location;





}
