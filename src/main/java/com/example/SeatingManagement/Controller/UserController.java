package com.example.SeatingManagement.Controller;


import com.example.SeatingManagement.Entity.Users;
import com.example.SeatingManagement.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<Users> registerUser(@RequestBody Users user) {
        Users newUser = this.userService.registerUser(user);
        return ResponseEntity.ok(newUser);
    }
}