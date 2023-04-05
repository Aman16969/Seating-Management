package com.example.SeatingManagement.Controller;

import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.PayLoad.ApiResponse;
import com.example.SeatingManagement.Services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/location")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @PostMapping("/")
    public ResponseEntity<Location> createLocation(@RequestBody Location location){
        Location newLocation = this.locationService.createLocation(location);
        return new ResponseEntity<>(newLocation, HttpStatus.CREATED);
    }
    @GetMapping("/")
    public ResponseEntity<List<Location>> getAllLocation(){
        List<Location> allLocations = this.locationService.getAllLocation();
        return new ResponseEntity<>(allLocations, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Location> updateLocation(@RequestBody Location location, @PathVariable(value = "id") Integer id){
        Location updatedLocation = this.locationService.updateLocation(location, id);
        return new ResponseEntity<>(updatedLocation, HttpStatus.CREATED);
    }




    @GetMapping("/{id}")
    public Location getLocationById(@PathVariable(value = "id") Integer id){
        return this.locationService.getLocationById(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteLocation(@PathVariable("id") Integer id) {
        this.locationService.deleteLocationById(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Location deleted Successfully", true), HttpStatus.OK);
    }


}

