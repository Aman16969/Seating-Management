package com.example.SeatingManagement.Services;

import com.example.SeatingManagement.Entity.Seat;
import com.example.SeatingManagement.EntityRequestBody.BookingDto;
import com.example.SeatingManagement.utils.BookingBody;
import com.example.SeatingManagement.utils.BookingResponse;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public interface BookingServices {
    BookingResponse createNewBooking(BookingBody bookingBody);
    BookingDto updateExistingBooking(BookingBody bookingBody);
    void deleteBookingOfUserById(Integer id);
    List<BookingDto> getAllBooking();
    List<BookingDto> getAllBookingByUser(Integer user_id);
    List<BookingDto> getAllBookingByDate(LocalDate date);
    Map<String,String> getAllBookingByDateAndLocation(LocalDate date, Integer location_id);
    List<BookingDto> getAllBookingByLocation(Integer location_id);

    Map<String,String> allAvailableSeats(Integer location_id,LocalDate date);

    Integer isBookedOrNot(Integer userId, LocalDate date);

    Map<String, Integer> seatsAvailableOnDatesAndLocation(Integer locationId, LocalDate startDate, LocalDate endDate) throws ParseException;

    void deleteBookingsOfLocation(Integer location_id);
}
