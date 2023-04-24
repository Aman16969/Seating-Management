package com.example.SeatingManagement.Controller;


import com.example.SeatingManagement.Entity.Room;
import com.example.SeatingManagement.EntityRequestBody.RoomDto;
import com.example.SeatingManagement.EntityRequestBody.SeatDto;
import com.example.SeatingManagement.Services.RoomServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/room")
@CrossOrigin
public class RoomController {
    @Autowired
    private RoomServices roomServices;

    @PostMapping("/")
    public ResponseEntity<RoomDto> addNewRoom(@RequestBody RoomDto roomDto){
        RoomDto room = this.roomServices.addnewRoom(roomDto);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }
    @GetMapping("/location/{id}")
    public ResponseEntity<List<RoomDto>> GetRoomByLocation(@PathVariable Integer id){
        List<RoomDto> rooms = this.roomServices.getByLocation(id);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }
}
