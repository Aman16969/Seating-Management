package com.example.SeatingManagement.ServiceImple;

import com.example.SeatingManagement.Entity.*;
import com.example.SeatingManagement.EntityRequestBody.*;
import com.example.SeatingManagement.ExceptionHandling.ResourceNotFound;
import com.example.SeatingManagement.Repository.*;
import com.example.SeatingManagement.Services.PdfServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;


import java.awt.*;
import java.io.ByteArrayOutputStream;


@Service

public class PdfImple implements PdfServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BookingRoomRepository bookingRoomRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private RequestBookingRepository requestBookingRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public byte[] generateAllUsersPdf() {
        List<User> users=this.userRepository.findAll();
        List<UserDto> userDtos=users.stream().map((e)->this.modelMapper.map(e,UserDto.class)).collect(Collectors.toList());

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();
        Font headingFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, new Color(0, 0, 255));
        Font tableHeaderFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.BLACK);
        Font tableCellFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.DARK_GRAY);
        Paragraph heading = new Paragraph("All User List", headingFont);
        heading.setAlignment(Element.ALIGN_CENTER);
        document.add(heading);
        document.add(Chunk.NEWLINE);
        float[] columnWidths = {2f, 3f, 3f};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        PdfPCell cell1 = new PdfPCell(new Phrase("Name", tableHeaderFont));
        cell1.setBackgroundColor(Color.LIGHT_GRAY);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell1);
        PdfPCell cell2 = new PdfPCell(new Phrase("Email", tableHeaderFont));
        cell2.setBackgroundColor(Color.LIGHT_GRAY);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell2);
        PdfPCell cell3 = new PdfPCell(new Phrase("Location", tableHeaderFont));
        cell3.setBackgroundColor(Color.LIGHT_GRAY);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell3);
        for (UserDto user : userDtos) {
            PdfPCell nameCell = new PdfPCell(new Phrase(user.getFirstName(), tableCellFont));
            nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            nameCell.setBackgroundColor(Color.WHITE);
            table.addCell(nameCell);

            PdfPCell emailCell = new PdfPCell(new Phrase(user.getEmail(), tableCellFont));
            emailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            emailCell.setBackgroundColor(Color.WHITE);
            table.addCell(emailCell);

            PdfPCell locationCell = new PdfPCell(new Phrase(user.getLocation().getName(), tableCellFont));
            locationCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            locationCell.setBackgroundColor(Color.WHITE);
            table.addCell(locationCell);
        }

        document.add(table);

        document.close();
        byte[] pdfBytes = byteArrayOutputStream.toByteArray();

        return pdfBytes;
    }

    @Override
    public byte[] generateAllAdminPdf(Integer id) {
        Location location=this.locationRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Location","Location_Id",""+id));
        List<User> users=this.userRepository.findByLocationAndRole(location,"ADMIN");
        List<UserDto> userDtos=users.stream().map((e)->this.modelMapper.map(e,UserDto.class)).collect(Collectors.toList());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();
        Font headingFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, new Color(0, 0, 255));
        Font tableHeaderFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.BLACK);
        Font tableCellFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.DARK_GRAY);
        Paragraph heading = new Paragraph("All User List", headingFont);
        heading.setAlignment(Element.ALIGN_CENTER);
        document.add(heading);
        document.add(Chunk.NEWLINE);
        float[] columnWidths = {2f, 3f, 3f};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        PdfPCell cell1 = new PdfPCell(new Phrase("Name", tableHeaderFont));
        cell1.setBackgroundColor(Color.LIGHT_GRAY);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell1);
        PdfPCell cell2 = new PdfPCell(new Phrase("Email", tableHeaderFont));
        cell2.setBackgroundColor(Color.LIGHT_GRAY);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell2);
        PdfPCell cell3 = new PdfPCell(new Phrase("Location", tableHeaderFont));
        cell3.setBackgroundColor(Color.LIGHT_GRAY);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell3);
        for (UserDto user : userDtos) {
            PdfPCell nameCell = new PdfPCell(new Phrase(user.getFirstName(), tableCellFont));
            nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            nameCell.setBackgroundColor(Color.WHITE);
            table.addCell(nameCell);

            PdfPCell emailCell = new PdfPCell(new Phrase(user.getEmail(), tableCellFont));
            emailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            emailCell.setBackgroundColor(Color.WHITE);
            table.addCell(emailCell);

            PdfPCell locationCell = new PdfPCell(new Phrase(user.getLocation().getName(), tableCellFont));
            locationCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            locationCell.setBackgroundColor(Color.WHITE);
            table.addCell(locationCell);
        }

        document.add(table);

        document.close();
        byte[] pdfBytes = byteArrayOutputStream.toByteArray();

        return pdfBytes;
    }

    @Override
    public byte[] generateAllUserPdf(Integer id) {
        Location location=this.locationRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Location","Location_Id",""+id));
        List<User> users=this.userRepository.findByLocationAndRole(location,"USER");
        List<UserDto> userDtos=users.stream().map((e)->this.modelMapper.map(e,UserDto.class)).collect(Collectors.toList());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();
        Font headingFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, new Color(0, 0, 255));
        Font tableHeaderFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.BLACK);
        Font tableCellFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.DARK_GRAY);
        Paragraph heading = new Paragraph("All User List", headingFont);
        heading.setAlignment(Element.ALIGN_CENTER);
        document.add(heading);
        document.add(Chunk.NEWLINE);
        float[] columnWidths = {2f, 3f, 3f};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        PdfPCell cell1 = new PdfPCell(new Phrase("Name", tableHeaderFont));
        cell1.setBackgroundColor(Color.LIGHT_GRAY);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell1);
        PdfPCell cell2 = new PdfPCell(new Phrase("Email", tableHeaderFont));
        cell2.setBackgroundColor(Color.LIGHT_GRAY);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell2);
        PdfPCell cell3 = new PdfPCell(new Phrase("Location", tableHeaderFont));
        cell3.setBackgroundColor(Color.LIGHT_GRAY);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell3);
        for (UserDto user : userDtos) {
            PdfPCell nameCell = new PdfPCell(new Phrase(user.getFirstName(), tableCellFont));
            nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            nameCell.setBackgroundColor(Color.WHITE);
            table.addCell(nameCell);

            PdfPCell emailCell = new PdfPCell(new Phrase(user.getEmail(), tableCellFont));
            emailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            emailCell.setBackgroundColor(Color.WHITE);
            table.addCell(emailCell);

            PdfPCell locationCell = new PdfPCell(new Phrase(user.getLocation().getName(), tableCellFont));
            locationCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            locationCell.setBackgroundColor(Color.WHITE);
            table.addCell(locationCell);
        }

        document.add(table);

        document.close();
        byte[] pdfBytes = byteArrayOutputStream.toByteArray();

        return pdfBytes;
    }

    @Override
    public byte[] generateAllBookingPdfDaily(Integer id) {
        LocalDate today = LocalDate.now();
        List<Booking> bookings = this.bookingRepository.findByDate(today);
        List<BookingDto> bookingDtos = bookings.stream().map((e) -> this.modelMapper.map(e, BookingDto.class)).collect(Collectors.toList());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();
        Font headingFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, new Color(0, 0, 255));
        Font tableHeaderFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.BLACK);
        Font tableCellFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.DARK_GRAY);
        Paragraph heading = new Paragraph("All Booking List " + today, headingFont);
        heading.setAlignment(Element.ALIGN_CENTER);
        document.add(heading);
        document.add(Chunk.NEWLINE);
        float[] columnWidths = {3f, 2f, 2f, 2f, 2f, 2f};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        PdfPCell cell1 = new PdfPCell(new Phrase("Email", tableHeaderFont));
        cell1.setBackgroundColor(Color.LIGHT_GRAY);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell1);
        PdfPCell cell2 = new PdfPCell(new Phrase("Seat Name", tableHeaderFont));
        cell2.setBackgroundColor(Color.LIGHT_GRAY);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell2);
        PdfPCell cell3 = new PdfPCell(new Phrase("Location", tableHeaderFont));
        cell3.setBackgroundColor(Color.LIGHT_GRAY);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell3);
        PdfPCell cell4 = new PdfPCell(new Phrase("Date", tableHeaderFont));
        cell3.setBackgroundColor(Color.LIGHT_GRAY);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell4);
        PdfPCell cell5 = new PdfPCell(new Phrase("Start Time", tableHeaderFont));
        cell3.setBackgroundColor(Color.LIGHT_GRAY);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell5);
        PdfPCell cell6 = new PdfPCell(new Phrase("End Time", tableHeaderFont));
        cell3.setBackgroundColor(Color.LIGHT_GRAY);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell6);
        for (BookingDto booking : bookingDtos) {
            PdfPCell emailCell = new PdfPCell(new Phrase(booking.getUser().getEmail(), tableCellFont));
            emailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            emailCell.setBackgroundColor(Color.WHITE);
            table.addCell(emailCell);

            PdfPCell nameCell = new PdfPCell(new Phrase(booking.getSeat().getName(), tableCellFont));
            nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            nameCell.setBackgroundColor(Color.WHITE);
            table.addCell(nameCell);

            PdfPCell locationCell = new PdfPCell(new Phrase(booking.getLocation().getName(), tableCellFont));
            locationCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            locationCell.setBackgroundColor(Color.WHITE);
            table.addCell(locationCell);

            PdfPCell dateCell = new PdfPCell(new Phrase(String.valueOf(booking.getDate()), tableCellFont));
            dateCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            dateCell.setBackgroundColor(Color.WHITE);
            table.addCell(dateCell);

            PdfPCell startTimeCell = new PdfPCell(new Phrase(String.valueOf(booking.getFromTime()), tableCellFont));
            startTimeCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            startTimeCell.setBackgroundColor(Color.WHITE);
            table.addCell(startTimeCell);

            PdfPCell toTimeCell = new PdfPCell(new Phrase(String.valueOf(booking.getToTime()), tableCellFont));
            toTimeCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            toTimeCell.setBackgroundColor(Color.WHITE);
            table.addCell(toTimeCell);
        }

        document.add(table);

        document.close();
        byte[] pdfBytes = byteArrayOutputStream.toByteArray();

        return pdfBytes;



//    @Override
//    public byte[] generateAllBookingRoom(Integer id) {
//        Location location=this.locationRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Location","Location_Id",""+id));
//        List<BookingRoom> bookingRooms=this.bookingRoomRepository.findByLocation(location);
//        List<BookingRoomDto> bookingRoomDtos=bookingRooms.stream().map((e)->this.modelMapper.map(e,BookingRoomDto.class)).collect(Collectors.toList());
//
//            return new byte[0];
//    }
//
//    @Override
//    public byte[] generateAllSeatPdfName(Integer id) {
//        Location location=this.locationRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Location","Location_Id",""+id));
//        List<Seat> seats=this.seatRepository.findSeatsByLocationId(location);
//        List<SeatDto> seatDtos=seats.stream().map((e)->this.modelMapper.map(e,SeatDto.class)).collect(Collectors.toList());
//
//        return new byte[0];
//    }\
    }

    @Override
    public byte[] generateAllBookingPdfDay(LocalDate date, Integer id) {
        Location location=this.locationRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Location","Location_Id",""+id));
        List<Booking> bookings = (List<Booking>) this.bookingRepository.findBookingByDateAndLocation(date,location);
        List<BookingDto> bookingDtos = bookings.stream().map((e) -> this.modelMapper.map(e, BookingDto.class)).collect(Collectors.toList());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();
        Font headingFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, new Color(0, 0, 255));
        Font tableHeaderFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.BLACK);
        Font tableCellFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.DARK_GRAY);
        Paragraph heading = new Paragraph("All Booking List " + date , headingFont);
        heading.setAlignment(Element.ALIGN_CENTER);
        document.add(heading);
        document.add(Chunk.NEWLINE);
        float[] columnWidths = {4f, 2f, 2f, 2f, 2f, 2f,2f};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        PdfPCell cell1 = new PdfPCell(new Phrase("Email", tableHeaderFont));
        cell1.setBackgroundColor(Color.LIGHT_GRAY);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell1);
        PdfPCell cell2 = new PdfPCell(new Phrase("Seat Name", tableHeaderFont));
        cell2.setBackgroundColor(Color.LIGHT_GRAY);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell2);
        PdfPCell cell3 = new PdfPCell(new Phrase("Location", tableHeaderFont));
        cell3.setBackgroundColor(Color.LIGHT_GRAY);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell3);
        PdfPCell cell4 = new PdfPCell(new Phrase("Date", tableHeaderFont));
        cell4.setBackgroundColor(Color.LIGHT_GRAY);
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell4);

        PdfPCell cell5 = new PdfPCell(new Phrase("Start Time", tableHeaderFont));
        cell5.setBackgroundColor(Color.LIGHT_GRAY);
        cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell5);

        PdfPCell cell6 = new PdfPCell(new Phrase("End Time", tableHeaderFont));
        cell6.setBackgroundColor(Color.LIGHT_GRAY);
        cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell6);

        PdfPCell cell7 = new PdfPCell(new Phrase("Present", tableHeaderFont));
        cell7.setBackgroundColor(Color.LIGHT_GRAY);
        cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell7);
        for (BookingDto booking : bookingDtos) {
            PdfPCell emailCell = new PdfPCell(new Phrase(booking.getUser().getEmail(), tableCellFont));
            emailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            emailCell.setBackgroundColor(Color.WHITE);
            table.addCell(emailCell);

            PdfPCell nameCell = new PdfPCell(new Phrase(booking.getSeat().getName(), tableCellFont));
            nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            nameCell.setBackgroundColor(Color.WHITE);
            table.addCell(nameCell);

            PdfPCell locationCell = new PdfPCell(new Phrase(booking.getLocation().getName(), tableCellFont));
            locationCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            locationCell.setBackgroundColor(Color.WHITE);
            table.addCell(locationCell);

            PdfPCell gdate = new PdfPCell(new Phrase(String.valueOf(booking.getDate()), tableCellFont));
            gdate.setHorizontalAlignment(Element.ALIGN_LEFT);
            gdate.setBackgroundColor(Color.WHITE);
            table.addCell(gdate);

            PdfPCell startTimeCell = new PdfPCell(new Phrase(String.valueOf(booking.getFromTime()), tableCellFont));
            startTimeCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            startTimeCell.setBackgroundColor(Color.WHITE);
            table.addCell(startTimeCell);

            PdfPCell toTimeCell = new PdfPCell(new Phrase(String.valueOf(booking.getToTime()), tableCellFont));
            toTimeCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            toTimeCell.setBackgroundColor(Color.WHITE);
            table.addCell(toTimeCell);

            PdfPCell present = new PdfPCell(new Phrase(booking.isPresent() ? "present" : "absent", tableCellFont));
            present.setHorizontalAlignment(Element.ALIGN_LEFT);
            present.setBackgroundColor(Color.WHITE);
            table.addCell(present);
        }

        document.add(table);

        document.close();
        byte[] pdfBytes = byteArrayOutputStream.toByteArray();

        return pdfBytes;
    }

    @Override
    public byte[] generateAllBookingPdfMonthly(Integer id) {
        LocalDate today=LocalDate.now();
        LocalDate lastMonthStart = today.minusMonths(1).withDayOfMonth(1);
        LocalDate lastMonthEnd = today.minusMonths(1).withDayOfMonth(today.minusMonths(1).lengthOfMonth());
        List<Booking> bookings=this.bookingRepository.findAllByBetweenDates(lastMonthStart,lastMonthEnd);
        List<BookingDto> bookingDtos = bookings.stream().map((e) -> this.modelMapper.map(e, BookingDto.class)).collect(Collectors.toList());

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();
        Font headingFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, new Color(0, 0, 255));
        Font tableHeaderFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.BLACK);
        Font tableCellFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.DARK_GRAY);
        Paragraph heading = new Paragraph("All Booking List " + lastMonthStart+ "-"+lastMonthEnd, headingFont);
        heading.setAlignment(Element.ALIGN_CENTER);
        document.add(heading);
        document.add(Chunk.NEWLINE);
        float[] columnWidths = {3f, 2f, 2f, 2f, 2f, 2f};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        PdfPCell cell1 = new PdfPCell(new Phrase("Email", tableHeaderFont));
        cell1.setBackgroundColor(Color.LIGHT_GRAY);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell1);
        PdfPCell cell2 = new PdfPCell(new Phrase("Seat Name", tableHeaderFont));
        cell2.setBackgroundColor(Color.LIGHT_GRAY);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell2);
        PdfPCell cell3 = new PdfPCell(new Phrase("Location", tableHeaderFont));
        cell3.setBackgroundColor(Color.LIGHT_GRAY);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell3);
        PdfPCell cell4 = new PdfPCell(new Phrase("Date", tableHeaderFont));
        cell3.setBackgroundColor(Color.LIGHT_GRAY);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell4);
        PdfPCell cell5 = new PdfPCell(new Phrase("Start Time", tableHeaderFont));
        cell3.setBackgroundColor(Color.LIGHT_GRAY);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell5);
        PdfPCell cell6 = new PdfPCell(new Phrase("End Time", tableHeaderFont));
        cell3.setBackgroundColor(Color.LIGHT_GRAY);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell6);
        for (BookingDto booking : bookingDtos) {
            PdfPCell emailCell = new PdfPCell(new Phrase(booking.getUser().getEmail(), tableCellFont));
            emailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            emailCell.setBackgroundColor(Color.WHITE);
            table.addCell(emailCell);

            PdfPCell nameCell = new PdfPCell(new Phrase(booking.getSeat().getName(), tableCellFont));
            nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            nameCell.setBackgroundColor(Color.WHITE);
            table.addCell(nameCell);

            PdfPCell locationCell = new PdfPCell(new Phrase(booking.getLocation().getName(), tableCellFont));
            locationCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            locationCell.setBackgroundColor(Color.WHITE);
            table.addCell(locationCell);

            PdfPCell dateCell = new PdfPCell(new Phrase(String.valueOf(booking.getDate()), tableCellFont));
            dateCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            dateCell.setBackgroundColor(Color.WHITE);
            table.addCell(dateCell);

            PdfPCell startTimeCell = new PdfPCell(new Phrase(String.valueOf(booking.getFromTime()), tableCellFont));
            startTimeCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            startTimeCell.setBackgroundColor(Color.WHITE);
            table.addCell(startTimeCell);

            PdfPCell toTimeCell = new PdfPCell(new Phrase(String.valueOf(booking.getToTime()), tableCellFont));
            toTimeCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            toTimeCell.setBackgroundColor(Color.WHITE);
            table.addCell(toTimeCell);
        }

        document.add(table);
        document.close();
        byte[] pdfBytes = byteArrayOutputStream.toByteArray();
        return pdfBytes;
    }

    @Override
    public byte[] generateAllBookingPdfWeekly(Integer id) {
        LocalDate today = LocalDate.now();
        LocalDate lastWeekStart = today.minusWeeks(1).with(DayOfWeek.MONDAY);
        LocalDate lastWeekEnd = today.minusWeeks(1).with(DayOfWeek.SUNDAY);
        List<Booking> bookings=this.bookingRepository.findAllByBetweenDates(lastWeekStart,lastWeekEnd);
        List<BookingDto> bookingDtos = bookings.stream().map((e) -> this.modelMapper.map(e, BookingDto.class)).collect(Collectors.toList());

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();
        Font headingFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, new Color(0, 0, 255));
        Font tableHeaderFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.BLACK);
        Font tableCellFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.DARK_GRAY);
        Paragraph heading = new Paragraph("All Booking List " + lastWeekStart+"-"+lastWeekEnd, headingFont);
        heading.setAlignment(Element.ALIGN_CENTER);
        document.add(heading);
        document.add(Chunk.NEWLINE);
        float[] columnWidths = {3f, 2f, 2f, 2f, 2f, 2f};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        PdfPCell cell1 = new PdfPCell(new Phrase("Email", tableHeaderFont));
        cell1.setBackgroundColor(Color.LIGHT_GRAY);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell1);
        PdfPCell cell2 = new PdfPCell(new Phrase("Seat Name", tableHeaderFont));
        cell2.setBackgroundColor(Color.LIGHT_GRAY);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell2);
        PdfPCell cell3 = new PdfPCell(new Phrase("Location", tableHeaderFont));
        cell3.setBackgroundColor(Color.LIGHT_GRAY);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell3);
        PdfPCell cell4 = new PdfPCell(new Phrase("Date", tableHeaderFont));
        cell3.setBackgroundColor(Color.LIGHT_GRAY);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell4);
        PdfPCell cell5 = new PdfPCell(new Phrase("Start Time", tableHeaderFont));
        cell3.setBackgroundColor(Color.LIGHT_GRAY);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell5);
        PdfPCell cell6 = new PdfPCell(new Phrase("End Time", tableHeaderFont));
        cell3.setBackgroundColor(Color.LIGHT_GRAY);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell6);
        for (BookingDto booking : bookingDtos) {
            PdfPCell emailCell = new PdfPCell(new Phrase(booking.getUser().getEmail(), tableCellFont));
            emailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            emailCell.setBackgroundColor(Color.WHITE);
            table.addCell(emailCell);

            PdfPCell nameCell = new PdfPCell(new Phrase(booking.getSeat().getName(), tableCellFont));
            nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            nameCell.setBackgroundColor(Color.WHITE);
            table.addCell(nameCell);

            PdfPCell locationCell = new PdfPCell(new Phrase(booking.getLocation().getName(), tableCellFont));
            locationCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            locationCell.setBackgroundColor(Color.WHITE);
            table.addCell(locationCell);

            PdfPCell dateCell = new PdfPCell(new Phrase(String.valueOf(booking.getDate()), tableCellFont));
            dateCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            dateCell.setBackgroundColor(Color.WHITE);
            table.addCell(dateCell);

            PdfPCell startTimeCell = new PdfPCell(new Phrase(String.valueOf(booking.getFromTime()), tableCellFont));
            startTimeCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            startTimeCell.setBackgroundColor(Color.WHITE);
            table.addCell(startTimeCell);

            PdfPCell toTimeCell = new PdfPCell(new Phrase(String.valueOf(booking.getToTime()), tableCellFont));
            toTimeCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            toTimeCell.setBackgroundColor(Color.WHITE);
            table.addCell(toTimeCell);
        }

        document.add(table);
        document.close();
        byte[] pdfBytes = byteArrayOutputStream.toByteArray();
        return pdfBytes;
    }

    @Override
    public byte[] generateAllRequest(Integer id) {
        Location location=this.locationRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Location","Location_Id",""+id));
        List<RequestBookingRoom> requestBookingRooms=this.requestBookingRepository.findAllByLocation(location);
        List<RequestBookingRoomDto> requestBookingRoomDtos=requestBookingRooms.stream().map((e)->this.modelMapper.map(e,RequestBookingRoomDto.class)).collect(Collectors.toList());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();
        Font headingFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, new Color(0, 0, 255));
        Font tableHeaderFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.BLACK);
        Font tableCellFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.DARK_GRAY);
        Paragraph heading = new Paragraph("All Request", headingFont);
        heading.setAlignment(Element.ALIGN_CENTER);
        document.add(heading);
        document.add(Chunk.NEWLINE);
        float[] columnWidths = {2f, 3f, 2f, 2f};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        PdfPCell cell1 = new PdfPCell(new Phrase("Email", tableHeaderFont));
        cell1.setBackgroundColor(Color.LIGHT_GRAY);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell1);
        PdfPCell cell2 = new PdfPCell(new Phrase("Description", tableHeaderFont));
        cell2.setBackgroundColor(Color.LIGHT_GRAY);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell2);
        PdfPCell cell3 = new PdfPCell(new Phrase("Active", tableHeaderFont));
        cell3.setBackgroundColor(Color.LIGHT_GRAY);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell3);
        PdfPCell cell4 = new PdfPCell(new Phrase("Accepted", tableHeaderFont));
        cell4.setBackgroundColor(Color.LIGHT_GRAY);
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell4);
        for (RequestBookingRoomDto request : requestBookingRoomDtos) {
            PdfPCell emailCell = new PdfPCell(new Phrase(request.getEmail(), tableCellFont));
            emailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            emailCell.setBackgroundColor(Color.WHITE);
            table.addCell(emailCell);
            PdfPCell descCell = new PdfPCell(new Phrase(request.getDescription(), tableCellFont));
            descCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            descCell.setBackgroundColor(Color.WHITE);
            table.addCell(descCell);
            PdfPCell activeCell = new PdfPCell(new Phrase(String.valueOf(request.isActive()), tableCellFont));
            activeCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            activeCell.setBackgroundColor(Color.WHITE);
            table.addCell(activeCell);
            PdfPCell acceptedCell = new PdfPCell(new Phrase(String.valueOf(request.isAccepted()), tableCellFont));
            acceptedCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            acceptedCell.setBackgroundColor(Color.WHITE);
            table.addCell(acceptedCell);
        }
            document.add(table);
            document.close();
            byte[] pdfBytes = byteArrayOutputStream.toByteArray();
            return pdfBytes;
    }

    @Override
    public byte[] generateAllBookingRoom(Integer id) {
        Location location=this.locationRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Location","Location_Id",""+id));
        List<BookingRoom> bookingRooms=this.bookingRoomRepository.findByLocation(location);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();
        Font headingFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, new Color(0, 0, 255));
        Font tableHeaderFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.BLACK);
        Font tableCellFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.DARK_GRAY);
        Paragraph heading = new Paragraph("All Room Booking", headingFont);
        heading.setAlignment(Element.ALIGN_CENTER);
        document.add(heading);
        document.add(Chunk.NEWLINE);
        float[] columnWidths = {2f,2f,2f,2f,2f,2f,2f,2f};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        PdfPCell cell1 = new PdfPCell(new Phrase("Admin", tableHeaderFont));
        cell1.setBackgroundColor(Color.LIGHT_GRAY);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell1);

        PdfPCell cell2 = new PdfPCell(new Phrase("Email", tableHeaderFont));
        cell2.setBackgroundColor(Color.LIGHT_GRAY);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell2);
        PdfPCell cell3 = new PdfPCell(new Phrase("Location", tableHeaderFont));
        cell3.setBackgroundColor(Color.LIGHT_GRAY);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell3);
        PdfPCell cell8 = new PdfPCell(new Phrase("Room Type", tableHeaderFont));
        cell8.setBackgroundColor(Color.LIGHT_GRAY);
        cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell8);

        PdfPCell cell4 = new PdfPCell(new Phrase("Room Number", tableHeaderFont));
        cell4.setBackgroundColor(Color.LIGHT_GRAY);
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell4);
        PdfPCell cell5 = new PdfPCell(new Phrase("Date", tableHeaderFont));
        cell5.setBackgroundColor(Color.LIGHT_GRAY);
        cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell5);
        PdfPCell cell6 = new PdfPCell(new Phrase("From Time", tableHeaderFont));
        cell6.setBackgroundColor(Color.LIGHT_GRAY);
        cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell6);
        PdfPCell cell7 = new PdfPCell(new Phrase("To Time", tableHeaderFont));
        cell7.setBackgroundColor(Color.LIGHT_GRAY);
        cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell7);
        for (BookingRoom room:bookingRooms) {
            PdfPCell emailCell = new PdfPCell(new Phrase(room.getAdmin().getEmail(), tableCellFont));
            emailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            emailCell.setBackgroundColor(Color.WHITE);
            table.addCell(emailCell);
            PdfPCell userCell = new PdfPCell(new Phrase(room.getUser().getEmail(), tableCellFont));
            userCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            userCell.setBackgroundColor(Color.WHITE);
            table.addCell(userCell);
            PdfPCell locationCell = new PdfPCell(new Phrase(room.getLocation().getName(), tableCellFont));
            locationCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            locationCell.setBackgroundColor(Color.WHITE);
            table.addCell(locationCell);
            PdfPCell typeCell = new PdfPCell(new Phrase(room.getRoomType(), tableCellFont));
            typeCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            typeCell.setBackgroundColor(Color.WHITE);
            table.addCell(typeCell);
            PdfPCell nameCell = new PdfPCell(new Phrase(room.getRoom().getName() ,tableCellFont));
            nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            nameCell.setBackgroundColor(Color.WHITE);
            table.addCell(nameCell);



            PdfPCell dateCell = new PdfPCell(new Phrase(String.valueOf(room.getDate()), tableCellFont));
            dateCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            dateCell.setBackgroundColor(Color.WHITE);
            table.addCell(dateCell);

            PdfPCell startTimeCell = new PdfPCell(new Phrase(String.valueOf(room.getFromTime()), tableCellFont));
            startTimeCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            startTimeCell.setBackgroundColor(Color.WHITE);
            table.addCell(startTimeCell);

            PdfPCell toTimeCell = new PdfPCell(new Phrase(String.valueOf(room.getToTime()), tableCellFont));
            toTimeCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            toTimeCell.setBackgroundColor(Color.WHITE);
            table.addCell(toTimeCell);
        }
        document.add(table);
        document.close();
        byte[] pdfBytes = byteArrayOutputStream.toByteArray();
        return pdfBytes;
    }

    @Override
    public byte[] generateAllSeatPdfName(Integer id) {
        Location location=this.locationRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Location","Location_Id",""+id));
        List<Seat> seats=this.seatRepository.findSeatsByLocationId(location);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();
        Font headingFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, new Color(0, 0, 255));
        Font tableHeaderFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.BLACK);
        Font tableCellFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.DARK_GRAY);
        Paragraph heading = new Paragraph("All Seats of "+location.getName(), headingFont);
        heading.setAlignment(Element.ALIGN_CENTER);
        document.add(heading);
        document.add(Chunk.NEWLINE);
        float[] columnWidths = {1f, 2f, 2f, 2f};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        PdfPCell cell1 = new PdfPCell(new Phrase("Seat Id", tableHeaderFont));
        cell1.setBackgroundColor(Color.LIGHT_GRAY);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell1);
        PdfPCell cell2 = new PdfPCell(new Phrase("Seat Name", tableHeaderFont));
        cell2.setBackgroundColor(Color.LIGHT_GRAY);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell2);
        PdfPCell cell3 = new PdfPCell(new Phrase("Location", tableHeaderFont));
        cell3.setBackgroundColor(Color.LIGHT_GRAY);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell3);
        PdfPCell cell4 = new PdfPCell(new Phrase("Capacity", tableHeaderFont));
        cell4.setBackgroundColor(Color.LIGHT_GRAY);
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell4);
        for (Seat seat:seats) {
            PdfPCell emailCell = new PdfPCell(new Phrase(seat.getId(), tableCellFont));
            emailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            emailCell.setBackgroundColor(Color.WHITE);
            table.addCell(emailCell);
            PdfPCell descCell = new PdfPCell(new Phrase(seat.getName(), tableCellFont));
            descCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            descCell.setBackgroundColor(Color.WHITE);
            table.addCell(descCell);
            PdfPCell activeCell = new PdfPCell(new Phrase(seat.getLocation().getName(), tableCellFont));
            activeCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            activeCell.setBackgroundColor(Color.WHITE);
            table.addCell(activeCell);
            PdfPCell acceptedCell = new PdfPCell(new Phrase(String.valueOf(seat.getC()), tableCellFont));
            acceptedCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            acceptedCell.setBackgroundColor(Color.WHITE);
            table.addCell(acceptedCell);
        }
        document.add(table);
        document.close();
        byte[] pdfBytes = byteArrayOutputStream.toByteArray();
        return pdfBytes;
    }

    @Override
    public byte[] getAllBookingsByDates(LocalDate fromDate, LocalDate toDate) {
        List<Booking> bookings = this.bookingRepository.findAllByBetweenDates(fromDate, toDate);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();
        Font headingFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, new Color(0, 0, 255));
        Font tableHeaderFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.BLACK);
        Font tableCellFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.DARK_GRAY);
        Paragraph heading = new Paragraph("Booking of Seats between "+fromDate+" and "+toDate, headingFont);
        heading.setAlignment(Element.ALIGN_CENTER);
        document.add(heading);
        document.add(Chunk.NEWLINE);
        float[] columnWidths = {2f, 2f, 2f, 2f, 2f, 2f};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        PdfPCell cell1 = new PdfPCell(new Phrase("Id", tableHeaderFont));
        cell1.setBackgroundColor(Color.LIGHT_GRAY);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell1);
        PdfPCell cell2 = new PdfPCell(new Phrase("Location", tableHeaderFont));
        cell2.setBackgroundColor(Color.LIGHT_GRAY);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell2);
        PdfPCell cell3 = new PdfPCell(new Phrase("Date", tableHeaderFont));
        cell3.setBackgroundColor(Color.LIGHT_GRAY);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell3);
        PdfPCell cell4 = new PdfPCell(new Phrase("From time", tableHeaderFont));
        cell4.setBackgroundColor(Color.LIGHT_GRAY);
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell4);
        PdfPCell cell5 = new PdfPCell(new Phrase("To time", tableHeaderFont));
        cell5.setBackgroundColor(Color.LIGHT_GRAY);
        cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell5);
        PdfPCell cell6 = new PdfPCell(new Phrase("Status", tableHeaderFont));
        cell6.setBackgroundColor(Color.LIGHT_GRAY);
        cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell6);
        for (Booking booking : bookings) {
            PdfPCell idCell = new PdfPCell(new Phrase(String.valueOf(booking.getId()), tableCellFont));
            idCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            idCell.setBackgroundColor(Color.WHITE);
            table.addCell(idCell);
            PdfPCell locationCell = new PdfPCell(new Phrase(String.valueOf(booking.getLocation().getName()), tableCellFont));
            locationCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            locationCell.setBackgroundColor(Color.WHITE);
            table.addCell(locationCell);
            PdfPCell dateCell = new PdfPCell(new Phrase(String.valueOf(booking.getDate()), tableCellFont));
            dateCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            dateCell.setBackgroundColor(Color.WHITE);
            table.addCell(dateCell);
            PdfPCell fromTimeCell = new PdfPCell(new Phrase(String.valueOf(booking.getFromTime()), tableCellFont));
            fromTimeCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            fromTimeCell.setBackgroundColor(Color.WHITE);
            table.addCell(fromTimeCell);
            PdfPCell toTimeCell = new PdfPCell(new Phrase(String.valueOf(booking.getToTime()), tableCellFont));
            toTimeCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            toTimeCell.setBackgroundColor(Color.WHITE);
            table.addCell(toTimeCell);
            PdfPCell statusCell;
            if(booking.isActive()) {
                statusCell = new PdfPCell(new Phrase("Active", tableCellFont));
            }
            else{
                statusCell = new PdfPCell(new Phrase("Cancelled", tableCellFont));
            }
            statusCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            statusCell.setBackgroundColor(Color.WHITE);
            table.addCell(statusCell);
        }
        document.add(table);
        document.close();
        byte[] pdfBytes = byteArrayOutputStream.toByteArray();
        return pdfBytes;
    }

    @Override
    public byte[] getAllBookingsBtwDatesAtLocation(LocalDate fromDate, LocalDate toDate, Location location) {
        List<Booking> bookings = this.bookingRepository.findAllByBetweenDatesAndLocation(fromDate, toDate, location);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();
        Font headingFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, new Color(0, 0, 255));
        Font tableHeaderFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.BLACK);
        Font tableCellFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.DARK_GRAY);
        Paragraph heading = new Paragraph("Booking of Seats between "+fromDate+" and "+toDate+".", headingFont);
        heading.setAlignment(Element.ALIGN_CENTER);
        document.add(heading);
        document.add(Chunk.NEWLINE);
        float[] columnWidths = {2f, 2f, 2f, 2f, 2f, 2f};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        PdfPCell cell1 = new PdfPCell(new Phrase("Id", tableHeaderFont));
        cell1.setBackgroundColor(Color.LIGHT_GRAY);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell1);
        PdfPCell cell2 = new PdfPCell(new Phrase("Location", tableHeaderFont));
        cell2.setBackgroundColor(Color.LIGHT_GRAY);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell2);
        PdfPCell cell3 = new PdfPCell(new Phrase("Date", tableHeaderFont));
        cell3.setBackgroundColor(Color.LIGHT_GRAY);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell3);
        PdfPCell cell4 = new PdfPCell(new Phrase("From time", tableHeaderFont));
        cell4.setBackgroundColor(Color.LIGHT_GRAY);
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell4);
        PdfPCell cell5 = new PdfPCell(new Phrase("To time", tableHeaderFont));
        cell5.setBackgroundColor(Color.LIGHT_GRAY);
        cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell5);
        PdfPCell cell6 = new PdfPCell(new Phrase("Status", tableHeaderFont));
        cell6.setBackgroundColor(Color.LIGHT_GRAY);
        cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell6);
        for (Booking booking : bookings) {
            PdfPCell idCell = new PdfPCell(new Phrase(String.valueOf(booking.getId()), tableCellFont));
            idCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            idCell.setBackgroundColor(Color.WHITE);
            table.addCell(idCell);
            PdfPCell locationCell = new PdfPCell(new Phrase(String.valueOf(booking.getLocation().getName()), tableCellFont));
            locationCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            locationCell.setBackgroundColor(Color.WHITE);
            table.addCell(locationCell);
            PdfPCell dateCell = new PdfPCell(new Phrase(String.valueOf(booking.getDate()), tableCellFont));
            dateCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            dateCell.setBackgroundColor(Color.WHITE);
            table.addCell(dateCell);
            PdfPCell fromTimeCell = new PdfPCell(new Phrase(String.valueOf(booking.getFromTime()), tableCellFont));
            fromTimeCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            fromTimeCell.setBackgroundColor(Color.WHITE);
            table.addCell(fromTimeCell);
            PdfPCell toTimeCell = new PdfPCell(new Phrase(String.valueOf(booking.getToTime()), tableCellFont));
            toTimeCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            toTimeCell.setBackgroundColor(Color.WHITE);
            table.addCell(toTimeCell);
            PdfPCell statusCell;
            if(booking.isActive()) {
                statusCell = new PdfPCell(new Phrase("Active", tableCellFont));
            }
            else{
                statusCell = new PdfPCell(new Phrase("Cancelled", tableCellFont));
            }
            statusCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            statusCell.setBackgroundColor(Color.WHITE);
            table.addCell(statusCell);
        }
        document.add(table);
        document.close();
        byte[] pdfBytes = byteArrayOutputStream.toByteArray();
        return pdfBytes;
    }

    @Override
    public byte[] getRoomBookingByDates(LocalDate fromDate, LocalDate toDate, String type) {
        String ty = "";
        if(type.equals("BOARD")){
            ty = "Board room";
        }
        else{
            ty = "Conference room";
        }
        List<BookingRoom> bookings = this.bookingRoomRepository.findBookingsBtwDates(fromDate, toDate, type);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();
        Font headingFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, new Color(0, 0, 255));
        Font tableHeaderFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.BLACK);
        Font tableCellFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.DARK_GRAY);
        Paragraph heading = new Paragraph("Booking of "+ty+" between "+fromDate+" and "+toDate, headingFont);
        heading.setAlignment(Element.ALIGN_CENTER);
        document.add(heading);
        document.add(Chunk.NEWLINE);
        float[] columnWidths = {2f, 2f, 2f, 2f, 2f, 2f};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        PdfPCell cell1 = new PdfPCell(new Phrase("Id", tableHeaderFont));
        cell1.setBackgroundColor(Color.LIGHT_GRAY);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell1);
        PdfPCell cell2 = new PdfPCell(new Phrase("Location", tableHeaderFont));
        cell2.setBackgroundColor(Color.LIGHT_GRAY);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell2);
        PdfPCell cell3 = new PdfPCell(new Phrase("Date", tableHeaderFont));
        cell3.setBackgroundColor(Color.LIGHT_GRAY);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell3);
        PdfPCell cell4 = new PdfPCell(new Phrase("From time", tableHeaderFont));
        cell4.setBackgroundColor(Color.LIGHT_GRAY);
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell4);
        PdfPCell cell5 = new PdfPCell(new Phrase("To time", tableHeaderFont));
        cell5.setBackgroundColor(Color.LIGHT_GRAY);
        cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell5);
        PdfPCell cell6 = new PdfPCell(new Phrase("Status", tableHeaderFont));
        cell6.setBackgroundColor(Color.LIGHT_GRAY);
        cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell6);
        for (BookingRoom booking : bookings) {
            PdfPCell idCell = new PdfPCell(new Phrase(String.valueOf(booking.getId()), tableCellFont));
            idCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            idCell.setBackgroundColor(Color.WHITE);
            table.addCell(idCell);
            PdfPCell locationCell = new PdfPCell(new Phrase(String.valueOf(booking.getLocation().getName()), tableCellFont));
            locationCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            locationCell.setBackgroundColor(Color.WHITE);
            table.addCell(locationCell);
            PdfPCell dateCell = new PdfPCell(new Phrase(String.valueOf(booking.getDate()), tableCellFont));
            dateCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            dateCell.setBackgroundColor(Color.WHITE);
            table.addCell(dateCell);
            PdfPCell fromTimeCell = new PdfPCell(new Phrase(String.valueOf(booking.getFromTime()), tableCellFont));
            fromTimeCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            fromTimeCell.setBackgroundColor(Color.WHITE);
            table.addCell(fromTimeCell);
            PdfPCell toTimeCell = new PdfPCell(new Phrase(String.valueOf(booking.getToTime()), tableCellFont));
            toTimeCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            toTimeCell.setBackgroundColor(Color.WHITE);
            table.addCell(toTimeCell);
            PdfPCell statusCell;
            if(booking.isActive()) {
                statusCell = new PdfPCell(new Phrase("Active", tableCellFont));
            }
            else{
                statusCell = new PdfPCell(new Phrase("Cancelled", tableCellFont));
            }
            statusCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            statusCell.setBackgroundColor(Color.WHITE);
            table.addCell(statusCell);
        }
        document.add(table);
        document.close();
        byte[] pdfBytes = byteArrayOutputStream.toByteArray();
        return pdfBytes;
    }

    @Override
    public byte[] getRoomBookingByDatesAndLocation(LocalDate fromDate, LocalDate toDate, String type, Location location) {
        String ty = "";
        if(type.equals("BOARD")){
            ty = "Board room";
        }
        else{
            ty = "Conference room";
        }
        List<BookingRoom> bookings = this.bookingRoomRepository.findBookingsBtwDatesAndLocation(fromDate, toDate, type, location);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();
        Font headingFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, new Color(0, 0, 255));
        Font tableHeaderFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.BLACK);
        Font tableCellFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.DARK_GRAY);
        Paragraph heading = new Paragraph("Booking of "+ty+" between "+fromDate+" and "+toDate, headingFont);
        heading.setAlignment(Element.ALIGN_CENTER);
        document.add(heading);
        document.add(Chunk.NEWLINE);
        float[] columnWidths = {2f, 2f, 2f, 2f, 2f, 2f};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        PdfPCell cell1 = new PdfPCell(new Phrase("Id", tableHeaderFont));
        cell1.setBackgroundColor(Color.LIGHT_GRAY);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell1);
        PdfPCell cell2 = new PdfPCell(new Phrase("Location", tableHeaderFont));
        cell2.setBackgroundColor(Color.LIGHT_GRAY);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell2);
        PdfPCell cell3 = new PdfPCell(new Phrase("Date", tableHeaderFont));
        cell3.setBackgroundColor(Color.LIGHT_GRAY);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell3);
        PdfPCell cell4 = new PdfPCell(new Phrase("From time", tableHeaderFont));
        cell4.setBackgroundColor(Color.LIGHT_GRAY);
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell4);
        PdfPCell cell5 = new PdfPCell(new Phrase("To time", tableHeaderFont));
        cell5.setBackgroundColor(Color.LIGHT_GRAY);
        cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell5);
        PdfPCell cell6 = new PdfPCell(new Phrase("Status", tableHeaderFont));
        cell6.setBackgroundColor(Color.LIGHT_GRAY);
        cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell6);
        for (BookingRoom booking : bookings) {
            PdfPCell idCell = new PdfPCell(new Phrase(String.valueOf(booking.getId()), tableCellFont));
            idCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            idCell.setBackgroundColor(Color.WHITE);
            table.addCell(idCell);
            PdfPCell locationCell = new PdfPCell(new Phrase(String.valueOf(booking.getLocation().getName()), tableCellFont));
            locationCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            locationCell.setBackgroundColor(Color.WHITE);
            table.addCell(locationCell);
            PdfPCell dateCell = new PdfPCell(new Phrase(String.valueOf(booking.getDate()), tableCellFont));
            dateCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            dateCell.setBackgroundColor(Color.WHITE);
            table.addCell(dateCell);
            PdfPCell fromTimeCell = new PdfPCell(new Phrase(String.valueOf(booking.getFromTime()), tableCellFont));
            fromTimeCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            fromTimeCell.setBackgroundColor(Color.WHITE);
            table.addCell(fromTimeCell);
            PdfPCell toTimeCell = new PdfPCell(new Phrase(String.valueOf(booking.getToTime()), tableCellFont));
            toTimeCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            toTimeCell.setBackgroundColor(Color.WHITE);
            table.addCell(toTimeCell);
            PdfPCell statusCell;
            if(booking.isActive()) {
                statusCell = new PdfPCell(new Phrase("Active", tableCellFont));
            }
            else{
                statusCell = new PdfPCell(new Phrase("Cancelled", tableCellFont));
            }
            statusCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            statusCell.setBackgroundColor(Color.WHITE);
            table.addCell(statusCell);
        }
        document.add(table);
        document.close();
        byte[] pdfBytes = byteArrayOutputStream.toByteArray();
        return pdfBytes;
    }
}
