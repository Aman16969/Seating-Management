package com.example.SeatingManagement.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// implements UserDetails
public class User implements UserDetails {

    @Id
    @Column(name="accolite_id")
    private String id;
    @Email(message = "Email should be of email type")
    @Column(name="accolite_email")
    private String email;
    private String firstName;
    private String lastName;
    @Pattern(regexp = "^\\d{10}$",message = "Invalid phone number")
    @Column(nullable = true)
    private String phoneNumber;
    private String role = "USER";
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "location",nullable = false)
    private Location location;
    private Integer isActive = 1;
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("USER"));
//        authorityList.add(new SimpleGrantedAuthority("ADMIN"));
        return authorityList;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

