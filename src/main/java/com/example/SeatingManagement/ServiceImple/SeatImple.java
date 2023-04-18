package com.example.SeatingManagement.ServiceImple;


import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.Entity.Seat;
import com.example.SeatingManagement.EntityRequestBody.LocationDto;
import com.example.SeatingManagement.EntityRequestBody.SeatDto;
import com.example.SeatingManagement.ExceptionHandling.ResourceNotFound;
import com.example.SeatingManagement.Repository.LocationRepository;
import com.example.SeatingManagement.Repository.SeatRepository;
import com.example.SeatingManagement.Services.SeatService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatImple implements SeatService {
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SeatDto addNewSeat(SeatDto seatDto) {
       Seat seat=this.modelMapper.map(seatDto,Seat.class);
       Seat newSeat=this.seatRepository.save(seat);
       SeatDto newSeatDto=this.modelMapper.map(newSeat,SeatDto.class);
       return newSeatDto;
    }

    @Override
    public List<SeatDto> getAllSeats() {
        List<Seat> seats=this.seatRepository.findAll();
        System.out.println(seats);
        List<SeatDto> seatDtos=seats.stream().map((e)->this.modelMapper.map(e,SeatDto.class)).collect(Collectors.toList());
        return seatDtos;
    }

    @Override
    public SeatDto getSeatById(String id) {
        Seat seat=this.seatRepository.findById(id).orElseThrow(()->new ResourceNotFound("Seat","Seat_id",id));
        SeatDto seatDto=this.modelMapper.map(seat,SeatDto.class);
        return seatDto;
    }

    @Override
    public void deleteSeatById(String id) {
        Seat seat=this.seatRepository.findById(id).orElseThrow(()->new ResourceNotFound("Seat","Seat_id",id));
        this.seatRepository.delete(seat);
    }

    @Override
    public List<SeatDto> getSeatsByLocation(Integer id) {
        Location location=this.locationRepository.findById(id).orElseThrow(()->new ResourceNotFound("Location","Location_id",""+id));
        List<Seat> seats=this.seatRepository.findSeatsByLocationId(location);
        List<SeatDto> seatDtos=seats.stream().map((e)->this.modelMapper.map(e,SeatDto.class)).collect(Collectors.toList());
        return seatDtos;
    }

    @Override
    public void autoGenerateSeats(LocationDto locationDto) {
        Location location=this.modelMapper.map(locationDto,Location.class);
        Integer locIdLey=locationDto.getId();
        String locNameKey=locationDto.getName().substring(0,1).toUpperCase();
        Integer locSeatCapacity=locationDto.getSeatingCapacity();
        for(int i=1;i<=locSeatCapacity;i++){
            StringBuilder geneateId=new StringBuilder();
            StringBuilder genrateName=new StringBuilder();
            geneateId.append(""+locIdLey).append(locNameKey).append(""+i);
            genrateName.append(locNameKey).append(""+i);
            String id = geneateId.toString();
            String name=genrateName.toString();
            SeatDto seatdto=new SeatDto(id,name,true,location);
            SeatDto createdSeat=addNewSeat(seatdto);
        }
    }

    @Override
    public void autoUpdateExistingSeats(Integer currentCapacity, Integer newCapacity, LocationDto locationDto){
        if(newCapacity>currentCapacity){
            Location location=this.modelMapper.map(locationDto,Location.class);
            Integer locIdLey=locationDto.getId();
            String locNameKey=locationDto.getName().substring(0,1).toUpperCase();
            for(Integer i=currentCapacity+1;i<=newCapacity;i++){
                StringBuilder geneateId=new StringBuilder();
                StringBuilder genrateName=new StringBuilder();
                geneateId.append(""+locIdLey).append(locNameKey).append(""+i);
                genrateName.append(locNameKey).append(""+i);
                String id = geneateId.toString();
                String name=genrateName.toString();
                SeatDto seatdto=new SeatDto(id,name,true,location);
                SeatDto createdSeat=addNewSeat(seatdto);

            }
        }
    }
}
