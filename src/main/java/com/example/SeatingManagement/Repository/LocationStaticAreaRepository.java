package com.example.SeatingManagement.Repository;

import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.Entity.LocationStaticArea;
import com.example.SeatingManagement.Entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationStaticAreaRepository extends JpaRepository<LocationStaticArea,String> {

    List<LocationStaticArea> findByLocation(Location location);
    @Query("SELECT count(*) from LocationStaticArea s where s.location = :location AND s.r = :row AND s.c = :column AND s.d = :dir  AND isActive = true")
    Integer isThereAreaAtPosition(@Param("location") Location location, @Param("row") Integer row, @Param("column") Integer col ,@Param("dir") Integer dir);

    @Query("SELECT s from LocationStaticArea s where s.location = :location AND s.r = :row AND s.c = :column AND s.d = :dir")
    LocationStaticArea areaAtPosition(@Param("location") Location location, @Param("row") Integer row, @Param("column") Integer col, @Param("dir") Integer dir);

}
