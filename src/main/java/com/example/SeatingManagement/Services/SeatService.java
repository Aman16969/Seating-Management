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
    public Seat addNewSeat(SeatRequestBody seatRequestBody) {
        Integer locationId = seatRequestBody.getLocation_id();
        String seatId = seatRequestBody.getId();
        Location location = this.locationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFound("Location", "location_id", seatId));
        Seat seat = new Seat(seatId, location);
        Seat newSeat = this.seatRepository.save(seat);
        return newSeat;
    }

    //Get all seats
    public List<Seat> getAllSeats() {
        List<Seat> allSeats = this.seatRepository.findAll();
        return allSeats;
    }

    //Get specific seat by ID
    public Seat getSeatById(String id) {
        Seat seat = this.seatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Seat", "seat_id", id));
        return seat;
    }

    //Delete a seat by id
    public void deleteSeat(String id) {
        Seat seat = this.seatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Seat", "seat_id", id));
        this.seatRepository.delete(seat);
    }

    //Get seats by location
    public List<Seat> getSeatsByLocation(Integer id) {
        Location location = this.locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Location", "location_id", String.valueOf(id)));
        List<Seat> seats = (List<Seat>) this.seatRepository.findSeatsByLocationId(location);
        return seats;
    }

    public void generateSeat(Location location) {
        Integer capacity = location.getSeatingCapacity();
        String keyCode = location.getName().substring(0, 1).toUpperCase();
        Integer loc_id = location.getId();
        for (int i = 1; i <= capacity; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("" + loc_id).append(keyCode).append("" + i);
            String id = sb.toString();
            Seat seat = new Seat(id, location);
            this.seatRepository.save(seat);
        }
    }

    public void updateSeatWhenUpdateLocation(Integer currentCapacity, Integer updatedCapacity, Location location) {
        String keyCode = location.getName().substring(0, 1).toUpperCase();
        Integer loc_id = location.getId();
        System.out.println(currentCapacity);
        System.out.println(updatedCapacity);
        if (updatedCapacity>currentCapacity) {
            for (Integer i = currentCapacity+1; i <= updatedCapacity; i++) {
//                System.out.println(i);
                StringBuilder sb = new StringBuilder();
                sb.append("" + loc_id).append(keyCode).append("" + i);
                String id = sb.toString();
                Seat seat = new Seat(id, location);
                this.seatRepository.save(seat);
            }}

    }
}