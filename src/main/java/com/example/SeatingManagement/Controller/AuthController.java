package com.example.SeatingManagement.Controller;

import com.example.SeatingManagement.Entity.User;
import com.example.SeatingManagement.Repository.UserRepository;
import com.example.SeatingManagement.Security.JwtTokenUtil;
import com.example.SeatingManagement.Services.UserService;
import com.example.SeatingManagement.utils.AuthRequest;
import com.example.SeatingManagement.utils.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest){
        try{
            String email = this.userService.decodeGoogleToken(authRequest.getToken());
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, "password"));
            User user = (User) authentication.getPrincipal();
            String accessToken = jwtTokenUtil.generateAccessToken(user);
            AuthResponse authResponse = new AuthResponse(user.getId(),user.getEmail(), accessToken);
            return new ResponseEntity<>(authResponse, HttpStatus.OK);
        }
        catch (BadCredentialsException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
//    @PostMapping("/decode")
//    public ResponseEntity<Object> decodeToken(@RequestBody String token) {
//        System.out.println(token);
//        String email = userService.decodeGoogleToken(token);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
}
