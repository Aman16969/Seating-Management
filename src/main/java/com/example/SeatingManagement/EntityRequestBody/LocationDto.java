package com.example.SeatingManagement.EntityRequestBody;

import com.example.SeatingManagement.Entity.Seat;
import com.example.SeatingManagement.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto {
    private Integer id;
    @NotNull
    private String name;
    private String address;
    private String image;
    @NotNull
    private Integer seatingCapacity;

    @Override
    public String toString() {
        return "LocationDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", seatingCapacity=" + seatingCapacity +
                ", seat=" + seat +
                '}';
    }

    private Set<Seat> seat=new HashSet<>();
}
