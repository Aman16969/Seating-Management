package com.example.SeatingManagement.Services;

import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public Location createLocation(Location location){
        Location newLocation = this.locationRepository.save(location);
        return newLocation;
    }

    public Location updateLocation(Location location){
        return null;
    }

    public List<Location> getAllLocations(){
        return null;
    }

    public Location getLocationById(Integer id){
        return null;
    }

    public void deleteLocationById(Integer id){

    }
}
