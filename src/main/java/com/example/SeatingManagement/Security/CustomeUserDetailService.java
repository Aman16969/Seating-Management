//package com.example.SeatingManagement.Security;
//
//import com.example.SeatingManagement.Entity.User;
//import com.example.SeatingManagement.ExceptionHandling.ResourceNotFound;
//import com.example.SeatingManagement.Repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomeUserDetailService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user=this.userRepository.findByEmail(username).orElseThrow(()->new ResourceNotFound("User","email",username));
//
//        return user;
//    }
//}
