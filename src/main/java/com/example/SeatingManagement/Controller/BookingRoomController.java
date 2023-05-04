package com.example.SeatingManagement.Controller;


import com.example.SeatingManagement.Entity.BookingRoom;
import com.example.SeatingManagement.EntityRequestBody.BookingDto;
import com.example.SeatingManagement.EntityRequestBody.BookingRoomDto;
import com.example.SeatingManagement.EntityRequestBody.RoomDto;
import com.example.SeatingManagement.Services.BookingRoomServices;
import com.example.SeatingManagement.utils.BookingRoomBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("api/bookRoom")
@CrossOrigin (origins= "*", allowedHeaders = "*")
public class BookingRoomController {
    @Autowired
    private BookingRoomServices bookingRoomServices;

    @GetMapping("/user/{email}")
    public ResponseEntity<List<BookingRoom>> bookingByUser(@PathVariable String email) {
        List<BookingRoom> bookings = this.bookingRoomServices.getBookingByUser(email);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping("/admin/{email}")
    public ResponseEntity<List<BookingRoom>> bookingByAdmin(@PathVariable String email) {
        List<BookingRoom> bookings = this.bookingRoomServices.getBookingsByAdmin(email);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @PutMapping("/setActiveStatus/{id}/{value}")
    public ResponseEntity<String> setBookingRoomActiveStatus(@PathVariable Integer id, @PathVariable boolean value) {
        String response = this.bookingRoomServices.setActiveStatus(id, value);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<BookingRoom> createNewBooking(@RequestBody BookingRoomBody bookingRoomBody) {
        System.out.println(bookingRoomBody);
        BookingRoom bookingRoom = this.bookingRoomServices.createNewBooking(bookingRoomBody);
        return new ResponseEntity<>(bookingRoom, HttpStatus.CREATED);
    }

    @GetMapping("/available/locationDateTime")
    public ResponseEntity<List<RoomDto>> roomsAvailableByLocationDateTime(@RequestParam("location") Integer locationId, @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                                          @RequestParam("fromTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime fromTime,
                                                                          @RequestParam("toTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime toTime){
        List<RoomDto> availableRooms = this.bookingRoomServices.roomsAvailableByLocationDateTime(locationId, date, fromTime, toTime);
        return new ResponseEntity<>(availableRooms, HttpStatus.OK);
    }
}