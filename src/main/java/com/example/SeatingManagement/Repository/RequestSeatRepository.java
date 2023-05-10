package com.example.SeatingManagement.Repository;

import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.Entity.RequestSeat;
import com.example.SeatingManagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestSeatRepository extends JpaRepository<RequestSeat, Integer> {
//    @Query("SELECT rs FROM RequestSeat rs WHERE rs.location = :location AND rs.isActive = true")
//    List<RequestSeat> requestsByLocation(@Param("location") Location location);
//
//    @Query("SELECT rs FROM RequestSeat rs WHERE rs.user = :user AND rs.isActive = true")
//    List<RequestSeat> requestsByUser(@Param("user") User user);
//
//    @Query("SELECT rs FROM RequestSeat rs WHERE rs.user = :user AND rs.isActive = true AND rs.status = 'REQUESTED'")
//    List<RequestSeat> requestsByUserAndRequested(@Param("user") User user);
//
//    @Query("SELECT rs FROM RequestSeat rs WHERE rs.admin = :admin AND rs.isActive = true")
//    List<RequestSeat> requestsByAdmin(@Param("admin") User admin);

    List<RequestSeat> findByLocationAndIsActive(Location location, boolean isActive);
    List<RequestSeat> findByUserAndIsActive(User user, boolean isActive);

}
