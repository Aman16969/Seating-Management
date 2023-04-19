package com.example.SeatingManagement.Services;


import com.example.SeatingManagement.EntityRequestBody.BookingRoomDto;
import com.example.SeatingManagement.utils.BookingRoomBody;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingRoomServices {
    BookingRoomDto createNewBooking(BookingRoomBody bookingRoomBody);
    String setActiveStatus(Integer id,boolean value);
    List<BookingRoomDto> getBookingsByAdmin(String email);
    List<BookingRoomDto> getBookingByUser(String email);
}
