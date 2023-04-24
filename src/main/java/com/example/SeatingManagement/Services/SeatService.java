package com.example.SeatingManagement.Services;

import com.example.SeatingManagement.EntityRequestBody.LocationDto;
import com.example.SeatingManagement.EntityRequestBody.SeatDto;
import com.example.SeatingManagement.utils.SeatBody;
import com.example.SeatingManagement.utils.SeatResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SeatService {
    SeatDto addNewSeat(SeatBody seatBody);
    List<SeatDto> getAllSeats();
    SeatDto getSeatById(String id);
    SeatResponse deleteSeatById(String id, String value);
    List<SeatDto> getSeatsByLocation(Integer id);
//    void autoGenerateSeats(LocationDto locationDto);
//    void autoUpdateExistingSeats(Integer currentCapacity,Integer newCapacity,LocationDto locationDto);

    SeatResponse getSeatByPosition(Integer locationId, Integer row, Integer column);

    SeatDto updateSeat(SeatDto seatDto);
}