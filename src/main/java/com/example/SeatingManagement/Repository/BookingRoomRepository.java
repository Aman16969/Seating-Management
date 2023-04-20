package com.example.SeatingManagement.Repository;

import com.example.SeatingManagement.Entity.BookingRoom;
import com.example.SeatingManagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface BookingRoomRepository extends JpaRepository<BookingRoom,Integer> {
    List<BookingRoom> findAllByAdmin(User admin);
    List<BookingRoom> findAllByUserAndIsActive(User user, boolean isActive);

}
