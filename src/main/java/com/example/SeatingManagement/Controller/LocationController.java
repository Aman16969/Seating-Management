package com.example.SeatingManagement.Controller;

import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.Services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.ok(newLocation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Location> updateLocation(@RequestBody Location location, @PathVariable(value = "id") Integer id){
        Location updatedLocation = this.locationService.updateLocation(location, id);
        return ResponseEntity.ok(updatedLocation);
    }

    @GetMapping("/")
    public List<Location> getAllLocations(){
        return this.locationService.getAllLocations();
    }

    @GetMapping("/{id}")
    public Optional<Location> getLocationById(@PathVariable(value = "id") Integer id){
        return this.locationService.getLocationById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteLocationById(@PathVariable(value = "id") int id){
        this.locationService.deleteLocationById(id);
    }
}
