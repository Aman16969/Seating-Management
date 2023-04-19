package com.example.SeatingManagement.ServiceImple;

import com.example.SeatingManagement.Entity.Booking;
import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.Entity.Seat;
import com.example.SeatingManagement.Entity.User;
import com.example.SeatingManagement.EntityRequestBody.BookingDto;
import com.example.SeatingManagement.EntityRequestBody.SeatDto;
import com.example.SeatingManagement.ExceptionHandling.ResourceNotFound;
import com.example.SeatingManagement.Repository.BookingRepository;
import com.example.SeatingManagement.Repository.LocationRepository;
import com.example.SeatingManagement.Repository.SeatRepository;
import com.example.SeatingManagement.Repository.UserRepository;
import com.example.SeatingManagement.Services.BookingServices;
import com.example.SeatingManagement.utils.BookingBody;
import com.example.SeatingManagement.utils.BookingResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookingImple implements BookingServices {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BookingResponse addNewBooking(BookingBody bookingBody) {
        Integer location_id=bookingBody.getLocation_id();
        Integer user_id= Integer.valueOf(bookingBody.getUser_id());
        String seat_id=bookingBody.getSeat_id();
        User user=this.userRepository.findById(user_id).orElseThrow(()->new ResourceNotFound("User","user_id",""+user_id));
        Location location=this.locationRepository.findById(location_id).orElseThrow(()->new ResourceNotFound("Location","location_id",""+location_id));
        Seat seat=this.seatRepository.findById(seat_id).orElseThrow(()->new ResourceNotFound("Seat","Seat_id",seat_id));
        Booking booking=new Booking();
        booking.setSeat(seat);
        booking.setLocation(location);
        booking.setUser(user);
        booking.setFromDate(bookingBody.getFromDate());
        booking.setToDate(bookingBody.getToDate());
        booking.setFromTime(bookingBody.getFromTime());
        booking.setActive(true);
        booking.setToTime(bookingBody.getToTime());
        Booking newBooking=this.bookingRepository.save(booking);
        return new BookingResponse(1, "Booking Successful");
    }

    @Override
    public List<BookingDto> getAllBookings(){
        List<Booking> allBookings = this.bookingRepository.findAll();
        List<BookingDto> allBookingsDto = allBookings.stream().map((e)->this.modelMapper.map(e,BookingDto.class)).collect(Collectors.toList());
        return allBookingsDto;
    }

    @Override
    public List<SeatDto> findAvailableSeats(Integer locationID, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        Location location = this.locationRepository.findById(locationID).orElseThrow(()->new ResourceNotFound("Location", "location_id", ""+locationID));
        List<Seat> seats = this.bookingRepository.findAvailableSeats(location, startDate, endDate, startTime, endTime);
        List<SeatDto> availableSeats = seats.stream().map((e)->this.modelMapper.map(e,SeatDto.class)).collect(Collectors.toList());
        return availableSeats;
    }

    @Override
    public List<BookingDto> getAllBookingsByLocation(Integer locationId) {
        Location location = this.locationRepository.findById(locationId).orElseThrow(()->new ResourceNotFound("Location", "location_id", ""+locationId));
        List<Booking> bookings = this.bookingRepository.findByLocation(location);
        List<BookingDto> bookingsDto = bookings.stream().map((e)->this.modelMapper.map(e,BookingDto.class)).collect(Collectors.toList());
        return bookingsDto;
    }

//    @Override
//    public List<BookingDto> getAllBookingsByDate(LocalDate date) {
//        List<Booking> bookings = this.bookingRepository.findByDate(date);
//        List<BookingDto> bookingsDto = bookings.stream().map((e)->this.modelMapper.map(e,BookingDto.class)).collect(Collectors.toList());
//        return bookingsDto;
//    }

    @Override
    public List<BookingDto> getAllBookingsByUser(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFound("User", "user_id", ""+userId));
        List<Booking> bookings = this.bookingRepository.findByUser(user);
        List<BookingDto> bookingsDto = bookings.stream().map((e)->this.modelMapper.map(e,BookingDto.class)).collect(Collectors.toList());
        return bookingsDto;
    }

//    @Override
//    public List<BookingDto> getAllBookingsByDateAndLocation(LocalDate date, Integer locationId) {
//        Location location = this.locationRepository.findById(locationId).orElseThrow(()->new ResourceNotFound("Location", "location_id", ""+locationId));
//        List<Booking> bookings = this.bookingRepository.findByDateAndLocation(date, location);
//        List<BookingDto> bookingsDto = bookings.stream().map((e)->this.modelMapper.map(e,BookingDto.class)).collect(Collectors.toList());
//        return bookingsDto;
//    }

//    @Override
//    public List<BookingDto> getAllBookingsByDateAndUser(LocalDate date, Integer userId) {
//        User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFound("User", "user_id", ""+userId));
//        List<Booking> bookings = this.bookingRepository.findByDateAndUser(date, user);
//        List<BookingDto> bookingsDto = bookings.stream().map((e)->this.modelMapper.map(e,BookingDto.class)).collect(Collectors.toList());
//        return bookingsDto;
//    }
}
