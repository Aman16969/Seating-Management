package com.example.SeatingManagement.Services;

import com.example.SeatingManagement.EntityRequestBody.BookingDto;
import com.example.SeatingManagement.utils.AttendanceBody;
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

    String setActiveStatus(Integer id,boolean value);
    Map<String, Integer> seatsAvailableOnDatesAndLocation(Integer locationId, LocalDate startDate, LocalDate endDate) throws ParseException;

    Map<String, Integer> seatsAvailableByLocationDateTime(Integer locationId, LocalDate date, LocalTime fromTime, LocalTime toTime);

    void updateAttendance(List<AttendanceBody> attendenceBodyList);
}