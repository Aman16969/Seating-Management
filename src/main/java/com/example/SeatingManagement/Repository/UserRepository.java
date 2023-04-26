package com.example.SeatingManagement.Repository;

import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
    List<User> findByLocation(Location location);
    List<User> findByLocationAndRole(Location location, String role);
    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id = :userId")
    void deleteUserById(@Param("userId") String userId);
    Optional<User> findByAccoliteId(String accoliteId);
}
