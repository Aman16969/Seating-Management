package com.example.SeatingManagement.Controller;

import com.example.SeatingManagement.Entity.RequestSeat;
import com.example.SeatingManagement.Services.RequestSeatService;
import com.example.SeatingManagement.utils.SeatRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/request/seat/")
@CrossOrigin (origins= "*", allowedHeaders = "*")

public class RequestSeatController {
    @Autowired
    private RequestSeatService requestSeatService;

    @PostMapping("/")
    public ResponseEntity<RequestSeat> addNewRequest(@RequestBody SeatRequest seatRequest){
        RequestSeat newRequestSeat = this.requestSeatService.createNewSeatRequest(seatRequest);
        return new ResponseEntity<>(newRequestSeat, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<RequestSeat> updateRequest(@RequestBody RequestSeat requestSeat){
        RequestSeat updateRequestSeat = this.requestSeatService.updateSeatRequest(requestSeat);
        return new ResponseEntity<>(updateRequestSeat, HttpStatus.OK);
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<RequestSeat> cancelRequest(@PathVariable("id") Integer id){
        RequestSeat updateRequestSeat = this.requestSeatService.cancelSeatRequest(id);
        return new ResponseEntity<>(updateRequestSeat, HttpStatus.OK);
    }
    @PutMapping("/accept/{id}")
    public ResponseEntity<RequestSeat> acceptRequest(@PathVariable("id") Integer id){
        RequestSeat updateRequestSeat = this.requestSeatService.acceptSeatRequest(id);
        return new ResponseEntity<>(updateRequestSeat, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<RequestSeat> getRequestSeat(@PathVariable("id") Integer id){
        RequestSeat requestSeat = this.requestSeatService.getRequestById(id);
        return new ResponseEntity<>(requestSeat, HttpStatus.OK);
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<RequestSeat>> getRequestsByLocation(@PathVariable("location") Integer locationId){
        List<RequestSeat> requestSeats = this.requestSeatService.getAllLocationRequests(locationId);
        return new ResponseEntity<>(requestSeats, HttpStatus.OK);
    }

    @GetMapping("/user/{user}")
    public ResponseEntity<List<RequestSeat>> getRequestsByUser(@PathVariable("user") Integer userId){
        List<RequestSeat> requestSeats = this.requestSeatService.getAllUserRequests(userId);
        return new ResponseEntity<>(requestSeats, HttpStatus.OK);
    }


}
