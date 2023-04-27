package com.example.SeatingManagement.ServiceImple;

import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.Entity.User;
import com.example.SeatingManagement.EntityRequestBody.UserDto;
import com.example.SeatingManagement.ExceptionHandling.ResourceNotFound;
import com.example.SeatingManagement.Repository.LocationRepository;
import com.example.SeatingManagement.Repository.UserRepository;
import com.example.SeatingManagement.Services.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserImple implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDto registerUser(UserDto userDto) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password=bCryptPasswordEncoder.encode("password");
       userDto.setPassword(password);
        userDto.setRole("USER");
        User user=this.modelMapper.map(userDto,User.class);
        User createdUser=this.userRepository.save(user);
        return this.modelMapper.map(createdUser,UserDto.class);
    }

    @Override
    public String decodeGoogleToken(String token) {
        String[] chunks = token.split("\\.");
        String payload = new String(Base64.decodeBase64(chunks[1]));
        Map<String, String> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(payload, new TypeReference<Map<String, String>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        Optional<User> user=this.userRepository.findByEmail(map.get("email"));
        if(user.isEmpty()) {
            BCryptPasswordEncoder b=new BCryptPasswordEncoder();
            String password= b.encode("password");
            User newuser=new User();
            newuser.setEmail(map.get("email"));
            newuser.setFirstName(map.get("given_name"));
            newuser.setLastName(map.get("family_name"));
            newuser.setLocation(null);
            newuser.setPassword(password);
//            UserDto userDto = new UserDto(null, map.get("email"), map.get("given_name"), map.get("family_name"), "USER",password);
//            System.out.println(userDto);
            User nuser = this.modelMapper.map(newuser, User.class);
            this.userRepository.save(nuser);
        }
        return map.get("email");
    }

    @Override
    public UserDto updateUserLocationById(Integer userId, Integer locationId) {
        User user=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFound("User","user id",""+userId));
        Location location=this.locationRepository.findById(locationId).orElseThrow(()->new ResourceNotFound("Location","Location_id",""+locationId));
        user.setLocation(location);
        User updatedUser=this.userRepository.save(user);
        UserDto updateduserDto=this.modelMapper.map(updatedUser,UserDto.class);
        return updateduserDto;
    }

    @Override
    public UserDto getUserById(Integer id) {
        User user=this.userRepository.findById(id).orElseThrow(()->new ResourceNotFound("User","User_id",""+id));
        UserDto userDto=this.modelMapper.map(user,UserDto.class);
        return userDto;
    }

    @Override
    public void deleteUserById(Integer id) {
        User user=this.userRepository.findById(id).orElseThrow(()->new ResourceNotFound("User","User_id",""+id));
        this.userRepository.delete(user);


    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users=this.userRepository.findAll();
        List<UserDto> allUsers=users.stream().map((e)->this.modelMapper.map(e,UserDto.class)).collect(Collectors.toList());
        return allUsers;
    }
//    @Override
//    public LocationDto getLocationOfUserById(String id) {
//        Location location=this.userRepository.findLocationByUserId(id);
//        LocationDto locationDto=this.modelMapper.map(location,LocationDto.class);
//        return locationDto;
//    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user=this.userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFound("User","email",email));
        UserDto userDto=this.modelMapper.map(user,UserDto.class);
        return userDto;
    }

    @Override
    public String updateUserActiveStatus(String email,boolean value) {
        User user=this.userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFound("user","userEmail",email));
        user.setActive(value);
        this.userRepository.save(user);
        return user.getEmail()+"'s active status is changed to "+value;

    }

    @Override
    public String updateUserRole(String email, Map<Object,Object> role) {
        User user=this.userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFound("user","userEmail",email));
        System.out.println(role.get("value"));
        user.setRole((String)role.get("value"));
        this.userRepository.save(user);
        return "user Role changes";
    }

    @Override
    public UserDto getUserByAccoliteId(String accoliteId) {
        User user=this.userRepository.findByAccoliteId(accoliteId).orElseThrow(()->new ResourceNotFound("User","accoliteId",accoliteId));
        UserDto userDto=this.modelMapper.map(user,UserDto.class);
        return userDto;
    }

    @Override
    public UserDto updateUserAccoliteIdById(Integer userId, String accoliteId) {
        User user=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFound("User","user id",""+userId));
        user.setAccoliteId(accoliteId);
        User updatedUser=this.userRepository.save(user);
        UserDto updatedUserDto=this.modelMapper.map(updatedUser,UserDto.class);
        return updatedUserDto;
    }

//    @Override
//    public UserDto setLocationOfUser(String id, Integer location_id) {
//        Location location=this.locationRepository.findById(location_id).orElseThrow(()->new ResourceNotFound("Location","Location_id",""+location_id));
//        User user=this.userRepository.findById(id).orElseThrow(()->new ResourceNotFound("User","User_id",id));
//        user.setLocation(location);
//        User updatedUser=this.userRepository.save(user);
//        UserDto userDto=this.modelMapper.map(updatedUser,UserDto.class);
//        return userDto;
//    }
}
