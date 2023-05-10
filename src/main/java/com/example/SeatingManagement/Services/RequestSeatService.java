package com.example.SeatingManagement.Services;

import com.example.SeatingManagement.Entity.RequestSeat;
import com.example.SeatingManagement.utils.SeatRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RequestSeatService {
    RequestSeat createNewSeatRequest(SeatRequest seatRequest);
    RequestSeat updateSeatRequest(RequestSeat requestSeat);
    RequestSeat cancelSeatRequest(Integer id);
    RequestSeat acceptSeatRequest(Integer id);
//    List<RequestSeat> getAllRequests();
    List<RequestSeat> getAllLocationRequests(Integer locationId);
    List<RequestSeat> getAllUserRequests(Integer userId);

    RequestSeat getRequestById(Integer id);
}
