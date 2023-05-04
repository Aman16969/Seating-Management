package com.example.SeatingManagement.Repository;

import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.Entity.RequestBookingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestBookingRepository extends JpaRepository<RequestBookingRoom,Integer> {
    @Query("SELECT r FROM RequestBookingRoom r WHERE r.isActive = :isActive AND r.accepted = false")
    List<RequestBookingRoom> findByIsActiveIsNotAccepted(@Param("isActive") boolean isActive);

    @Query("SELECT r FROM RequestBookingRoom r JOIN User u ON r.email=u.email AND u.location = :location")
    List<RequestBookingRoom> findAllByLocation(Location location);


    List<RequestBookingRoom> findByEmailAndIsActive(String email, boolean isActive);
}
