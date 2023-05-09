package com.example.SeatingManagement.Services;

import com.example.SeatingManagement.Entity.RequestSeat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RequestSeatService {
    RequestSeat createNewSeatRequest(RequestSeat requestSeat);
    RequestSeat updateSeatRequest(RequestSeat requestSeat);
    RequestSeat cancelSeatRequest(Integer id);
//    List<RequestSeat> getAllRequests();
    List<RequestSeat> getAllLocationRequests(Integer locationId);
    List<RequestSeat> getAllUserRequests(Integer userId);
    List<RequestSeat> getAllAdminRequests(Integer adminId);

    RequestSeat getRequestById(Integer id);
}
