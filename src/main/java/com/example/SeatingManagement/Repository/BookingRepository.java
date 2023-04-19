package com.example.SeatingManagement.Repository;

import com.example.SeatingManagement.Entity.Booking;
import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.Entity.Seat;
import com.example.SeatingManagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {
//    List<Booking> findByDateAndLocation(LocalDate date, Location location);
//    List<Booking> findByLocation(Location location);
//    List<Booking> findByDate(LocalDate date);
//
//    List<Booking> findByUserAndIsActive(User user, boolean isActive);
//
//
//    @Query("SELECT s FROM Seat s WHERE s.location = :location AND s NOT IN "
//            + "(SELECT b.seat FROM Booking b WHERE b.location = :location AND b.date = :date)")
//    List<Seat> findAvailableSeatsByLocationAndDate(@Param("location") Location location, @Param("date") LocalDate date);
//
//    @Query("SELECT count(*) from Booking b where b.user = :user AND b.date = :date")
//    Integer isUserBookedOnThatDate(@Param("user") User user, @Param("date") LocalDate date);
//
//    @Query("SELECT count(*) from Booking b where b.seat = :seat AND b.date = :date")
//    Integer isSeatBookedOnThatDate(@Param("seat") Seat seat, @Param("date") LocalDate date);
    @Query("SELECT s FROM Seat s WHERE s.location = :location AND s NOT IN (SELECT b.seat FROM Booking b WHERE b.isActive = true AND b.location = :location AND ((b.fromDate <= :toDate AND b.toDate => :fromDate) AND (b.fromDate = :fromDate AND b.toDate = :toDate AND b.fromTime < :toTime AND b.toTime > :fromTime)))")
    List<Seat> findAvailableSeats(@Param("location") Location location, @Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate, @Param("fromTime") LocalTime fromTime, @Param("toTime") LocalTime toTime);

    @Query("SELECT b from Booking where b.location = :location AND (b.fromDate<= :date AND b.toDate>= :date)")
    List<Booking> findByDateAndLocation(@Param("date") LocalDate date, @Param("location") Location location);
    @Query("SELECT b from Booking where b.location = :location")
    List<Booking> findByLocation(Location location);
//    @Query("SELECT b from Booking where b.fromDate<= :date AND b.toDate>= :date")
//    List<Booking> findByDate(LocalDate date);
    @Query("SELECT b from Booking where b.user = :user")
    List<Booking> findByUser(User user);
//    @Query("SELECT b from Booking where b.user = :user AND (b.fromDate<= :date AND b.toDate>= :date)")
//    List<Booking> findByDateAndUser(LocalDate date, User user);
}
