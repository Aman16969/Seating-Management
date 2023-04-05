package com.example.SeatingManagement.Services;


import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.Entity.Role;
import com.example.SeatingManagement.Entity.User;
import com.example.SeatingManagement.ExceptionHandling.ResourceNotFound;
import com.example.SeatingManagement.Repository.LocationRepository;
import com.example.SeatingManagement.Repository.RoleRepository;
import com.example.SeatingManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private RoleRepository roleRepository;
    //Create new User
    public User registerUser(User user){
        User newUser=this.userRepository.save(user);
        return newUser;
    }
//    Update User partially
    public User updateUser(User user,String id){
        User newUser=this.userRepository.findById(id).map(u -> {
            u.setDesignation(user.getDesignation());
            u.setAddress(user.getAddress());
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
//    set user role
    public User setRole(String id, Integer role_id){
        Role role=this.roleRepository.findById(role_id).orElseThrow(()->new ResourceNotFound("Role","Role_id",""+role_id));
        User user=this.userRepository.findById(id).orElseThrow(()->new ResourceNotFound("User","user_id",id));
        user.setRole(role);
        return  this.userRepository.save(user);
    }
    public Location getUserLocation(String id){
        Location location=this.userRepository.findLocationByUserId(id);
        return location;
    }
    public Role getUserRole(String id){
        Role role=this.userRepository.findRoleByUserId(id);
        return role;
    }

}
