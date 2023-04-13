package com.example.SeatingManagement.Controller;

import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.EntityRequestBody.LocationDto;
import com.example.SeatingManagement.PayLoad.ApiResponse;
import com.example.SeatingManagement.Services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/location")
@CrossOrigin
public class LocationController {
    @Autowired
    private LocationService locationService;

    @PostMapping("/")
    public ResponseEntity<LocationDto> createLocation(@Valid @RequestBody LocationDto locationDto){
        LocationDto newLocation = this.locationService.createLocation(locationDto);
        return new ResponseEntity<>(newLocation, HttpStatus.CREATED);
    }
    @GetMapping("/")
    public ResponseEntity<List<LocationDto>> getAllLocations(){
        List<LocationDto> allLocations = this.locationService.getAllLocations();
        return new ResponseEntity<>(allLocations, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/{id}")
    public ResponseEntity<LocationDto> updateLocationById( @Valid @RequestBody LocationDto locationDto, @PathVariable(value = "id") Integer id){
        LocationDto updatedLocationDto = this.locationService.updateLocationById(locationDto,id);
        return new ResponseEntity<>(updatedLocationDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationDto> getLocationById(@PathVariable(value = "id") Integer id){
        LocationDto locationDto=this.locationService.getLocationById(id);
        return new ResponseEntity<>(locationDto,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteLocation(@PathVariable("id") Integer id) {
        this.locationService.deleteLocationById(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Location deleted Successfully", true), HttpStatus.OK);
    }


}

