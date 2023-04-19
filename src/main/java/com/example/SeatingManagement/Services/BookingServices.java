package com.example.SeatingManagement.Services;

import com.example.SeatingManagement.Entity.User;
import com.example.SeatingManagement.EntityRequestBody.BookingDto;
import com.example.SeatingManagement.EntityRequestBody.SeatDto;
import com.example.SeatingManagement.utils.BookingBody;
import com.example.SeatingManagement.utils.BookingResponse;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Service
public interface BookingServices {
    BookingResponse addNewBooking(BookingBody bookingBody);
    List<BookingDto> getAllBookings();

    List<SeatDto> findAvailableSeats(Integer locationID, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime);
    List<BookingDto> getAllBookingsByLocation(Integer locationId);
//    List<BookingDto> getAllBookingsByDate(LocalDate date);
    List<BookingDto> getAllBookingsByUser(Integer userId);
//    List<BookingDto> getAllBookingsByDateAndLocation(LocalDate date, Integer locationId);
//    List<BookingDto> getAllBookingsByDateAndUser(LocalDate date, Integer userId);
}
