package com.example.SeatingManagement.Services;

import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired

    private LocationRepository locationRepository;


    public Location createLocation(Location location){
        Location newLocation = this.locationRepository.save(location);
        return newLocation;
    }

    public Location updateLocation(Location newLocation, Integer id){
        Location updatedLocation = this.locationRepository.findById(id).map(location -> {
            location.setName(newLocation.getName());
            location.setSeatingCapacity(newLocation.getSeatingCapacity());
            return locationRepository.save(location);
        }).orElseGet(()->{
            newLocation.setId(id);
            return locationRepository.save(newLocation);
        });
        return updatedLocation;
    }

    public List<Location> getAllLocations(){
        return this.locationRepository.findAll();
    }

    public Optional<Location> getLocationById(Integer id){
        return this.locationRepository.findById(id);
    }

    public void deleteLocationById(Integer id){
        this.locationRepository.deleteById(id);
    }
}