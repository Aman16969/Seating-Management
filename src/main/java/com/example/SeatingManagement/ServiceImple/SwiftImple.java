package com.example.SeatingManagement.ServiceImple;

import com.example.SeatingManagement.Entity.SwiftData;
import com.example.SeatingManagement.Repository.SwiftRepository;
import com.example.SeatingManagement.Services.SwiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SwiftImple implements SwiftService {
    @Autowired
    private SwiftRepository swiftRepository;

    @Override
    public List<SwiftData> getAllUsers() {
        List<SwiftData> swiftData = this.swiftRepository.findAll();
        return swiftData;
    }

    @Override
    public SwiftData findDataByEmail(String email) {
        SwiftData data = this.swiftRepository.findByEmail(email);
        return data;
    }
}
