package com.example.SeatingManagement.Repository;

import com.example.SeatingManagement.Entity.BoardRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRoomRepository extends JpaRepository<BoardRoom,String> {
}
