package com.example.SeatingManagement.Services;


import com.example.SeatingManagement.EntityRequestBody.LocationDto;
import com.example.SeatingManagement.EntityRequestBody.UserDto;
import com.example.SeatingManagement.utils.UserBody;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserDto registerUser(UserBody userBody);
    UserDto updateUserById(String id,UserBody userBody);
    UserDto getUserById(String id);
    void deleteUserById(String id);
    List<UserDto>getAllUser();
    LocationDto getLocationOfUserById(String id);
    UserDto setLocationOfUser(String id,Integer location_id);



}
