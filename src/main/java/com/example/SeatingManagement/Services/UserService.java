package com.example.SeatingManagement.Services;


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
    UserDto updateUserById(Integer id, String accoliteId);
    UserDto getUserById(Integer id);
    void deleteUserById(Integer id);
    List<UserDto>getAllUser();
    UserDto getUserByEmail(String email);
    String softDelete(String email, boolean value);




}
