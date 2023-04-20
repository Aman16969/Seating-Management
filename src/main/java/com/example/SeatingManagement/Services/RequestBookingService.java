package com.example.SeatingManagement.Services;

import com.example.SeatingManagement.Entity.RequestBookingRoom;
import com.example.SeatingManagement.EntityRequestBody.RequestBookingRoomDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RequestBookingService {
    RequestBookingRoomDto createNewRequest(RequestBookingRoomDto requestBookingRoomDto);
    String setActiveStatus(Integer id,boolean value);
    String setAccepted(Integer id,boolean value);
    List<RequestBookingRoomDto> allRequest();
    List<RequestBookingRoomDto> allRequestByUser(String email);

}
