package com.example.SeatingManagement.Controller;


import com.example.SeatingManagement.Entity.LocationStaticArea;

import com.example.SeatingManagement.Services.LocationStaticAreaServices;
import com.example.SeatingManagement.utils.AreaResponse;

import com.example.SeatingManagement.utils.SeatResponse;
import com.example.SeatingManagement.utils.StaticAreaBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/locationStaticArea")
@CrossOrigin(origins= "*", allowedHeaders = "*")
public class LocationStaticAreaController {

    @Autowired
    private LocationStaticAreaServices locationStaticAreaServices;

    @PostMapping("/")
    public ResponseEntity<LocationStaticArea> addNewArea(@Valid @RequestBody StaticAreaBody staticAreaBody) {
        LocationStaticArea newArea = this.locationStaticAreaServices.addNewArea(staticAreaBody);
        return new ResponseEntity<>(newArea, HttpStatus.OK);
    }
    @GetMapping("/location/{id}")
    public ResponseEntity<List<LocationStaticArea>> getAreaByLocation(@PathVariable("id") Integer id){
        List<LocationStaticArea> areas = this.locationStaticAreaServices.getAreaByLocation(id);
        return new ResponseEntity<>(areas, HttpStatus.OK);
    }
    @GetMapping("/position")
    public ResponseEntity<AreaResponse> getAreaByPosition(@RequestParam("location") Integer locationId, @RequestParam("row") Integer row, @RequestParam("column") Integer column, @RequestParam("dir") Integer dir){
        AreaResponse areaResponse = this.locationStaticAreaServices.getAreaByPosition(locationId, row, column,dir);
        return new ResponseEntity<>(areaResponse, HttpStatus.OK);
    }
    @PutMapping("/{id}/{value}")
    public ResponseEntity<AreaResponse> deleteArea(@PathVariable("id") String id, @PathVariable("value") String value){
        AreaResponse area = this.locationStaticAreaServices.deleteAreaById(id, value);
        return new ResponseEntity<>(area, HttpStatus.OK);
    }
}
