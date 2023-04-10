package com.example.SeatingManagement.Controller;

import com.example.SeatingManagement.EntityRequestBody.SeatDto;
import com.example.SeatingManagement.PayLoad.ApiResponse;
import com.example.SeatingManagement.Services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/seat")
@CrossOrigin
public class SeatController {
    @Autowired
    private SeatService seatService;

    @PostMapping("/")
    public ResponseEntity<SeatDto> addNewSeat( @Valid @RequestBody SeatDto seatDto){
        SeatDto newSeat = this.seatService.addNewSeat(seatDto);
        return new ResponseEntity<>(newSeat, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<SeatDto>> getAllSeat(){
        List<SeatDto> allSeats = this.seatService.getAllSeats();
        return new ResponseEntity<>(allSeats, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatDto> getSeatById(@PathVariable("id") String id){
        SeatDto seatDto = this.seatService.getSeatById(id);
        return new ResponseEntity<>(seatDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteSeat(@PathVariable("id") String id){
        this.seatService.deleteSeatById(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Seat deleted successfully", true), HttpStatus.OK);
    }

    @GetMapping("/location/{id}")
    public ResponseEntity<List<SeatDto>> getSeatsByLocation(@PathVariable("id") Integer id){
        List<SeatDto> seats = this.seatService.getSeatsByLocation(id);
        return new ResponseEntity<>(seats, HttpStatus.OK);
    }

}
