package com.example.SeatingManagement.Controller;


import com.example.SeatingManagement.EntityRequestBody.LocationDto;
import com.example.SeatingManagement.EntityRequestBody.UserDto;
import com.example.SeatingManagement.PayLoad.ApiResponse;
import com.example.SeatingManagement.Services.UserService;
import com.example.SeatingManagement.utils.UserBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserBody userBody) {
        UserDto newUser=this.userService.registerUser(userBody);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<UserDto>> allUsers(){
        List<UserDto> allUser=this.userService.getAllUser();
        return new ResponseEntity<>(allUser,HttpStatus.OK);
    }
    @GetMapping("/{user_id}")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<UserDto> userById(@PathVariable("user_id") String user_id){
        UserDto user=this.userService.getUserById(user_id);
        return ResponseEntity.ok(user);
    }
    @DeleteMapping("/{user_id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("user_id") String user_id){
        this.userService.deleteUserById(user_id);
        return  new ResponseEntity<ApiResponse>( new ApiResponse("User deleted Successfully",true),HttpStatus.OK);

    }
    @PutMapping("/{user_id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserBody userBody,@PathVariable String user_id){
        UserDto updatedUser=this.userService.updateUserById(user_id,userBody);
        return new ResponseEntity<>(updatedUser,HttpStatus.CREATED);

    }
    @PostMapping("/{user_id}/location/{location_id}")
    public ResponseEntity<UserDto> setLocation(@PathVariable String user_id,@PathVariable Integer location_id){
        UserDto user=this.userService.setLocationOfUser(user_id,location_id);
        return ResponseEntity.ok(user);
    }


    @GetMapping("/locationOfUser/{id}")
    public ResponseEntity<LocationDto> findLocationOfUser(@PathVariable String id){
        LocationDto location=this.userService.getLocationOfUserById(id);
        return ResponseEntity.ok(location);
    }

}