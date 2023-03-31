package com.example.SeatingManagement.Entity;


import javax.persistence.*;

@Entity
public class UserDetails {

    @Id
    private Integer id;
    private String phoneNo;
    private String location;
    private String Address;
    private String role;
    private String employeeId;

    @OneToOne
    @JoinColumn(name = "users")
    private Users users;

}
