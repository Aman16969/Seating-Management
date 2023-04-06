package com.example.SeatingManagement.Repository;

import com.example.SeatingManagement.Entity.BookingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<BookingDetail,Integer> {
}
