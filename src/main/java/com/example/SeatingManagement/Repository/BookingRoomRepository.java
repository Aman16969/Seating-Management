package com.example.SeatingManagement.Repository;

import com.example.SeatingManagement.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface BookingRoomRepository extends JpaRepository<BookingRoom,Integer> {
    List<BookingRoom> findAllByAdmin(User admin);
    List<BookingRoom> findByLocation(Location location);
    List<BookingRoom> findAllByUserAndIsActive(User user, boolean isActive);
    @Query("SELECT b from BookingRoom b WHERE b.date BETWEEN :startDate AND :endDate AND roomType = :type")
    List<BookingRoom> findBookingsBtwDates(@PathVariable("startDate") LocalDate startDate, @PathVariable("endDate") LocalDate endDate, @PathVariable("type") String type);
    @Query("SELECT s FROM Room s WHERE s.location = :location AND s NOT IN "
            + "(SELECT b.room FROM BookingRoom b WHERE b.date = :date AND b.isActive = true AND ((b.fromTime > :fromTime AND b.fromTime < :toTime) OR (b.toTime > :fromTime AND b.toTime < :toTime) OR (b.fromTime <= :fromTime AND b.toTime >= :toTime)))")
    List<Room> findAvailableRoomsByLocationDateTime(@Param("location") Location location, @Param("date") LocalDate date, @Param("fromTime") LocalTime fromTime, @Param("toTime") LocalTime toTime);

    @Query("SELECT b from BookingRoom b WHERE b.date BETWEEN :startDate AND :endDate AND b.roomType = :type AND b.location = :location")
    List<BookingRoom> findBookingsBtwDatesAndLocation(@PathVariable("startDate") LocalDate startDate, @PathVariable("endDate") LocalDate endDate, @PathVariable("type") String type, @PathVariable("location") Location location);
}
