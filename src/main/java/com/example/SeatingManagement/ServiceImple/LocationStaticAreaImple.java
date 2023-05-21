package com.example.SeatingManagement.ServiceImple;

import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.Entity.LocationStaticArea;

import com.example.SeatingManagement.ExceptionHandling.ResourceNotFound;
import com.example.SeatingManagement.Repository.LocationRepository;
import com.example.SeatingManagement.Repository.LocationStaticAreaRepository;
import com.example.SeatingManagement.Services.LocationStaticAreaServices;
import com.example.SeatingManagement.utils.AreaResponse;
import com.example.SeatingManagement.utils.StaticAreaBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LocationStaticAreaImple implements LocationStaticAreaServices {
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private LocationStaticAreaRepository locationStaticAreaRepository;
    @Override
    public LocationStaticArea addNewArea(StaticAreaBody staticAreaBody) {
        Location location = this.locationRepository.findById(staticAreaBody.getLocationId()).orElseThrow(()->new ResourceNotFound("Location", "location_id", staticAreaBody.getLocationId().toString()));
        LocationStaticArea area=new LocationStaticArea();
        area.setLocation(location);
        area.setR(staticAreaBody.getRow());
        area.setC(staticAreaBody.getCol());
        area.setName(staticAreaBody.getName());
        area.setD(staticAreaBody.getD());
        area.setId(""+location.getId()+"R"+staticAreaBody.getRow()+"C"+staticAreaBody.getCol()+"D"+staticAreaBody.getD());
        LocationStaticArea staticArea=this.locationStaticAreaRepository.save(area);
        return staticArea;
    }

    @Override
    public List<LocationStaticArea> getAreaByLocation(Integer id) {
        Location location=this.locationRepository.findById(id).orElseThrow(()->new ResourceNotFound("Location","Location_id",""+id));
        List<LocationStaticArea> areas= this.locationStaticAreaRepository.findByLocation(location);
        return areas;
    }

    @Override
    public AreaResponse getAreaByPosition(Integer locationId, Integer row, Integer column,Integer dir) {
        Location location=this.locationRepository.findById(locationId).orElseThrow(()->new ResourceNotFound("Location","Location_id",""+locationId));
        if(this.locationStaticAreaRepository.isThereAreaAtPosition(location, row, column,dir)==1){
            LocationStaticArea locationStaticArea = this.locationStaticAreaRepository.areaAtPosition(location, row, column,dir);
            return new AreaResponse(1, locationStaticArea.getId(), locationStaticArea.getName(),locationStaticArea.getD());
        }
        else {
            return new AreaResponse(0, "NA", "  ",0);
        }
    }

    @Override
    public AreaResponse deleteAreaById(String id, String value) {
        LocationStaticArea area=this.locationStaticAreaRepository.findById(id).orElseThrow(()->new ResourceNotFound("Seat","Seat_id",id));
       area.setActive(false);
        this.locationStaticAreaRepository.save(area);
        return new AreaResponse(0, "NA", "NA",0);
    }


    @Override
    public LocationStaticArea updateArea(LocationStaticArea locationStaticArea) {
        return null;
    }
}
