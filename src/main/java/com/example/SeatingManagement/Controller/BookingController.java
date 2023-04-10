package com.example.SeatingManagement.Controller;

import com.example.SeatingManagement.EntityRequestBody.BookingDto;
import com.example.SeatingManagement.PayLoad.ApiResponse;
import com.example.SeatingManagement.Services.BookingServices;
import com.example.SeatingManagement.utils.BookingBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/booking")
@CrossOrigin
public class BookingController {
    @Autowired
    private BookingServices bookingServices;
    @PostMapping("/")
    public ResponseEntity<BookingDto> addNewBooking(@RequestBody BookingBody bookingBody){
        BookingDto newbooking=this.bookingServices.createNewBooking(bookingBody);
        return new ResponseEntity<>(newbooking, HttpStatus.CREATED);
    }
    @GetMapping("/")
    public ResponseEntity<List<BookingDto>> getAllBookings(){
        List<BookingDto> allbookings=this.bookingServices.getAllBooking();
        return new ResponseEntity<>(allbookings, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBookingById(@PathVariable("id") Integer id) {
        this.bookingServices.deleteBookingOfUserById(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Booking deleted Successfully", true), HttpStatus.OK);
    }
    @GetMapping("/location")
    public ResponseEntity<List<BookingDto>> getBookingsByLocation(@RequestParam("location") Integer location_id) {
        List<BookingDto> allBookingsByLocation=this.bookingServices.getAllBookingByLocation(location_id);
        return new ResponseEntity<>(allBookingsByLocation,HttpStatus.OK);
    }
    @GetMapping("/locationAndDate")
    public ResponseEntity<List<BookingDto>> getBookingsByDate( @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,@RequestParam("location") Integer location_id) {

        List<BookingDto> allBookingsByDateAndLocation=this.bookingServices.getAllBookingByDateAndLocation(date,location_id);
        return new ResponseEntity<>(allBookingsByDateAndLocation,HttpStatus.OK);
    }
    @GetMapping("/date")
    public ResponseEntity<List<BookingDto>> getBookingsByDate( @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<BookingDto> allBookingsByDate=this.bookingServices.getAllBookingByDate(date);
        return new ResponseEntity<>(allBookingsByDate,HttpStatus.OK);
    }

//    @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
}
