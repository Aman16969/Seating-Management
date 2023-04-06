package com.example.SeatingManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//implements CommandLineRunner
public class SeatingManagementApplication  {

//	@Autowired
//	private PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(SeatingManagementApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println(this.passwordEncoder.encode("aman1"));
//	}
}
