package com.example.SeatingManagement.Repository;

import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

Optional<User> findByEmail(String email);

    @Query("SELECT u.location FROM User u WHERE u.id = :id")
    Location findLocationByUserId(@Param("id") String userId);
}
