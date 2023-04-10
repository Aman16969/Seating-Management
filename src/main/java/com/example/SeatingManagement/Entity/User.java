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
import java.util.*;


@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// implements UserDetails
public class User{

    @Id
    @Column(name = "accolite_id")
    private String id;
    @Email(message = "Email should be of email type")
    @Column(name="accolite_email")
    private String email;
    private String firstName;
    private String lastName;
@Pattern(regexp = ".*[0-9].*",message = "Password Must Contain One Number")
@Column(name = "user_password",nullable = false)
    private String password;
    @Pattern(regexp = "^\\d{10}$",message = "Invalid phone number")
    @Column(nullable = true)
    private String phoneNumber;
    private boolean isadmin=false;
    private boolean isLoggedIn=false;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location", referencedColumnName = "id")
    private Location location;




}
