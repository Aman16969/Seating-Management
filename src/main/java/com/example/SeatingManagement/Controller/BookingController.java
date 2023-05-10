package com.example.SeatingManagement.Controller;

import com.example.SeatingManagement.EntityRequestBody.BookingDto;
import com.example.SeatingManagement.EntityRequestBody.SeatDto;
import com.example.SeatingManagement.PayLoad.ApiResponse;
import com.example.SeatingManagement.Services.BookingServices;
import com.example.SeatingManagement.utils.AttendanceBody;

import com.example.SeatingManagement.utils.AvailableSeat;
import com.example.SeatingManagement.utils.BookingBody;
import com.example.SeatingManagement.utils.BookingResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/booking")
@CrossOrigin(origins= "*", allowedHeaders = "*")
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
        List<BookingDto> allBookings=this.bookingServices.getAllBooking();
        return new ResponseEntity<>(allBookings, HttpStatus.CREATED);
    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<ApiResponse> deleteBookingById(@PathVariable("id") Integer id) {
//        this.bookingServices.deleteBookingOfUserById(id);
//        return new ResponseEntity<ApiResponse>(new ApiResponse("Booking deleted Successfully", true), HttpStatus.OK);
//    }
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
    public ResponseEntity<Map<String,String>> getBookingsByDateAndLocation(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @RequestParam("location") Integer location_id) {
        Map<String,String> allBookingsByDateAndLocation=this.bookingServices.getAllBookingByDateAndLocation(date,location_id);
        return new ResponseEntity<>(allBookingsByDateAndLocation,HttpStatus.OK);
    }
    @GetMapping("/date")
    public ResponseEntity<List<BookingDto>> getBookingsByDate( @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<BookingDto> allBookingsByDate=this.bookingServices.getAllBookingByDate(date);
        return new ResponseEntity<>(allBookingsByDate,HttpStatus.OK);
    }
    @GetMapping("/available/locationAndDate")
    public ResponseEntity<Map<String,String>> getAvailabeSeatsByDate(@RequestParam("location") Integer location_id,@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date ) {
        Map<String,String> allAvailableBookingsByDateAndLocation=this.bookingServices.allAvailableSeats(location_id,date);
        return new ResponseEntity<>(allAvailableBookingsByDateAndLocation,HttpStatus.OK);
    }

    @GetMapping("/bookedOrNotOnDate")
    public ResponseEntity<Integer> isUserBookedSeatOrNotOnDate(@RequestParam("user") Integer user_id, @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        Integer isBookedOrNot = this.bookingServices.isBookedOrNot(user_id, date);
        return new ResponseEntity<>(isBookedOrNot, HttpStatus.OK);
    }
    @PutMapping("/setActiveStatus/{id}/value/{value}")
    public ResponseEntity<String> setBookingActiveStatus(@PathVariable Integer id,@PathVariable boolean value) {
        String response = this.bookingServices.setActiveStatus(id, value);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/available/locationAndDates")
    public ResponseEntity<Map<String, Integer>> seatsAvailableOnDatesAndLocationAndTime(@RequestParam("location") Integer locationId, @RequestParam("startDate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam("endDate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate endDate) throws ParseException, ParseException {
        Map<String, Integer> availableSeats = this.bookingServices.seatsAvailableOnDatesAndLocation(locationId, startDate, endDate);
        return new ResponseEntity<>(availableSeats, HttpStatus.OK);
    }

    @GetMapping("/available/locationDateTime")
    public ResponseEntity<Map<String, Integer>> seatsAvailableByLocationDateTime(@RequestParam("location") Integer locationId, @RequestParam("date") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate date, @RequestParam("fromTime") @DateTimeFormat(pattern = "HH:mm") LocalTime fromTime, @RequestParam("toTime") @DateTimeFormat(pattern = "HH:mm") LocalTime toTime) throws ParseException{
        Map<String, Integer> availableSeats = this.bookingServices.seatsAvailableByLocationDateTime(locationId, date, fromTime, toTime);
        return new ResponseEntity<>(availableSeats, HttpStatus.OK);
    }
    @GetMapping("/dropdown/locationDateTime")
    public ResponseEntity<List<AvailableSeat>> seatsAvailable(@RequestParam("location") Integer locationId, @RequestParam("date") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate date, @RequestParam("fromTime") @DateTimeFormat(iso=DateTimeFormat.ISO.TIME) LocalTime fromTime, @RequestParam("toTime") @DateTimeFormat(iso=DateTimeFormat.ISO.TIME) LocalTime toTime) throws ParseException{
        List<AvailableSeat> availableSeats = this.bookingServices.SeatDropdownLocDateTime(locationId, date, fromTime, toTime);
        return new ResponseEntity<>(availableSeats, HttpStatus.OK);
    }

    @PostMapping("/markAttendance")
    public ResponseEntity<?> markAttendance(@RequestBody String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<AttendanceBody> attendanceBodyList = objectMapper.readValue(json, new TypeReference<List<AttendanceBody>>() {});
            this.bookingServices.updateAttendance(attendanceBodyList);
            return new ResponseEntity<>(new AttendanceBody("INT1437", "Visswateza", "a","a","a"), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing request body");
        }
    }
}