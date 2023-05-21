package com.example.SeatingManagement.Services;

import com.example.SeatingManagement.Entity.LocationStaticArea;
import com.example.SeatingManagement.EntityRequestBody.SeatDto;
import com.example.SeatingManagement.utils.AreaResponse;
import com.example.SeatingManagement.utils.SeatBody;
import com.example.SeatingManagement.utils.SeatResponse;
import com.example.SeatingManagement.utils.StaticAreaBody;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LocationStaticAreaServices {
    LocationStaticArea addNewArea(StaticAreaBody staticAreaBody);
    List<LocationStaticArea> getAreaByLocation(Integer id);
    AreaResponse getAreaByPosition(Integer locationId, Integer row, Integer column,Integer dir);
    AreaResponse deleteAreaById(String id, String value);
    LocationStaticArea updateArea(LocationStaticArea locationStaticArea);

}
