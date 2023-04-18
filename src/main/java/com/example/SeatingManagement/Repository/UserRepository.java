package com.example.SeatingManagement.Repository;

import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.Entity.Role;
import com.example.SeatingManagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {


    @Query("SELECT u.location FROM User u WHERE u.id = :id")
    Location findLocationByUserId(@Param("id") String userId);

    // method to get Role by User Id
    @Query("SELECT u.role FROM User u WHERE u.id = :id")
    Role findRoleByUserId(@Param("id") String userId);
}
