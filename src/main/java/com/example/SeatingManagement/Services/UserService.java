package com.example.SeatingManagement.Services;


import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.Entity.User;
import com.example.SeatingManagement.ExceptionHandling.ResourceNotFound;
import com.example.SeatingManagement.Repository.LocationRepository;
import com.example.SeatingManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LocationRepository locationRepository;

    //Create new User
    public User registerUser(User user){
        User newUser=this.userRepository.save(user);
        return newUser;
    }
//    Update User partially
    public User updateUser(User user,String id){
        User newUser=this.userRepository.findById(id).map(u -> {
            u.setPhoneNumber(user.getPhoneNumber());
            return userRepository.save(u);
        }).orElseThrow(()->new ResourceNotFound("User","user_id",id));
        return newUser;
    }
    //delete User
    public void deleteUser(String id){
        User user=this.userRepository.findById(id).orElseThrow(()->new ResourceNotFound("User","user_id",id));
        this.userRepository.delete(user);

    }
//    Get all user
    public List<User> getAllUsers(){
        List<User> allUsers=this.userRepository.findAll();
        return allUsers;
    }
//    Get user by id
    public User getUserById(String id){
       User user=this.userRepository.findById(id).orElseThrow(()->new ResourceNotFound("User","user_id",id));
       return user;

    }
//    set user location
    public  User setLocation(String id,Integer location_id){
        Location location=this.locationRepository.findById(location_id).orElseThrow(()->new ResourceNotFound("Location","Location_id",""+location_id));
        User user=this.userRepository.findById(id).orElseThrow(()->new ResourceNotFound("User","user_id",id));
        user.setLocation(location);
        User updatedUser=this.userRepository.save(user);
        return updatedUser;
    }

    public Location getUserLocation(String id){
        Location location=this.userRepository.findLocationByUserId(id);
        return location;
    }


}
