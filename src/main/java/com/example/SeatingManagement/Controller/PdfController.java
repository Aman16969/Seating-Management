package com.example.SeatingManagement.Controller;

import com.example.SeatingManagement.Entity.User;
import com.example.SeatingManagement.Repository.UserRepository;
import com.example.SeatingManagement.Services.PdfServices;
import com.itextpdf.text.BaseColor;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/pdf")
@CrossOrigin
public class PdfController {
    @Autowired
    private PdfServices pdfServices;
    @GetMapping("/users")
    public ResponseEntity<byte[]> generateAllUserPdf() throws IOException, DocumentException {
        byte[] pdfBytes=this.pdfServices.generateAllUsersPdf();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "users.pdf");
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

    }
    @GetMapping("/users/admin/location/{id}")
    public ResponseEntity<byte[]> generateAllAdminPdf(@PathVariable Integer id) throws IOException, DocumentException {
        byte[] pdfBytes=this.pdfServices.generateAllAdminPdf(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "admins.pdf");
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
    @GetMapping("/users/user/location/{id}")
    public ResponseEntity<byte[]> generateAllUserPdf(@PathVariable Integer id) throws IOException, DocumentException {
        byte[] pdfBytes=this.pdfServices.generateAllUserPdf(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "ALlusers.pdf");
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
    @GetMapping("/booking/daily/location/{id}")
    public ResponseEntity<byte[]> generateAllBookingDailyPdf(@PathVariable Integer id) throws IOException, DocumentException {
        byte[] pdfBytes=this.pdfServices.generateAllBookingPdfDaily(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "Daily.pdf");
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
    @GetMapping("/booking/weekly/location/{id}")
    public ResponseEntity<byte[]> generateAllBookingWeeklyPdf(@PathVariable Integer id) throws IOException, DocumentException {
        byte[] pdfBytes=this.pdfServices.generateAllBookingPdfWeekly(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "WeeklyBooking.pdf");
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
    @GetMapping("/booking/monthly/location/{id}")
    public ResponseEntity<byte[]> generateAllBookingMonthlyPdf(@PathVariable Integer id) throws IOException, DocumentException {
        byte[] pdfBytes=this.pdfServices.generateAllBookingPdfMonthly(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "MonthlyBooking.pdf");
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
    @GetMapping("/request/location/{id}")
    public ResponseEntity<byte[]> generateAllRequestPdf(@PathVariable Integer id) throws IOException, DocumentException {
        byte[] pdfBytes=this.pdfServices.generateAllRequest(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "Request.pdf");
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
    @GetMapping("/room/location/{id}")
    public ResponseEntity<byte[]> generateAllRoomBookingPdf(@PathVariable Integer id) throws IOException, DocumentException {
        byte[] pdfBytes=this.pdfServices.generateAllBookingRoom(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "RoomBooking.pdf");
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
    @GetMapping("/seat/location/{id}")
    public ResponseEntity<byte[]> generateSeatPdf(@PathVariable Integer id) throws IOException, DocumentException {
        byte[] pdfBytes=this.pdfServices.generateAllSeatPdfName(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "Seat.pdf");
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

}
