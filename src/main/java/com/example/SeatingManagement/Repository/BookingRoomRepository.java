package com.example.SeatingManagement.Repository;

import com.example.SeatingManagement.Entity.BookingRoom;
import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRoomRepository extends JpaRepository<BookingRoom,Integer> {
    List<BookingRoom> findAllByAdmin(User admin);
    List<BookingRoom> findByLocation(Location location);
    List<BookingRoom> findAllByUserAndIsActive(User user, boolean isActive);
    @Query("SELECT b from BookingRoom b WHERE b.date BETWEEN :startDate AND :endDate AND roomType = :type")
    List<BookingRoom> findBookingsBtwDates(@PathVariable("startDate") LocalDate startDate, @PathVariable("endDate") LocalDate endDate, @PathVariable("type") String type);

    @Query("SELECT b from BookingRoom b WHERE b.date BETWEEN :startDate AND :endDate AND b.roomType = :type AND b.location = :location")
    List<BookingRoom> findBookingsBtwDatesAndLocation(@PathVariable("startDate") LocalDate startDate, @PathVariable("endDate") LocalDate endDate, @PathVariable("type") String type, @PathVariable("location") Location location);
}
