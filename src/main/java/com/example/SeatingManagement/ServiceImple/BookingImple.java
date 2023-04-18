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
import com.example.SeatingManagement.utils.BookingResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
    public BookingResponse createNewBooking(BookingBody bookingBody) {
        Integer location_id=bookingBody.getLocation_id();
        Integer user_id= Integer.valueOf(bookingBody.getUser_id());
        String seat_id=bookingBody.getSeat_id();
        LocalDate date=bookingBody.getDate();
        User user=this.userRepository.findById(user_id).orElseThrow(()->new ResourceNotFound("User","user_id",""+user_id));
        Location location=this.locationRepository.findById(location_id).orElseThrow(()->new ResourceNotFound("Location","location_id",""+location_id));
        Seat seat=this.seatRepository.findById(seat_id).orElseThrow(()->new ResourceNotFound("Seat","Seat_id",seat_id));
        Booking booking=new Booking(date,seat,user,location);
        if(this.bookingRepository.isSeatBookedOnThatDate(seat, date)==1){
            return new BookingResponse(0, "This seat was already taken.");
        }
        if(this.bookingRepository.isUserBookedOnThatDate(user, date)==1){
            return new BookingResponse(0, "You already made booking for this date.");
        }
        Booking newBooking=this.bookingRepository.save(booking);
        return new BookingResponse(1, "Booking Successful");
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
    public List<BookingDto> getAllBookingByUser(Integer user_id) {
        User user=this.userRepository.findById(user_id).orElseThrow(()->new ResourceNotFound("User","user_id",""+user_id));
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
    public Map<String,String> allAvailableSeats(Integer location_id, LocalDate date) {
        Location location=this.locationRepository.findById(location_id).orElseThrow(()->new ResourceNotFound("Location","Location_id",""+location_id));
        List<Seat> availabeSeats=this.bookingRepository.findAvailableSeatsByLocationAndDate(location,date);
        Map<String,String> availableseat=new HashMap<>();
        for(Seat seat:availabeSeats){
            String seatId=seat.getId();
            String seatName=seat.getName();
            availableseat.put(seatId,seatName);
        }
        return availableseat;
    }

    @Override
    public Integer isBookedOrNot(Integer userId, LocalDate date) {
        User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFound("User","user_id",""+userId));
        Integer isBookedOrNot = this.bookingRepository.isUserBookedOnThatDate(user, date);
        return isBookedOrNot;
    }

    @Override
    public Map<String, Integer> seatsAvailableOnDatesAndLocation(Integer locationId, LocalDate startDate, LocalDate endDate) throws ParseException {
        System.out.println(locationId);
        Location location = this.locationRepository.findById(locationId).orElseThrow(()->new ResourceNotFound("Location", "location_id", String.valueOf(locationId)));
        System.out.println(location.getName());
        List<Seat> seats = this.seatRepository.findSeatsByLocationId(location);
        System.out.println(seats.toArray());
        Map<String, Integer> availableSeats = new HashMap<>();
        for(int i=0; i<seats.size(); i++){
            availableSeats.put(seats.get(i).getId(), 1);
        }
        System.out.println(availableSeats.toString());
        List<String> dates = new ArrayList<>();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate1 = formatter.parse(String.valueOf(startDate)); // today's date
        Calendar calendarBegin = Calendar.getInstance();
        calendarBegin.setTime(startDate1);
        Date endDate1 = formatter.parse(String.valueOf(endDate)); // today's date
        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTime(endDate1);
        calendarEnd.add(Calendar.DATE, 1);
        while (calendarBegin.before(calendarEnd)) {
            // get Date & add to List and increment Date by 1 day
            Date date = calendarBegin.getTime();
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = format1.format(date);
            dates.add(date1);
            calendarBegin.add(Calendar.DATE, 1);
        }
        for(String date :  dates) {
            //Date date1 = formatter.parse(String.valueOf(date));
            System.out.println(date);
            List<Booking> allBookingsByLocationAndDate=this.bookingRepository.findByDateAndLocation(LocalDate.parse(date),location);
            for(int i=0; i<allBookingsByLocationAndDate.size(); i++){
                availableSeats.put(allBookingsByLocationAndDate.get(i).getSeat().getId(), 0);
            }
        }
//        for(; startDate.isBefore(endDate);){
//            System.out.println(startDate.toString());
//            List<Booking> allBookingsByLocationAndDate=this.bookingRepository.findByDateAndLocation(startDate,location);
//            for(int i=0; i<allBookingsByLocationAndDate.size(); i++){
//                availableSeats.put(allBookingsByLocationAndDate.get(i).getSeat(), 0);
//            }
//            startDate.plusDays(1);
//            System.out.println(startDate.toString());
//        }
        return availableSeats;
    }

    @Override
    public void deleteBookingsOfLocation(Integer location_id) {

    }

    @Override
    public Map<String,String> getAllBookingByDateAndLocation(LocalDate date, Integer location_id) {
        Location location=this.locationRepository.findById(location_id).orElseThrow(()->new ResourceNotFound("Location","location_id",""+location_id));
        List<Booking> allBookingsByLocationAndDate=this.bookingRepository.findByDateAndLocation(date,location);
        Map<String,String> bookedSeat=new HashMap<>();
        for(Booking booking:allBookingsByLocationAndDate){
            String seatId=booking.getSeat().getId();
            String seatName=booking.getSeat().getName();
            bookedSeat.put(seatId,seatName);
        }
        return bookedSeat;
    }



}
