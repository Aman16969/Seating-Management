package com.example.SeatingManagement.Controller;


import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.Entity.Role;
import com.example.SeatingManagement.Entity.User;

import com.example.SeatingManagement.PayLoad.ApiResponse;
import com.example.SeatingManagement.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User newUser=this.userService.registerUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
    @GetMapping("/")
    public ResponseEntity<List<User>> allUsers(){
        List<User> allUser=this.userService.getAllUsers();
        return new ResponseEntity<>(allUser,HttpStatus.OK);
    }
    @GetMapping("/{user_id}")
    public ResponseEntity<User> userById(@PathVariable("user_id") String user_id){
        User user=this.userService.getUserById(user_id);
        return ResponseEntity.ok(user);
    }
    @DeleteMapping("/{user_id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("user_id") String user_id){
        this.userService.deleteUser(user_id);
        return  new ResponseEntity<ApiResponse>( new ApiResponse("User deleted Successfully",true),HttpStatus.OK);

    }
    @PutMapping("/{user_id}")
    public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable String user_id){
        User updatedUser=this.userService.updateUser(user,user_id);
        return new ResponseEntity<>(updatedUser,HttpStatus.CREATED);

    }
    @PostMapping("/{user_id}/location/{location_id}")
    public ResponseEntity<User> setLocation(@PathVariable String user_id,@PathVariable Integer location_id){
        User user=this.userService.setLocation(user_id,location_id);
        return ResponseEntity.ok(user);
    }
    @PostMapping("/{user_id}/role/{role_id}")
    public ResponseEntity<User> setRole(@PathVariable String user_id,@PathVariable Integer role_id){
        User user=this.userService.setRole(user_id,role_id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/locationOfUser/{id}")
    public ResponseEntity<Location> findLocationOfUser(@PathVariable String id){
        Location location=this.userService.getUserLocation(id);
        return ResponseEntity.ok(location);
    }
    @GetMapping("/roleOfUser/{id}")
    public ResponseEntity<Role> findRoleOfUser(@PathVariable String id){
        Role role=this.userService.getUserRole(id);
        return ResponseEntity.ok(role);
    }
}