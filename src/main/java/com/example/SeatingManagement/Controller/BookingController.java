package com.example.SeatingManagement.Controller;

import com.example.SeatingManagement.Entity.Seat;
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
        BookingResponse bookingResponse = this.bookingServices.createNewBooking(bookingBody);
        return new ResponseEntity<>(bookingResponse, HttpStatus.CREATED);
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
    @GetMapping("/user")
    public ResponseEntity<List<BookingDto>> getBookingsByUser(@RequestParam("user") Integer user_id) {
        List<BookingDto> allBookingsByUser=this.bookingServices.getAllBookingByUser(user_id);
        return new ResponseEntity<>(allBookingsByUser,HttpStatus.OK);
    }
    @GetMapping("/locationAndDate")
    public ResponseEntity<Map<String,String>> getBookingsByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @RequestParam("location") Integer location_id) {
        Map<String,String> allBookingsByDateAndLocation=this.bookingServices.getAllBookingByDateAndLocation(date,location_id);
        return new ResponseEntity<>(allBookingsByDateAndLocation,HttpStatus.OK);
    }
    @GetMapping("/date")
    public ResponseEntity<List<BookingDto>> getBookingsByDate( @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<BookingDto> allBookingsByDate=this.bookingServices.getAllBookingByDate(date);
        return new ResponseEntity<>(allBookingsByDate,HttpStatus.OK);
    }
    @GetMapping("/availabe/locationAndDate")
    public ResponseEntity<Map<String,String>> getAvailabeBookingsByDate(@RequestParam("location") Integer location_id,@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date ) {
        Map<String,String> allAvailableBookingsByDateAndLocation=this.bookingServices.allAvailableSeats(location_id,date);
        return new ResponseEntity<>(allAvailableBookingsByDateAndLocation,HttpStatus.OK);
    }

    @GetMapping("/bookedOrNotOnDate")
    public ResponseEntity<Integer> isUserBookedSeatOrNotOnDate(@RequestParam("user") Integer user_id, @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        Integer isBookedOrNot = this.bookingServices.isBookedOrNot(user_id, date);
        return new ResponseEntity<>(isBookedOrNot, HttpStatus.OK);
    }

    @GetMapping("/available/locationAndDates")
    public ResponseEntity<Map<String, Integer>> seatsAvailableOnDatesAndLocation(@RequestParam("location") Integer locationId, @RequestParam("startDate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam("endDate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate endDate) throws ParseException {
        Map<String, Integer> availableSeats = this.bookingServices.seatsAvailableOnDatesAndLocation(locationId, startDate, endDate);
        return new ResponseEntity<>(availableSeats, HttpStatus.OK);
    }
}
