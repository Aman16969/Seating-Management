package com.example.SeatingManagement.ServiceImple;


import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.Entity.Seat;
import com.example.SeatingManagement.EntityRequestBody.LocationDto;
import com.example.SeatingManagement.EntityRequestBody.SeatDto;
import com.example.SeatingManagement.ExceptionHandling.ResourceNotFound;
import com.example.SeatingManagement.Repository.LocationRepository;
import com.example.SeatingManagement.Repository.SeatRepository;
import com.example.SeatingManagement.Services.SeatService;
import com.example.SeatingManagement.utils.SeatBody;
import com.example.SeatingManagement.utils.SeatResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public SeatDto addNewSeat(SeatBody seatBody) {
        Location location = this.locationRepository.findById(seatBody.getLocationId()).orElseThrow(()->new ResourceNotFound("Location", "location_id", seatBody.getLocationId().toString()));
        Seat seat = new Seat();
        seat.setLocation(location);
        seat.setR(seatBody.getRow());
        seat.setC(seatBody.getCol());
        seat.setName(seatBody.getName());
        seat.setId(""+location.getId()+"R"+seatBody.getRow()+"C"+seatBody.getCol());
       Seat newSeat=this.seatRepository.save(seat);
       SeatDto newSeatDto=this.modelMapper.map(newSeat,SeatDto.class);
       return newSeatDto;
    }

    @Override
    public List<SeatDto> getAllSeats() {
        List<Seat> seats=this.seatRepository.findAll();
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
    public SeatResponse deleteSeatById(String id, String value) {
        Seat seat=this.seatRepository.findById(id).orElseThrow(()->new ResourceNotFound("Seat","Seat_id",id));
        if(value.equals("true")){
            seat.setActive(true);
        }
        else{
            seat.setActive(false);
        }
        this.seatRepository.save(seat);
        return new SeatResponse(0, "NA", "NA", 0);
    }

    @Override
    public List<SeatDto> getSeatsByLocation(Integer id) {
        Location location=this.locationRepository.findById(id).orElseThrow(()->new ResourceNotFound("Location","Location_id",""+id));
        List<Seat> seats=this.seatRepository.findSeatsByLocationId(location);
        List<SeatDto> seatDtos=seats.stream().map((e)->this.modelMapper.map(e,SeatDto.class)).collect(Collectors.toList());
        return seatDtos;
    }

//    @Override
//    public void autoGenerateSeats(LocationDto locationDto) {
//        Location location=this.modelMapper.map(locationDto,Location.class);
//        Integer locIdLey=locationDto.getId();
//        String locNameKey=locationDto.getName().substring(0,1).toUpperCase();
//        Integer locSeatCapacity=locationDto.getSeatingCapacity();
//        for(int i=1;i<=locSeatCapacity;i++){
//            StringBuilder geneateId=new StringBuilder();
//            StringBuilder genrateName=new StringBuilder();
//            geneateId.append(""+locIdLey).append(locNameKey).append(""+i);
//            genrateName.append(locNameKey).append(""+i);
//            String id = geneateId.toString();
//            String name=genrateName.toString();
//            SeatDto seatdto=new SeatDto(id,name,true,location);
////            SeatDto createdSeat=addNewSeat(seatdto);
//        }
//    }

//    @Override
//    public void autoUpdateExistingSeats(Integer currentCapacity, Integer newCapacity, LocationDto locationDto){
//        if(newCapacity>currentCapacity){
//            Location location=this.modelMapper.map(locationDto,Location.class);
//            Integer locIdLey=locationDto.getId();
//            String locNameKey=locationDto.getName().substring(0,1).toUpperCase();
//            for(Integer i=currentCapacity+1;i<=newCapacity;i++){
//                StringBuilder geneateId=new StringBuilder();
//                StringBuilder genrateName=new StringBuilder();
//                geneateId.append(""+locIdLey).append(locNameKey).append(""+i);
//                genrateName.append(locNameKey).append(""+i);
//                String id = geneateId.toString();
//                String name=genrateName.toString();
//                SeatDto seatdto=new SeatDto(id,0,0,0,name,true,location);
////                SeatDto createdSeat=addNewSeat(seatdto);
//            }
//        }
//    }

    @Override
    public SeatResponse getSeatByPosition(Integer locationId, Integer row, Integer column) {
        Location location=this.locationRepository.findById(locationId).orElseThrow(()->new ResourceNotFound("Location","Location_id",""+locationId));
        if(this.seatRepository.isThereSeatAtPosition(location, row, column)==1){
            Seat seat = this.seatRepository.seatAtPosition(location, row, column);
            return new SeatResponse(1, seat.getId(), seat.getName(), seat.getD());
        }
        else {
            return new SeatResponse(0, "  ", "  ", 0);
        }
    }

    @Override
    public SeatDto updateSeat(SeatDto seatDto) {
        Seat seat = this.seatRepository.findById(seatDto.getId()).orElseThrow(()->new ResourceNotFound("Seat", "seat_id", seatDto.getId()));
        seat.setName(seatDto.getName());
        seat.setActive(seatDto.isActive());
        seat.setR(seatDto.getR());
        seat.setC(seatDto.getC());
        seat.setD(seatDto.getD());
        Seat newSeat = this.seatRepository.save(seat);
        SeatDto newSeatDto=this.modelMapper.map(newSeat,SeatDto.class);
        return newSeatDto;
    }
}