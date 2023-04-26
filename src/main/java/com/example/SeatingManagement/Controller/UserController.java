package com.example.SeatingManagement.Controller;

import com.example.SeatingManagement.EntityRequestBody.UserDto;
import com.example.SeatingManagement.PayLoad.ApiResponse;
import com.example.SeatingManagement.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/")
//    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
        UserDto newUser=this.userService.registerUser(userDto);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
    @PostMapping("/role/{email}")
    public ResponseEntity<String> updateUserRole(@PathVariable String email,@RequestBody Map<Object,Object> role){

        String message=this.userService.updateUserRole(email,role);
        return ResponseEntity.ok(message);
    }
    @GetMapping("/")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<UserDto>> allUsers(){
        List<UserDto> allUser=this.userService.getAllUser();
        return new ResponseEntity<>(allUser,HttpStatus.OK);
    }
    @GetMapping("/{user_id}")
//    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<UserDto> userById(@PathVariable("user_id") Integer user_id){
        UserDto user=this.userService.getUserById(user_id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/email/{email}")
//    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<UserDto> userByEmail(@PathVariable("email") String email){
        UserDto user=this.userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/accolite/{acc_id}")
//    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<UserDto> userByAccoliteID(@PathVariable("acc_id") String accId){
        UserDto user=this.userService.getUserByAccoliteId(accId);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("user_id") Integer user_id){
        this.userService.deleteUserById(user_id);
        return  new ResponseEntity<ApiResponse>( new ApiResponse("User deleted Successfully",true),HttpStatus.OK);
    }
    @PutMapping("/{user_id}/location/{locationId}")
    public ResponseEntity<UserDto> updateUser(@Valid @PathVariable Integer user_id,@PathVariable Integer locationId){
        UserDto updatedUser=this.userService.updateUserLocationById(user_id,locationId);
        return new ResponseEntity<>(updatedUser,HttpStatus.CREATED);
    }

    @PutMapping("/{user_id}/accolite/{accolite}")
    public ResponseEntity<UserDto> updateUser(@Valid @PathVariable Integer user_id,@PathVariable String accolite){
        UserDto updatedUser=this.userService.updateUserAccoliteIdById(user_id,accolite);
        return new ResponseEntity<>(updatedUser,HttpStatus.CREATED);
    }

    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<UserDto> findUserByEmail(@PathVariable String email){
        UserDto userDto=this.userService.getUserByEmail(email);
        return ResponseEntity.ok(userDto);
    }
//    updating user active status
    @PutMapping("/updateActiveStatus/{email}/{value}")
    public ResponseEntity<String> updateUserActiveStatus(@PathVariable String email,@PathVariable boolean value){
        String response=this.userService.updateUserActiveStatus(email,value);
        return ResponseEntity.ok(response);
    }
}