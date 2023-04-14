package com.example.SeatingManagement.Services;

import com.example.SeatingManagement.Entity.*;
import com.example.SeatingManagement.EntityRequestBody.BookingDto;
import com.example.SeatingManagement.EntityRequestBody.SeatDto;
import com.example.SeatingManagement.ExceptionHandling.ResourceNotFound;
import com.example.SeatingManagement.Repository.BookingRepository;
import com.example.SeatingManagement.Repository.LocationRepository;
import com.example.SeatingManagement.Repository.SeatRepository;
import com.example.SeatingManagement.Repository.UserRepository;
import com.example.SeatingManagement.utils.BookingBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public interface BookingServices {
    BookingDto createNewBooking(BookingBody bookingBody);
    BookingDto updateExistingBooking(BookingBody bookingBody);
    void deleteBookingOfUserById(Integer id);
    List<BookingDto> getAllBooking();
    List<BookingDto> getAllBookingByUser(Integer user_id);
    List<BookingDto> getAllBookingByDate(LocalDate date);
    Map<String,String> getAllBookingByDateAndLocation(LocalDate date, Integer location_id);
    List<BookingDto> getAllBookingByLocation(Integer location_id);

    Map<String,String> allAvailableSeats(Integer location_id,LocalDate date);

}
