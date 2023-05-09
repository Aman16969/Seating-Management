package com.example.SeatingManagement.ServiceImple;

import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.Entity.RequestSeat;
import com.example.SeatingManagement.Entity.User;
import com.example.SeatingManagement.ExceptionHandling.ResourceNotFound;
import com.example.SeatingManagement.Repository.LocationRepository;
import com.example.SeatingManagement.Repository.RequestSeatRepository;
import com.example.SeatingManagement.Repository.UserRepository;
import com.example.SeatingManagement.Services.RequestSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestSeatImple implements RequestSeatService {
    @Autowired
    private RequestSeatRepository requestSeatRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public RequestSeat createNewSeatRequest(RequestSeat requestSeat) {
        RequestSeat newRequestSeat = this.requestSeatRepository.save(requestSeat);
        return newRequestSeat;
    }

    @Override
    public RequestSeat updateSeatRequest(RequestSeat requestSeat) {
        RequestSeat updatedRequestSeat = this.requestSeatRepository.save(requestSeat);
        return updatedRequestSeat;
    }

    @Override
    public RequestSeat cancelSeatRequest(Integer id) {
        RequestSeat requestSeat = this.requestSeatRepository.findById(id).orElseThrow(()->new ResourceNotFound("RequestSeat", "id", id.toString()));
        requestSeat.setActive(false);
        RequestSeat cancelledRequestSeat = this.requestSeatRepository.save(requestSeat);
        return cancelledRequestSeat;
    }

    @Override
    public List<RequestSeat> getAllLocationRequests(Integer locationId) {
        Location location = this.locationRepository.findById(locationId).orElseThrow(()->new ResourceNotFound("Location", "id", locationId.toString()));
        List<RequestSeat> requestSeats = this.requestSeatRepository.findByLocationAndIsActive(location, true);
        return requestSeats;
    }

    @Override
    public List<RequestSeat> getAllUserRequests(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFound("User", "user_id", userId.toString()));
        List<RequestSeat> requestSeats = this.requestSeatRepository.findByUserAndIsActive(user, true);
        return requestSeats;
    }

    @Override
    public List<RequestSeat> getAllAdminRequests(Integer adminId) {
        User admin = this.userRepository.findById(adminId).orElseThrow(()->new ResourceNotFound("User", "user_id", adminId.toString()));
        List<RequestSeat> requestSeats = this.requestSeatRepository.findByAdminAndIsActive(admin, true);
        return requestSeats;
    }

    @Override
    public RequestSeat getRequestById(Integer id) {
        RequestSeat requestSeat = this.requestSeatRepository.findById(id).orElseThrow(()->new ResourceNotFound("RequestSeat", "id", id.toString()));
        return requestSeat;
    }
}
