package com.example.SeatingManagement.ServiceImple;

import com.example.SeatingManagement.Entity.BoardRoom;
import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.EntityRequestBody.BoardRoomDto;
import com.example.SeatingManagement.EntityRequestBody.LocationDto;
import com.example.SeatingManagement.EntityRequestBody.SeatDto;
import com.example.SeatingManagement.Repository.BoardRoomRepository;
import com.example.SeatingManagement.Services.BoardRoomServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardRoomImple implements BoardRoomServices {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BoardRoomRepository boardRoomRepository;
    @Override
    public BoardRoomDto addnewBoardRoom(BoardRoomDto boardRoomDto) {
        BoardRoom newBoardRoom=this.modelMapper.map(boardRoomDto,BoardRoom.class);
        BoardRoom boardRoom=this.boardRoomRepository.save(newBoardRoom);
        return this.modelMapper.map(boardRoom,BoardRoomDto.class);
    }
    @Override
    public void autoGenerateBoardRoom(LocationDto locationDto) {
        Integer boardRoomCapacity=locationDto.getBoardRoomCapacity();
        Location location=this.modelMapper.map(locationDto,Location.class);
        Integer locIdLey=locationDto.getId();
        String locNameKey=locationDto.getName().substring(0,1).toUpperCase();
        for(int i=1;i<=boardRoomCapacity;i++){
            StringBuilder geneateId=new StringBuilder();
            StringBuilder genrateName=new StringBuilder();
            geneateId.append(""+locIdLey).append(locNameKey).append(""+i);
            genrateName.append(locNameKey).append(""+i);
            String id = geneateId.toString();
            String name=genrateName.toString();
            BoardRoomDto boardRoomDto=new BoardRoomDto(id,name,true,location);
            BoardRoomDto createdBoardRoom=this.addnewBoardRoom(boardRoomDto);
        }
    }

    @Override
    public void autoUpdateExistingBoardRoom(Integer currentBoardCapacity, Integer newBoardCapacity, LocationDto locationDto) {
        if(newBoardCapacity>currentBoardCapacity){
            Location location=this.modelMapper.map(locationDto,Location.class);
            Integer locIdLey=locationDto.getId();
            String locNameKey=locationDto.getName().substring(0,1).toUpperCase();
            for(Integer i=currentBoardCapacity+1;i<=newBoardCapacity;i++){
                StringBuilder geneateId=new StringBuilder();
                StringBuilder genrateName=new StringBuilder();
                geneateId.append(""+locIdLey).append(locNameKey).append(""+i);
                genrateName.append(locNameKey).append(""+i);
                String id = geneateId.toString();
                String name=genrateName.toString();
                BoardRoomDto boardRoomDto=new BoardRoomDto(id,name,true,location);
                BoardRoomDto createdBoardRoom=this.addnewBoardRoom(boardRoomDto);

            }
        }
    }


}
