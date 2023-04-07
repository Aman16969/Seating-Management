package com.example.SeatingManagement.Controller;

import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.Entity.Seat;
import com.example.SeatingManagement.Entity.SeatRequestBody;
import com.example.SeatingManagement.PayLoad.ApiResponse;
import com.example.SeatingManagement.Services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/seat")
@CrossOrigin
public class SeatController {
    @Autowired
    private SeatService seatService;

    @PostMapping("/")
    public ResponseEntity<Seat> addNewSeat(@RequestBody SeatRequestBody seatRequestBody){
        Seat newSeat = this.seatService.addNewSeat(seatRequestBody);
        return new ResponseEntity<>(newSeat, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Seat>> getAllSeats(){
        List<Seat> allSeats = this.seatService.getAllSeats();
        return new ResponseEntity<>(allSeats, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seat> getSeatById(@PathVariable("id") String id){
        Seat seat = this.seatService.getSeatById(id);
        return new ResponseEntity<>(seat, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteSeat(@PathVariable("id") String id){
        this.seatService.deleteSeat(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Seat deleted successfully", true), HttpStatus.OK);
    }

    @GetMapping("/location/{id}")
    public ResponseEntity<List<Seat>> getSeatsByLocation(@PathVariable("id") Integer id){
        List<Seat> seats = this.seatService.getSeatsByLocation(id);
        return new ResponseEntity<>(seats, HttpStatus.OK);
    }

}
