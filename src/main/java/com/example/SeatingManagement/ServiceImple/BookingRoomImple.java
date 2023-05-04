package com.example.SeatingManagement.ServiceImple;

import com.example.SeatingManagement.Entity.*;
import com.example.SeatingManagement.EntityRequestBody.BookingRoomDto;
import com.example.SeatingManagement.EntityRequestBody.RoomDto;
import com.example.SeatingManagement.ExceptionHandling.ResourceNotFound;
import com.example.SeatingManagement.Repository.*;
import com.example.SeatingManagement.Services.BookingRoomServices;
import com.example.SeatingManagement.utils.BookingResponse;
import com.example.SeatingManagement.utils.BookingRoomBody;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class BookingRoomImple implements BookingRoomServices {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BookingRoomRepository bookingRoomRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private RoomRepository boardRoomRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public BookingRoom createNewBooking(BookingRoomBody bookingRoomBody) {

        User admin=this.userRepository.findByEmail(bookingRoomBody.getAdminEmail()).orElseThrow(()->new ResourceNotFound("user","userEmail",bookingRoomBody.getAdminEmail()));
        User user=this.userRepository.findByEmail(bookingRoomBody.getUserEmail()).orElseThrow(()->new ResourceNotFound("user","userEmail",bookingRoomBody.getUserEmail()));
        Location location=this.locationRepository.findById(bookingRoomBody.getLocation_id()).orElseThrow(() -> new ResourceNotFound("Location", "location_id", ""+bookingRoomBody.getLocation_id()));
        Room room=this.boardRoomRepository.findById(bookingRoomBody.getRoom_id()).orElseThrow(() -> new ResourceNotFound("Room", "Room_id", ""+bookingRoomBody.getRoom_id()));
        BookingRoom bookingRoom=new BookingRoom();
        bookingRoom.setUser(user);
        bookingRoom.setAdmin(admin);
        bookingRoom.setDate(bookingRoomBody.getDate());
        bookingRoom.setFromTime(bookingRoomBody.getFromTime());
        bookingRoom.setToTime(bookingRoomBody.getToTime());
        bookingRoom.setLocation(location);
        bookingRoom.setRoom(room);
        bookingRoom.setRoomType(bookingRoomBody.getRoomType());
        bookingRoom.setActive(true);

        BookingRoom newBooking=this.bookingRoomRepository.save(bookingRoom);
        return newBooking;

    }
    @Override
    public String setActiveStatus(Integer id, boolean value) {
        BookingRoom bookingRoom=this.bookingRoomRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Booking", "booking_id", ""+id));
        bookingRoom.setActive(value);
        this.bookingRoomRepository.save(bookingRoom);
        return bookingRoom.getId()+ "'s active status is changed to "+value;
    }

    @Override
    public List<BookingRoom> getBookingsByAdmin(String email) {
        User admin=this.userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFound("user","userEmail",email));
        List<BookingRoom> bookingRooms=this.bookingRoomRepository.findAllByAdmin(admin);
//        List<BookingRoomDto> bookingRoomDtos=bookingRooms.stream().map((e)->this.modelMapper.map(e,BookingRoomDto.class)).collect(Collectors.toList());
        return bookingRooms;
    }

    @Override
    public List<BookingRoom> getBookingByUser(String email) {
        User user=this.userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFound("user","userEmail",email));
        List<BookingRoom> bookingRooms=this.bookingRoomRepository.findAllByUserAndIsActive(user,true);
//        List<BookingRoomDto> bookingRoomDtos=bookingRooms.stream().map((e)->this.modelMapper.map(e,BookingRoomDto.class)).collect(Collectors.toList());
        return bookingRooms;
    }

    @Override
    public List<RoomDto> roomsAvailableByLocationDateTime(Integer locationId, LocalDate date, LocalTime fromTime, LocalTime toTime) {
        Location location=this.locationRepository.findById(locationId).orElseThrow(()-> new ResourceNotFound("Location","id",""+locationId));
        List<Room> rooms=this.bookingRoomRepository.findAvailableRoomsByLocationDateTime(location, date, fromTime, toTime);
        List<RoomDto> availableRooms=rooms.stream().map((e)->this.modelMapper.map(e,RoomDto.class)).collect(Collectors.toList());
        return availableRooms;
    }
}
