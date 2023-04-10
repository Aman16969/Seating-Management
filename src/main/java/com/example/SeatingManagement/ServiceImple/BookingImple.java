package com.example.SeatingManagement.ServiceImple;

import com.example.SeatingManagement.Entity.Booking;
import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.Entity.Seat;
import com.example.SeatingManagement.Entity.User;
import com.example.SeatingManagement.EntityRequestBody.BookingDto;
import com.example.SeatingManagement.ExceptionHandling.ResourceNotFound;
import com.example.SeatingManagement.Repository.BookingRepository;
import com.example.SeatingManagement.Repository.LocationRepository;
import com.example.SeatingManagement.Repository.SeatRepository;
import com.example.SeatingManagement.Repository.UserRepository;
import com.example.SeatingManagement.Services.BookingServices;
import com.example.SeatingManagement.utils.BookingBody;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
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
    public BookingDto createNewBooking(BookingBody bookingBody) {
        Integer location_id=bookingBody.getLocation_id();
        String user_id=bookingBody.getUser_id();
        String seat_id=bookingBody.getSeat_id();
        LocalDate date=bookingBody.getDate();
        User user=this.userRepository.findById(user_id).orElseThrow(()->new ResourceNotFound("User","user_id",user_id));
        Location location=this.locationRepository.findById(location_id).orElseThrow(()->new ResourceNotFound("Location","location_id",""+location_id));
        Seat seat=this.seatRepository.findById(seat_id).orElseThrow(()->new ResourceNotFound("Seat","Seat_id",seat_id));
        Booking booking=new Booking(date,seat,user,location);
        Booking newBooking=this.bookingRepository.save(booking);
        return this.modelMapper.map(newBooking,BookingDto.class);

    }

    @Override
    public BookingDto updateExistingBooking(BookingBody bookingBody) {
        return null;
    }

    @Override
    public void deleteBookingOfUserById(Integer id) {
        Booking booking=this.bookingRepository.findById(id).orElseThrow(()->new ResourceNotFound("Booking","booking_id",""+id));
        this.bookingRepository.delete(booking);

    }

    @Override
    public List<BookingDto> getAllBooking() {
       List<Booking> allBookings=this.bookingRepository.findAll();
       List<BookingDto> allBookingDto=allBookings.stream().map((e)->this.modelMapper.map(e,BookingDto.class)).collect(Collectors.toList());
       return allBookingDto;
    }

    @Override
    public List<BookingDto> getAllBookingByUser(String user_id) {
        User user=this.userRepository.findById(user_id).orElseThrow(()->new ResourceNotFound("User","user_id",user_id));
        List<Booking> allBookingsByUser=this.bookingRepository.findByUser(user);
        List<BookingDto> allBookingDto=allBookingsByUser.stream().map((e)->this.modelMapper.map(e,BookingDto.class)).collect(Collectors.toList());
        return allBookingDto;
    }

    @Override
    public List<BookingDto> getAllBookingByDate(LocalDate date) {
        List<Booking> allBookingsByDate=this.bookingRepository.findByDate(date);
        List<BookingDto> allBookingDto=allBookingsByDate.stream().map((e)->this.modelMapper.map(e,BookingDto.class)).collect(Collectors.toList());
        return allBookingDto;
    }
    @Override
    public List<BookingDto> getAllBookingByLocation(Integer location_id) {
        Location location=this.locationRepository.findById(location_id).orElseThrow(()->new ResourceNotFound("Location","location_id",""+location_id));
        List<Booking> allBookingsByLocation=this.bookingRepository.findByLocation(location);
        List<BookingDto> allBookingDto=allBookingsByLocation.stream().map((e)->this.modelMapper.map(e,BookingDto.class)).collect(Collectors.toList());
        return allBookingDto;
    }
    @Override
    public List<BookingDto> getAllBookingByDateAndLocation(LocalDate date, Integer location_id) {
        Location location=this.locationRepository.findById(location_id).orElseThrow(()->new ResourceNotFound("Location","location_id",""+location_id));
        List<Booking> allBookingsByLocationAndDate=this.bookingRepository.findByDateAndLocation(date,location);
        List<BookingDto> allBookingDto=allBookingsByLocationAndDate.stream().map((e)->this.modelMapper.map(e,BookingDto.class)).collect(Collectors.toList());
        return allBookingDto;
    }


}
