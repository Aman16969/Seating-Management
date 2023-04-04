package com.example.SeatingManagement.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name="Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @Column(name = "accolite_id")
    private String id;
    @Email(message = "Email should be of email type")
    @Column(name="accolite_email")
    private String email;
    private String firstName;
    private String lastName;
    @NotEmpty
    @Size(min=6,max=20,message = "Password Must Be Minimum Of 6 Character")
    @Pattern(regexp = ".*[0-9].*",message = "Password Must Contain One Number")
    private String password;
    @Column(nullable = true)
    private String designation;
    @Pattern(regexp = "^\\d{10}$")
    @Column(nullable = true)
    private String phoneNumber;
    @Column(nullable = true)
    private String address;


}
