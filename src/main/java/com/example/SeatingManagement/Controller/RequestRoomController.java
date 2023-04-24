package com.example.SeatingManagement.Controller;


import com.example.SeatingManagement.Entity.RequestBookingRoom;
import com.example.SeatingManagement.EntityRequestBody.RequestBookingRoomDto;
import com.example.SeatingManagement.EntityRequestBody.UserDto;
import com.example.SeatingManagement.Services.RequestBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/requestBooking")
@CrossOrigin
public class RequestRoomController {
    @Autowired
    private RequestBookingService requestBookingService;

   @PostMapping("/")
    public ResponseEntity<RequestBookingRoomDto> createRequest(@RequestBody RequestBookingRoomDto requestBookingRoomDto) {
       RequestBookingRoomDto request = this.requestBookingService.createNewRequest(requestBookingRoomDto);
       return new ResponseEntity<>(request, HttpStatus.CREATED);
   }
   @PutMapping("/request/{request_id}/value/{value}")
   public ResponseEntity<String> setActiveStatus(@PathVariable Integer request_id,@PathVariable boolean value) {
       String request = this.requestBookingService.setActiveStatus(request_id,value);
       return new ResponseEntity<>(request, HttpStatus.OK);
   }
    @PutMapping("/request/{request_id}/book/{value}")
    public ResponseEntity<String> setAcceptedStatus(@PathVariable Integer request_id,@PathVariable boolean value) {
        String request = this.requestBookingService.setAccepted(request_id,value);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<RequestBookingRoomDto>> allRequest() {
        List<RequestBookingRoomDto> requests = this.requestBookingService.allRequest();
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RequestBookingRoomDto> getRequestById(@PathVariable Integer id) {
        RequestBookingRoomDto request = this.requestBookingService.getAllRequestById(id);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }
    @GetMapping("/byUser/{email}")
    public ResponseEntity<List<RequestBookingRoomDto>> allRequestByUser(@PathVariable String email) {
        List<RequestBookingRoomDto> requests = this.requestBookingService.allRequestByUser(email);
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

}
