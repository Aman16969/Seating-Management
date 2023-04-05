package com.example.SeatingManagement.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

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
//    @JsonIgnore
    private String password;
    @Column(nullable = true)
    private String designation;
    @Pattern(regexp = "^\\d{10}$",message = "Invalid phone number")
    @Column(nullable = true)
    private String phoneNumber;
    @Column(nullable = true)
    private String address;
    private boolean isAdmin=false;

    @ManyToOne
    @JoinColumn(name = "locations", referencedColumnName = "id")
    private Location location;
}
