package com.example.SeatingManagement.Services;


import com.example.SeatingManagement.Entity.BookingRoom;
import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.EntityRequestBody.BookingRoomDto;
import com.example.SeatingManagement.EntityRequestBody.RoomDto;
import com.example.SeatingManagement.utils.BookingRoomBody;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public interface BookingRoomServices {
    BookingRoom createNewBooking(BookingRoomBody bookingRoomBody);
    String setActiveStatus(Integer id,boolean value);
    List<BookingRoom> getBookingsByAdmin(String email);
    List<BookingRoom> getBookingByUser(String email);
    List<RoomDto> roomsAvailableByLocationDateTime(Integer locationId, LocalDate date, LocalTime fromTime, LocalTime toTime);
}
