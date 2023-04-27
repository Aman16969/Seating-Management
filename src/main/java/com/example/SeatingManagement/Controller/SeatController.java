package com.example.SeatingManagement.Controller;

import com.example.SeatingManagement.Entity.Seat;
import com.example.SeatingManagement.EntityRequestBody.SeatDto;
import com.example.SeatingManagement.PayLoad.ApiResponse;
import com.example.SeatingManagement.Services.SeatService;
import com.example.SeatingManagement.utils.SeatBody;
import com.example.SeatingManagement.utils.SeatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/seat")
@CrossOrigin (origins= "*", allowedHeaders = "*")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @PostMapping("/")
    public ResponseEntity<SeatDto> addNewSeat(@Valid @RequestBody SeatBody seatBody) {
        SeatDto newSeat = this.seatService.addNewSeat(seatBody);
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

//    @DeleteMapping("/{id}")
//    public ResponseEntity<ApiResponse> deleteSeat(@PathVariable("id") String id){
//        this.seatService.deleteSeatById(id);
//        return new ResponseEntity<ApiResponse>(new ApiResponse("Seat deleted successfully", true), HttpStatus.OK);
//    }

    @GetMapping("/position")
    public ResponseEntity<SeatResponse> getSetByPosition(@RequestParam("location") Integer locationId, @RequestParam("row") Integer row, @RequestParam("column") Integer column){
        SeatResponse seatResponse = this.seatService.getSeatByPosition(locationId, row, column);
        return new ResponseEntity<>(seatResponse, HttpStatus.OK);
    }

    @PutMapping("/changeDirection/{seatId}")
    public ResponseEntity<SeatResponse> changeSeatDirection(@PathVariable("seatId") String seatId){
        SeatResponse seatResponse = this.seatService.changeSeatDirection(seatId);
        return new ResponseEntity<>(seatResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}/{value}")
    public ResponseEntity<SeatResponse> deleteSeat(@PathVariable("id") String id, @PathVariable("value") String value){
        SeatResponse seat = this.seatService.deleteSeatById(id, value);
        return new ResponseEntity<>(seat, HttpStatus.OK);
    }

    @GetMapping("/location/{id}")
    public ResponseEntity<List<Seat>> getSeatsByLocation(@PathVariable("id") Integer id){
        List<Seat> seats = this.seatService.getSeatsByLocation(id);
        return new ResponseEntity<>(seats, HttpStatus.OK);
    }
}
