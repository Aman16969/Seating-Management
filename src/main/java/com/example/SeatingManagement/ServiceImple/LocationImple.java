package com.example.SeatingManagement.ServiceImple;

import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.EntityRequestBody.LocationDto;
import com.example.SeatingManagement.ExceptionHandling.IllegalArgument;
import com.example.SeatingManagement.ExceptionHandling.ResourceNotFound;
import com.example.SeatingManagement.Repository.LocationRepository;
import com.example.SeatingManagement.Services.LocationService;
import com.example.SeatingManagement.Services.SeatService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class LocationImple implements LocationService {

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private ModelMapper modelMaper;
    @Autowired
    private SeatService seatService;

    @Override
    public LocationDto createLocation(LocationDto locationDto) {
        Location location=this.modelMaper.map(locationDto,Location.class);
        Location newLocation=this.locationRepository.save(location);
        LocationDto newLocationDto=this.modelMaper.map(newLocation,LocationDto.class);
        this.seatService.autoGenerateSeats(newLocationDto);
        return newLocationDto;
    }

    @Override
    public List<LocationDto> getAllLocations() {
        List<Location> allLocation=this.locationRepository.findAll();
        List<LocationDto> allLocationDto=allLocation.stream().map((e)->this.modelMaper.map(e,LocationDto.class)).collect(Collectors.toList());
        return allLocationDto;
    }
    @Override
    public LocationDto getLocationById(Integer id) {
        Location location=this.locationRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Location", "location_id", ""+id));
        LocationDto locationDto=this.modelMaper.map(location,LocationDto.class);
        return locationDto;

    }

    @Override
    public LocationDto updateLocationById(LocationDto locationDto, Integer id) {
        Location location=this.locationRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Location", "location_id", ""+id));
        Integer currentSeatCapacity=location.getSeatingCapacity();
        Integer newSeatCapacity=locationDto.getSeatingCapacity();
        if(newSeatCapacity<currentSeatCapacity) {
            throw (new IllegalArgument("Seats",currentSeatCapacity,newSeatCapacity));
        }
        location.setName(locationDto.getName());
        location.setAddress(locationDto.getAddress());
        location.setSeatingCapacity(locationDto.getSeatingCapacity());
        Location updatedLocation=this.locationRepository.save(location);
        LocationDto updatedLocationDto=this.modelMaper.map(updatedLocation,LocationDto.class);
        this.seatService.autoUpdateExistingSeats(currentSeatCapacity,newSeatCapacity,updatedLocationDto);
        return updatedLocationDto;
    }

    @Override
    public void deleteLocationById(Integer id) {
        Location location=this.locationRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Location", "location_id", ""+id));
        this.locationRepository.delete(location);
    }
}
