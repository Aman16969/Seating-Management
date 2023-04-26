package com.example.SeatingManagement.Controller;

import com.example.SeatingManagement.Entity.SwiftData;
import com.example.SeatingManagement.ServiceImple.SwiftImple;
import com.example.SeatingManagement.Services.SwiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/swift")
@CrossOrigin
public class SwiftController {
    @Autowired
    private SwiftService swiftService;

    @GetMapping("/")
    public ResponseEntity<List<SwiftData>> getAllUsers(){
        List<SwiftData> swiftData = this.swiftService.getAllUsers();
        return new ResponseEntity<>(swiftData, HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<SwiftData> getUserByEmail(@PathVariable("email") String email){
        SwiftData swiftData = this.swiftService.findDataByEmail(email);
        return new ResponseEntity<>(swiftData, HttpStatus.OK);
    }
}
