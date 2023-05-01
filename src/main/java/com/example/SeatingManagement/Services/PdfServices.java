package com.example.SeatingManagement.Services;

import com.example.SeatingManagement.Entity.*;
import com.example.SeatingManagement.EntityRequestBody.*;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface PdfServices {

    byte[] generateAllUsersPdf();
    byte[] generateAllAdminPdf(Integer id);
    byte[] generateAllUserPdf(Integer id);
    byte[] generateAllBookingPdfDaily(Integer id);
    byte [] generateAllBookingPdfDay(LocalDate date,Integer id);
    byte[] generateAllBookingPdfMonthly(Integer id);
    byte[] generateAllBookingPdfWeekly(Integer id);
    byte[] generateAllRequest(Integer id);
    byte[] generateAllBookingRoom(Integer id);
    byte[] generateAllSeatPdfName(Integer id);
    byte[] getAllBookingsByDates(LocalDate fromDate, LocalDate toDate);
    byte[] getAllBookingsBtwDatesAtLocation(LocalDate fromDate, LocalDate toDate, Location location);
    byte[] getRoomBookingByDates(LocalDate fromDate, LocalDate toDate, String type);
    byte[] getRoomBookingByDatesAndLocation(LocalDate fromDate, LocalDate toDate, String type, Location location);
}
