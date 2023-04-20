package com.example.SeatingManagement.Repository;

import com.example.SeatingManagement.Entity.RequestBookingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestBookingRepository extends JpaRepository<RequestBookingRoom,Integer> {
    List<RequestBookingRoom> findByIsActive(boolean isActive);
    List<RequestBookingRoom> findByEmailAndIsActive(String email, boolean isActive);
}
