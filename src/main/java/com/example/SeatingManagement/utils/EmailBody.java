package com.example.SeatingManagement.utils;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailBody {
    private String toEmail;
    private String subject;
    private String message;
}
