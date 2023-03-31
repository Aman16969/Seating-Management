package com.example.SeatingManagement.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.UUID;

@Entity
@Table(name="Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Email(message = "Email should be of email type")
    private String email;
    private String name;
    private String password;
    private String designation;

    @OneToOne(mappedBy = "users",cascade = CascadeType.ALL)
    private UserDetails userDetail;

}
