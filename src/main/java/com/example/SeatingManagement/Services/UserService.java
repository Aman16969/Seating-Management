package com.example.SeatingManagement.Services;

import com.example.SeatingManagement.Entity.Users;
import com.example.SeatingManagement.Repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Users registerUser(Users user){
        Users createdUser=this.userRepository.save(user);
        return createdUser;
    }
    public Users updateUser(User user,String id){
        return null;
    }
    public void deleteUser(String id){

    }
    public List<User> getAllUsers(){
        return null;
    }
    public User getUserById(String id){
        return null;
    }

}
