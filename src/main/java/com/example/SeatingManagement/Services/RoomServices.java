package com.example.SeatingManagement.Services;

import com.example.SeatingManagement.EntityRequestBody.LocationDto;
import com.example.SeatingManagement.EntityRequestBody.RoomDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomServices {
    RoomDto addnewRoom(RoomDto roomDto);
    List<RoomDto> getByLocation(Integer location_id);

}
