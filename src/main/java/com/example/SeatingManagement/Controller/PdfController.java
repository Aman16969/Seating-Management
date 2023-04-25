package com.example.SeatingManagement.Controller;

import com.example.SeatingManagement.Entity.User;
import com.example.SeatingManagement.Repository.UserRepository;
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
    private UserRepository userRepository;
    @GetMapping("/users")
    public ResponseEntity<byte[]> generatePdf() throws IOException, DocumentException {
        List<User> users = userRepository.findAll();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();

        // Define font styles for the PDF

        Font headingFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, new Color(0, 0, 255));

        Font tableHeaderFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.BLACK);
        Font tableCellFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.DARK_GRAY);

        // Add the heading to the document
        Paragraph heading = new Paragraph("All User List", headingFont);
        heading.setAlignment(Element.ALIGN_CENTER);
        document.add(heading);
        document.add(Chunk.NEWLINE);

        // Define table columns and their widths
        float[] columnWidths = {2f, 3f, 3f};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);

        // Add table headers
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

        // Add table data
        for (User user : users) {
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

        // Add table to the document
        document.add(table);

        document.close();
        byte[] pdfBytes = byteArrayOutputStream.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "users.pdf");
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

    }
}
