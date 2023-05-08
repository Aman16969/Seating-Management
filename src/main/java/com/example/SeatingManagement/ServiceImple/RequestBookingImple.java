package com.example.SeatingManagement.ServiceImple;

import com.example.SeatingManagement.Entity.RequestBookingRoom;
import com.example.SeatingManagement.EntityRequestBody.RequestBookingRoomDto;
import com.example.SeatingManagement.ExceptionHandling.ResourceNotFound;
import com.example.SeatingManagement.Repository.RequestBookingRepository;
import com.example.SeatingManagement.Services.RequestBookingService;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestBookingImple implements RequestBookingService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RequestBookingRepository requestBookingRepository;
    @Override
    public RequestBookingRoomDto createNewRequest(RequestBookingRoomDto requestBookingRoomDto) {
        RequestBookingRoom req=this.modelMapper.map(requestBookingRoomDto,RequestBookingRoom.class);
        RequestBookingRoom newRequest=this.requestBookingRepository.save(req);
        return this.modelMapper.map(newRequest,RequestBookingRoomDto.class);

    }

    @Override
    public String setActiveStatus(Integer id, boolean value) {
        RequestBookingRoom request=this.requestBookingRepository.findById(id).orElseThrow(()->new ResourceNotFound("Request","request_id",""+id));
        request.setActive(value);
        this.requestBookingRepository.save(request);
        return "request with "+id+"'s active status changed to "+value;
    }

    @Override
    public String setAccepted(Integer id, boolean value) {
        RequestBookingRoom request=this.requestBookingRepository.findById(id).orElseThrow(()->new ResourceNotFound("Request","request_id",""+id));
        request.setAccepted(value);
        this.requestBookingRepository.save(request);
        return "your request have been accepted and yoy are allotted a room";
    }

    @Override
    public List<RequestBookingRoomDto> allRequest() {
        List<RequestBookingRoom> requestBookingRooms=this.requestBookingRepository.findByIsActiveIsNotAccepted(true);
        List<RequestBookingRoomDto> requests=requestBookingRooms.stream().map((e)->this.modelMapper.map(e,RequestBookingRoomDto.class)).collect(Collectors.toList());
        return requests;
    }

    @Override
    public RequestBookingRoomDto getAllRequestById(Integer id) {
        RequestBookingRoom request=this.requestBookingRepository.findById(id).orElseThrow(()->new ResourceNotFound("Request","request_id",""+id));
        RequestBookingRoomDto requestBookingRoomDto=this.modelMapper.map(request,RequestBookingRoomDto.class);
        return requestBookingRoomDto;

    }

    @Override
    public List<RequestBookingRoomDto> allRequestByUser(String email) {
        List<RequestBookingRoom> requestBookingRooms=this.requestBookingRepository.findByEmailAndIsActiveAndIsNotAccepted(email,true);
        List<RequestBookingRoomDto> requests=requestBookingRooms.stream().map((e)->this.modelMapper.map(e,RequestBookingRoomDto.class)).collect(Collectors.toList());
        return requests;
    }
}
