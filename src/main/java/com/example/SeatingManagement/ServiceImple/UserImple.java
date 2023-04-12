package com.example.SeatingManagement.ServiceImple;

import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.Entity.User;
import com.example.SeatingManagement.EntityRequestBody.LocationDto;
import com.example.SeatingManagement.EntityRequestBody.UserDto;
import com.example.SeatingManagement.ExceptionHandling.ResourceNotFound;
import com.example.SeatingManagement.Repository.LocationRepository;
import com.example.SeatingManagement.Repository.UserRepository;
import com.example.SeatingManagement.Services.UserService;
import com.example.SeatingManagement.utils.UserBody;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public UserDto registerUser(UserBody userBody) {
        String id=userBody.getId();
        String fname=userBody.getFirstName();
        String lname=userBody.getLastName();
        String email=userBody.getEmail();
        String password=userBody.getPassword();
        String phoneNo=userBody.getPhoneNumber();
        Integer location_id=userBody.getLocation();
        Location location=this.locationRepository.findById(location_id).orElseThrow(()->new ResourceNotFound("Location","location_id",""+location_id));
        UserDto newUserDto=new UserDto(id,email,fname,lname,password,phoneNo,false,false,location);
        User user=this.modelMapper.map(newUserDto,User.class);
        System.out.println(user);
        User createdUser=this.userRepository.save(user);

        return this.modelMapper.map(createdUser,UserDto.class);
    }

    @Override
    public UserDto updateUserById(String id, UserBody userBody) {
        return null;
    }

    @Override
    public UserDto getUserById(String id) {
        User user=this.userRepository.findById(id).orElseThrow(()->new ResourceNotFound("User","User_id",id));
        UserDto userDto=this.modelMapper.map(user,UserDto.class);
        return userDto;
    }

    @Override
    public void deleteUserById(String id) {
        User user=this.userRepository.findById(id).orElseThrow(()->new ResourceNotFound("User","User_id",id));
        this.userRepository.deleteUserById(id);


    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users=this.userRepository.findAll();
        List<UserDto> allUsers=users.stream().map((e)->this.modelMapper.map(e,UserDto.class)).collect(Collectors.toList());
        return allUsers;
    }
    @Override
    public LocationDto getLocationOfUserById(String id) {
        Location location=this.userRepository.findLocationByUserId(id);
        LocationDto locationDto=this.modelMapper.map(location,LocationDto.class);
        return locationDto;
    }
    @Override
    public UserDto setLocationOfUser(String id, Integer location_id) {
        Location location=this.locationRepository.findById(location_id).orElseThrow(()->new ResourceNotFound("Location","Location_id",""+location_id));
        User user=this.userRepository.findById(id).orElseThrow(()->new ResourceNotFound("User","User_id",id));
        user.setLocation(location);
        User updatedUser=this.userRepository.save(user);
        UserDto userDto=this.modelMapper.map(updatedUser,UserDto.class);
        return userDto;
    }
}
