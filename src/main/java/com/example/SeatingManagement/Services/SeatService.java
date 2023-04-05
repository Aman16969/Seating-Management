package com.example.SeatingManagement.Services;

import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.Entity.Seat;
import com.example.SeatingManagement.Entity.SeatRequestBody;
import com.example.SeatingManagement.ExceptionHandling.ResourceNotFound;
import com.example.SeatingManagement.Repository.LocationRepository;
import com.example.SeatingManagement.Repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private LocationRepository locationRepository;

    //Add new seat
    public Seat addNewSeat(SeatRequestBody seatRequestBody){
        Integer locationId = seatRequestBody.getLocation_id();
        String seatId = seatRequestBody.getId();
        Location location=this.locationRepository.findById(locationId)
                .orElseThrow(()-> new ResourceNotFound("Location", "location_id", seatId));
        Seat seat=new Seat(seatId,location);
        Seat newSeat = this.seatRepository.save(seat);
        return newSeat;
    }

    //Get all seats
    public List<Seat> getAllSeats(){
        List<Seat> allSeats = this.seatRepository.findAll();
        return  allSeats;
    }

    //Get specific seat by ID
    public Seat getSeatById(String id){
        Seat seat = this.seatRepository.findById(id)
                .orElseThrow(()->new ResourceNotFound("Seat", "seat_id", id));
        return seat;
    }

    //Delete a seat by id
    public void deleteSeat(String id){
        Seat seat = this.seatRepository.findById(id)
                .orElseThrow(()->new ResourceNotFound("Seat", "seat_id", id));
        this.seatRepository.delete(seat);
    }

    //Get seats by location
    public List<Seat> getSeatsByLocation(Integer id){
        Location location=this.locationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFound("Location", "location_id", String.valueOf(id)));
        List<Seat> seats = (List<Seat>) this.seatRepository.findSeatsByLocationId(location);
        return seats;
    }
}