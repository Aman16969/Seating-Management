package com.example.SeatingManagement.Controller;


import com.example.SeatingManagement.EntityRequestBody.BookingDto;
import com.example.SeatingManagement.EntityRequestBody.BookingRoomDto;
import com.example.SeatingManagement.Services.BookingRoomServices;
import com.example.SeatingManagement.utils.BookingRoomBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/bookRoom")
public class BookingRoomController {
    @Autowired
    private BookingRoomServices bookingRoomServices;
    @GetMapping("/user/{email}")
    public ResponseEntity<List<BookingRoomDto>> bookingByUser(@PathVariable String email){
        List<BookingRoomDto> bookings = this.bookingRoomServices.getBookingByUser(email);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }
    @GetMapping("/admin/{email}")
    public ResponseEntity<List<BookingRoomDto>> bookingByAdmin(@PathVariable String email){
        List<BookingRoomDto> bookings = this.bookingRoomServices.getBookingsByAdmin(email);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }
    @PutMapping("/setActiveStatus/{id}/{value}")
    public ResponseEntity<String> setBookingRoomActiveStatus(@PathVariable Integer id, @PathVariable boolean value) {
        String response = this.bookingRoomServices.setActiveStatus(id, value);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<BookingRoomDto> createNewBooking(@RequestBody BookingRoomBody bookingRoomBody){
        BookingRoomDto bookingRoomDto=this.bookingRoomServices.createNewBooking(bookingRoomBody);
        return new ResponseEntity<>(bookingRoomDto,HttpStatus.CREATED);
    }
}
