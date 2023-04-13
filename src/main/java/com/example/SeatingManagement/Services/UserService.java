package com.example.SeatingManagement.Services;


import com.example.SeatingManagement.EntityRequestBody.LocationDto;
import com.example.SeatingManagement.EntityRequestBody.UserDto;
import com.example.SeatingManagement.utils.UserBody;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserDto registerUser(UserDto userDto);


    UserDto updateUserById(Integer id, UserDto userDto);
    UserDto getUserById(Integer id);
    void deleteUserById(Integer id);
    List<UserDto>getAllUser();
    UserDto getUserByEmail(String email);




}
