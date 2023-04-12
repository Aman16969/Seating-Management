package com.example.SeatingManagement.Controller;

import com.example.SeatingManagement.Entity.User;
import com.example.SeatingManagement.Security.JwtTokenUtil;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String a=bCryptPasswordEncoder.encode("Aman@16969");
        System.out.println(a);
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            User user = (User) authentication.getPrincipal();
            String accessToken = jwtTokenUtil.generateAccessToken(user);
            AuthResponse authResponse = new AuthResponse(user.getEmail(), accessToken);
            System.out.println(authResponse);
            return new ResponseEntity<>(authResponse, HttpStatus.OK);
        }
        catch (BadCredentialsException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
