package com.example.SeatingManagement.Controller;

import com.example.SeatingManagement.EntityRequestBody.BookingDto;
import com.example.SeatingManagement.EntityRequestBody.SeatDto;
import com.example.SeatingManagement.PayLoad.ApiResponse;
import com.example.SeatingManagement.Services.BookingServices;
import com.example.SeatingManagement.utils.BookingBody;
import com.example.SeatingManagement.utils.BookingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/booking")
@CrossOrigin
public class BookingController {
    @Autowired
    private BookingServices bookingServices;

    @PostMapping("/")
    public ResponseEntity<BookingResponse> addNewBooking(@RequestBody BookingBody bookingBody){
        BookingResponse bookingResponse = this.bookingServices.addNewBooking(bookingBody);
        return new ResponseEntity<>(bookingResponse, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public  ResponseEntity<List<BookingDto>> getAllBookings(){
        List<BookingDto> allBookings = this.bookingServices.getAllBookings();
        return  new ResponseEntity<>(allBookings, HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<List<SeatDto>> findAvailableSeats(@RequestParam("location") Integer locationId,
                                                            @RequestParam("startDate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                            @RequestParam("endDate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate endDate,

                                                            @RequestParam("startTime") @DateTimeFormat(pattern = "HH:mm:ss") LocalTime startTime,
                                                            @RequestParam("endTime") @DateTimeFormat(pattern = "HH:mm:ss") LocalTime endTime) throws ParseException, ParseException{
        List<SeatDto> availableSeats = this.bookingServices.findAvailableSeats(locationId, startDate, endDate, startTime, endTime);
        return new ResponseEntity<>(availableSeats, HttpStatus.OK);
    }
}
