package com.example.SeatingManagement.Repository;

import com.example.SeatingManagement.Entity.DisscussionRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisscussionRoomRepository extends JpaRepository<DisscussionRoom,String> {
}
