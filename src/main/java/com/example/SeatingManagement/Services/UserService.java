package com.example.SeatingManagement.Services;


import com.example.SeatingManagement.Entity.User;
import com.example.SeatingManagement.EntityRequestBody.LocationDto;
import com.example.SeatingManagement.EntityRequestBody.UserDto;
import com.example.SeatingManagement.utils.UserBody;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService {
    UserDto registerUser(UserDto userDto);
    String decodeGoogleToken(String token);
    UserDto updateUserLocationById(Integer UserId, Integer locationId);
    UserDto getUserById(Integer id);
    void deleteUserById(Integer id);
    List<UserDto>getAllUser();
    UserDto getUserByEmail(String email);
   String updateUserActiveStatus(String email, boolean value);
   String updateUserRole(String email,Map<Object,Object> role);




}
