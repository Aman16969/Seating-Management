package com.example.SeatingManagement.Services;

import com.example.SeatingManagement.Entity.*;
import com.example.SeatingManagement.ExceptionHandling.ResourceNotFound;
import com.example.SeatingManagement.Repository.BookingRepository;
import com.example.SeatingManagement.Repository.LocationRepository;
import com.example.SeatingManagement.Repository.SeatRepository;
import com.example.SeatingManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BookingServices {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private SeatRepository seatRepository;


    public BookingDetail createBooking(BookingRequestBody book){

        User user=this.userRepository.findById(book.getUser_id()).orElseThrow(()->new ResourceNotFound("User","user_id",book.getUser_id()));
        Location location=this.locationRepository.findById(book.getLocation_id()).orElseThrow(()->new ResourceNotFound("Location","location_id",""+book.getLocation_id()));
        Seat seat=this.seatRepository.findById(book.getSeat_id()).orElseThrow(()->new ResourceNotFound("Seat","Seat_id",book.getSeat_id()));
        Date date=book.getDate();
        System.out.println(date);
        BookingDetail bookingDetail=new BookingDetail(date,seat,user,location);
        BookingDetail booking=this.bookingRepository.save(bookingDetail);
        return booking;
    }
}
