package com.example.SeatingManagement.Services;

import com.example.SeatingManagement.Entity.Booking;
import com.example.SeatingManagement.Entity.BookingRoom;
import com.example.SeatingManagement.Entity.Seat;
import com.example.SeatingManagement.Entity.User;
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
    byte[] generateAllBookingPdfMonthly(Integer id);
    byte[] generateAllBookingPdfWeekly(Integer id);
    byte[] generateAllRequest(Integer id);
    byte[] generateAllBookingRoom(Integer id);
    byte[] generateAllSeatPdfName(Integer id);
    byte[] getAllBookingsByDates(LocalDate fromDate, LocalDate toDate);
}
