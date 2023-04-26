package com.example.SeatingManagement.Repository;

import com.example.SeatingManagement.Entity.SwiftData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SwiftRepository extends JpaRepository<SwiftData, String> {
    SwiftData findByEmail(String Email);
}
