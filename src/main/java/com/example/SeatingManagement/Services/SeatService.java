package com.example.SeatingManagement.Services;

import com.example.SeatingManagement.EntityRequestBody.LocationDto;
import com.example.SeatingManagement.EntityRequestBody.SeatDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SeatService {
    SeatDto addNewSeat(SeatDto seatDto);
    List<SeatDto> getAllSeats();
    SeatDto getSeatById(String id);
    void deleteSeatById(String id);
    List<SeatDto> getSeatsByLocation(Integer id);
    void autoGenerateSeats(LocationDto locationDto);
    void autoUpdateExistingSeats(Integer currentCapacity,Integer newCapacity,LocationDto locationDto);

}