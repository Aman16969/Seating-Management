package com.example.SeatingManagement.Controller;


import com.example.SeatingManagement.Entity.BookingDetail;
import com.example.SeatingManagement.Entity.BookingRequestBody;
import com.example.SeatingManagement.Repository.BookingRepository;
import com.example.SeatingManagement.Services.BookingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingServices bookingServices;
    @PostMapping("/")
    public ResponseEntity<BookingDetail> createBooking(@RequestBody BookingRequestBody bookingBody){
        System.out.println(bookingBody);
        BookingDetail newbooking=this.bookingServices.createBooking(bookingBody);
        return new ResponseEntity<>(newbooking, HttpStatus.CREATED);
    }


}
