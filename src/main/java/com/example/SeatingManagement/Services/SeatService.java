package com.example.SeatingManagement.Services;

import com.example.SeatingManagement.Entity.Seat;
import com.example.SeatingManagement.EntityRequestBody.LocationDto;
import com.example.SeatingManagement.EntityRequestBody.SeatDto;
import com.example.SeatingManagement.utils.SeatBody;
import com.example.SeatingManagement.utils.SeatResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface SeatService {
    SeatDto addNewSeat(SeatBody seatBody);
    List<SeatDto> getAllSeats();
    SeatDto getSeatById(String id);
    SeatResponse deleteSeatById(String id, String value);
    List<Seat> getSeatsByLocation(Integer id);
//    void autoGenerateSeats(LocationDto locationDto);
//    void autoUpdateExistingSeats(Integer currentCapacity,Integer newCapacity,LocationDto locationDto);

    SeatResponse getSeatByPosition(Integer locationId, Integer row, Integer column);

    SeatDto updateSeat(SeatDto seatDto);

    SeatResponse changeSeatDirection(String seatId);
}