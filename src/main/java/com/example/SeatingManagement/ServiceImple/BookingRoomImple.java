package com.example.SeatingManagement.ServiceImple;

import com.example.SeatingManagement.Entity.*;
import com.example.SeatingManagement.EntityRequestBody.BookingRoomDto;
import com.example.SeatingManagement.ExceptionHandling.ResourceNotFound;
import com.example.SeatingManagement.Repository.*;
import com.example.SeatingManagement.Services.BookingRoomServices;
import com.example.SeatingManagement.utils.BookingResponse;
import com.example.SeatingManagement.utils.BookingRoomBody;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private BoardRoomRepository boardRoomRepository;
    @Autowired
    private DisscussionRoomRepository disscussionRoomRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public BookingRoomDto createNewBooking(BookingRoomBody bookingRoomBody) {
        User admin=this.userRepository.findByEmail(bookingRoomBody.getAdminEmail()).orElseThrow(()->new ResourceNotFound("user","userEmail",bookingRoomBody.getAdminEmail()));
        User user=this.userRepository.findByEmail(bookingRoomBody.getUserEmail()).orElseThrow(()->new ResourceNotFound("user","userEmail",bookingRoomBody.getUserEmail()));
        Location location=this.locationRepository.findById(bookingRoomBody.getLocation_id()).orElseThrow(() -> new ResourceNotFound("Location", "location_id", ""+bookingRoomBody.getLocation_id()));
        BoardRoom boardRoom=this.boardRoomRepository.findById(bookingRoomBody.getBoardRoom_id()).orElseThrow(() -> new ResourceNotFound("BoardRoom", "BoardRoom_id", ""+bookingRoomBody.getBoardRoom_id()));
        DisscussionRoom disscussionRoom =this.disscussionRoomRepository.findById(bookingRoomBody.getDisscussionRoom_id()).orElseThrow(() -> new ResourceNotFound("BoardRoom", "BoardRoom_id", ""+bookingRoomBody.getDisscussionRoom_id()));
        BookingRoom bookingRoom=new BookingRoom();
        bookingRoom.setBoardRoom(boardRoom);
        bookingRoom.setDisscussionRoom(disscussionRoom);
        bookingRoom.setUser(user);
        bookingRoom.setAdmin(admin);
        bookingRoom.setDate(bookingRoomBody.getDate());
        bookingRoom.setFromTime(bookingRoomBody.getFromTime());
        bookingRoom.setToTime(bookingRoomBody.getToTime());
        bookingRoom.setActive(true);
        BookingRoom newBooking=this.bookingRoomRepository.save(bookingRoom);
        return this.modelMapper.map(newBooking,BookingRoomDto.class);

    }
    @Override
    public String setActiveStatus(Integer id, boolean value) {
        BookingRoom bookingRoom=this.bookingRoomRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Booking", "booking_id", ""+id));
        bookingRoom.setActive(value);
        this.bookingRoomRepository.save(bookingRoom);
        return bookingRoom.getId()+ "'s active status is changed to "+value;
    }

    @Override
    public List<BookingRoomDto> getBookingsByAdmin(String email) {
        User admin=this.userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFound("user","userEmail",email));
        List<BookingRoom> bookingRooms=this.bookingRoomRepository.findAllByAdmin(admin);
        List<BookingRoomDto> bookingRoomDtos=bookingRooms.stream().map((e)->this.modelMapper.map(e,BookingRoomDto.class)).collect(Collectors.toList());
        return bookingRoomDtos;
    }

    @Override
    public List<BookingRoomDto> getBookingByUser(String email) {
        User user=this.userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFound("user","userEmail",email));
        List<BookingRoom> bookingRooms=this.bookingRoomRepository.findAllByUserAndIsActive(user,true);
        List<BookingRoomDto> bookingRoomDtos=bookingRooms.stream().map((e)->this.modelMapper.map(e,BookingRoomDto.class)).collect(Collectors.toList());
        return bookingRoomDtos;
    }
}
