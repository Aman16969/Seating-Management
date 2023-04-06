package com.example.SeatingManagement.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AuthLogin {
    @GetMapping("/login")
    public String getRequest(){
        return "welcome";
    }
}
