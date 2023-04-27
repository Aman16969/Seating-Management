package com.example.SeatingManagement.Services;

import com.example.SeatingManagement.Entity.SwiftData;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface SwiftService {
    List<SwiftData> getAllUsers();

    SwiftData findDataByEmail(String email);
}
