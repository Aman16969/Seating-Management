package com.example.SeatingManagement.ServiceImple;


import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.Entity.Room;
import com.example.SeatingManagement.EntityRequestBody.RoomDto;
import com.example.SeatingManagement.ExceptionHandling.ResourceNotFound;
import com.example.SeatingManagement.Repository.LocationRepository;
import com.example.SeatingManagement.Repository.RoomRepository;
import com.example.SeatingManagement.Services.RoomServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomImple implements RoomServices {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private LocationRepository locationRepository;


    @Override
    public RoomDto addnewRoom(RoomDto roomDto) {
        Room room=this.modelMapper.map(roomDto,Room.class);
        Room newRoom=this.roomRepository.save((room));
        Location location = this.locationRepository.findById(newRoom.getLocation().getId()).orElseThrow(()->new ResourceNotFound("Location", "locationId", newRoom.getLocation().getId().toString()));
        if(newRoom.getRoomType().equals("BOARD")){
            location.setBoardRoomCapacity(location.getBoardRoomCapacity()+1);
            this.locationRepository.save(location);
        }
        else{
            location.setDiscussionRoomCapacity(location.getDiscussionRoomCapacity()+1);
            this.locationRepository.save(location);
        }
        return this.modelMapper.map(newRoom,RoomDto.class);
    }

    @Override
    public List<RoomDto> getByLocation(Integer location_id) {
        Location location=this.locationRepository.findById(location_id).orElseThrow(()->new ResourceNotFound("Location","Location Id",""+location_id));
        List<Room> rooms=this.roomRepository.findRoomsByLocationId(location);
        List<RoomDto> roomDtos=rooms.stream().map((e)->this.modelMapper.map(e,RoomDto.class)).collect(Collectors.toList());
        return roomDtos;
    }


}
