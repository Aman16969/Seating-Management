package com.example.SeatingManagement.Repository;

import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.Entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SeatRepository extends JpaRepository<Seat, String> {
    @Query("SELECT s FROM Seat s WHERE s.location = ?1")
    List<Seat> findSeatsByLocationId(Location location);

    @Query("SELECT count(*) from Seat s where s.location = :location AND s.r = :row AND s.c = :column AND isActive = true")
    Integer isThereSeatAtPosition(@Param("location") Location location, @Param("row") Integer row, @Param("column") Integer col);

    @Query("SELECT s from Seat s where s.location = :location AND s.r = :row AND s.c = :column")
    Seat seatAtPosition(@Param("location") Location location, @Param("row") Integer row, @Param("column") Integer col);
}