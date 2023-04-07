package com.example.SeatingManagement.Services;

import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.ExceptionHandling.ResourceNotFound;
import com.example.SeatingManagement.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private SeatService seatService;


    public Location createLocation(Location location){
        Location newLocation = this.locationRepository.save(location);
        this.seatService.generateSeat(newLocation);
        return newLocation;
    }

    public List<Location> getAllLocation(){
        List<Location> location=this.locationRepository.findAll();
        return location;
    }
    public Location getLocationByIds(Integer id){
        return this.locationRepository.findById(id).orElseThrow(()->new ResourceNotFound("Location","id",String.valueOf(id)));
    }
    public Location updateLocation(Location newLocation, Integer id){
        Location forSeat=this.locationRepository.findById(id).orElseThrow(()->new ResourceNotFound("Location","Location_id",""+id));
        Integer currentCapacity=forSeat.getSeatingCapacity();
        Location updatedLocation = this.locationRepository.findById(id).map(location -> {
            location.setName(newLocation.getName());
            location.setSeatingCapacity(newLocation.getSeatingCapacity());
            Integer updatedSeatCapacity=location.getSeatingCapacity();
//            System.out.println(currentCapacity);
//            System.out.println(updatedSeatCapacity);
            if(currentCapacity.equals(updatedSeatCapacity)){
                return locationRepository.save(location);

            }else{
                Location updatedLoc=this.locationRepository.save(location);
                 this.seatService.updateSeatWhenUpdateLocation(currentCapacity,updatedSeatCapacity,updatedLoc);
                 return updatedLoc;
            }

        }).orElseThrow(()->new ResourceNotFound("Location","Location_id",""+id));


        return updatedLocation;
    }



    public Location getLocationById(Integer id){

        return this.locationRepository.findById(id).orElseThrow(()->new ResourceNotFound("Location","Location_id",""+id));
    }



    public void deleteLocationById(Integer id){
        this.locationRepository.deleteById(id);
    }
}