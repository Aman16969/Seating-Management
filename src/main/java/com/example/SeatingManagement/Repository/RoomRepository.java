package com.example.SeatingManagement.Repository;


import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.Entity.Room;
import com.example.SeatingManagement.Entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room,Integer> {
    @Query("SELECT s FROM Room s WHERE s.location = ?1")
    List<Room> findRoomsByLocationId(Location location);

    @Query("SELECT s FROM Room s WHERE s.location = :location AND s.roomType = :type")
    List<Room> findRoomsByLocationAndType(@Param("location") Location location, @Param("type") String type);
}
