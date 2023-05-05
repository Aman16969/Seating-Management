package com.example.SeatingManagement.Services;

import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.EntityRequestBody.LocationDto;
import com.example.SeatingManagement.ExceptionHandling.ResourceNotFound;
import com.example.SeatingManagement.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface LocationService {

    LocationDto createLocation(LocationDto locationDto);
    List<LocationDto> getAllLocations();
    LocationDto getLocationById(Integer id);
    LocationDto updateLocationById(LocationDto locationDto, Integer id);
//    void deleteLocationById(Integer id);
//    String setActiveStatus(Integer id,boolean value);

    LocationDto updateLocationRowAndColumn(Integer locationId, Integer row, Integer column);
}