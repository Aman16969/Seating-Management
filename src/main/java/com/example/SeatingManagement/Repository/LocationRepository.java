package com.example.SeatingManagement.Repository;

import com.example.SeatingManagement.Entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    List<Location> findByIsActive(boolean isActive);
}
