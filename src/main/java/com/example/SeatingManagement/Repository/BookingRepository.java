package com.example.SeatingManagement.Repository;

import com.example.SeatingManagement.Entity.Booking;
import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.Entity.Seat;
import com.example.SeatingManagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {
    @Query("SELECT b FROM Booking b WHERE b.date BETWEEN :startDate AND :endDate")
    List<Booking> findAllByBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT b FROM Booking b WHERE b.date BETWEEN :startDate AND :endDate AND b.location = :location")
    List<Booking> findAllByBetweenDatesAndLocation(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("location") Location location);

    @Query("SELECT b from Booking b WHERE b.date = :givenDate AND b.accoliteId = :id AND b.isActive = true")
    Booking findBookingByAccoliteIdAndDate(@Param("givenDate") LocalDate date, @Param("id") String accoliteId);

    @Query("SELECT count(b) from Booking b WHERE b.date = :givenDate AND b.accoliteId = :id AND b.isActive = true")
    Integer isBookingByAccoliteIdAndDate(@Param("givenDate") LocalDate date, @Param("id") String accoliteId);

    List<Booking> findByDateAndLocation(LocalDate date, Location location);
    List<Booking> findByLocation(Location location);
    @Query("SELECT b from Booking b WHERE b.date = :date AND b.location = :location AND b.isActive = true")
    Booking findBookingByDateAndLocation(@Param("date") LocalDate date, @Param("location") Location location);

    List<Booking> findByDate(LocalDate date);
    List<Booking> findByIsActive(boolean isActive);
    List<Booking> findByUserAndIsActive(User user, boolean isActive);


    @Query("SELECT s FROM Seat s WHERE s.location = :location AND s NOT IN "
            + "(SELECT b.seat FROM Booking b WHERE  b.isActive = true AND b.location = :location AND b.date = :date)")
    List<Seat> findAvailableSeatsByLocationAndDate(@Param("location") Location location, @Param("date") LocalDate date);

    @Query("SELECT s FROM Seat s WHERE s.location = :location AND s NOT IN "
            + "(SELECT b.seat FROM Booking b WHERE b.date = :date AND b.isActive = true AND ((b.fromTime > :fromTime AND b.fromTime < :toTime) OR (b.toTime > :fromTime AND b.toTime < :toTime) OR (b.fromTime <= :fromTime AND b.toTime >= :toTime)))")
    List<Seat> findAvailableSeatsByLocationDateTime(@Param("location") Location location, @Param("date") LocalDate date, @Param("fromTime") LocalTime fromTime, @Param("toTime") LocalTime toTime);

    @Query("SELECT count(*) from Booking b where b.isActive = true AND b.user = :user AND b.date = :date")
    Integer isUserBookedOnThatDate(@Param("user") User user, @Param("date") LocalDate date);

    @Query("SELECT count(*) from Booking b where  b.seat = :seat AND b.date = :date AND b.isActive = true AND  ((b.fromTime > :fromTime AND b.fromTime < :toTime) OR (b.toTime > :fromTime AND b.toTime < :toTime) OR (b.fromTime < :fromTime AND b.toTime > :toTime))")
    Integer isSeatBookedOnThatDateAndTime(@Param("seat") Seat seat, @Param("date") LocalDate date,@Param("fromTime") LocalTime fromTime,@Param("toTime") LocalTime tofromTime);
}