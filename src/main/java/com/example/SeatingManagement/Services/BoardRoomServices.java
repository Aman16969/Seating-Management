package com.example.SeatingManagement.Services;


import com.example.SeatingManagement.EntityRequestBody.BoardRoomDto;
import com.example.SeatingManagement.EntityRequestBody.LocationDto;
import org.springframework.stereotype.Service;

@Service
public interface BoardRoomServices {
    void autoGenerateBoardRoom(LocationDto locationDto);
    void autoUpdateExistingBoardRoom(Integer currentBoardCapacity,Integer newBoardCapacity,LocationDto locationDto);
    BoardRoomDto addnewBoardRoom(BoardRoomDto boardRoomDto);

}
