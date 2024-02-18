package com.rentu.rentu.models;

import com.rentu.rentu.dao.ReservationRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PdfGenerator {

    @Autowired
    private ReservationRepository reservationRepository;

    public void generatePdf(Long reservationId) {
        try {
            // Get the reservation from the database
            Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
            if (optionalReservation.isPresent()) {
                Reservation reservation = optionalReservation.get();
                Agency agency = reservation.getAgency();
                User user = reservation.getUser();
                Vehicle vehicle = reservation.getVehicle();

                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream("ReservationDetails.pdf"));
                document.open();

                // Agency details
                document.add(new Paragraph("Agency Details:"));
                document.add(new Paragraph("Name: " + agency.getName()));
                document.add(new Paragraph("Email: " + agency.getEmail()));
                document.add(new Paragraph("Location: " + agency.getLocation().getStreet() + ", " + agency.getLocation().getNumber()));
                document.add(new Paragraph(agency.getLocation().getCity() + ", " + agency.getLocation().getCountry()));
                document.add(new Paragraph("\n\n"));

                // User details
                PdfPTable userTable = new PdfPTable(5);
                addTableHeader(userTable, new String[]{"User id", "firstName", "lastName", "birthDay", "email"});
                addTableRow(userTable, new String[]{user.getId().toString(), user.getFirstName(), user.getLastName(), user.getBirthday().toString(), user.getEmail()});
                document.add(userTable);
                document.add(new Paragraph("\n\n"));

                // Vehicle details
                PdfPTable vehicleTable = new PdfPTable(5);
                addTableHeader(vehicleTable, new String[]{"Vehicle id", "manufacturer", "model", "fuelType", "vehicleType"});
                addTableRow(vehicleTable, new String[]{vehicle.getId().toString(), vehicle.getManufacturer(), vehicle.getModel(), vehicle.getFuelType().name(), vehicle.getVehicleType().name()});
                document.add(vehicleTable);
                document.add(new Paragraph("\n\n"));

                // Reservation details
                document.add(new Paragraph("Reservation Details:"));
                document.add(new Paragraph("Start Date: " + reservation.getStartDate().toString()));
                document.add(new Paragraph("End Date: " + reservation.getEndDate().toString()));
                document.add(new Paragraph("Price per Day: " + vehicle.getPricePerDay()));
                document.add(new Paragraph("Total Price: " + reservation.getPrice()));

                document.close();
            } else {
                throw new IllegalArgumentException("No reservation found with the provided ID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addTableHeader(PdfPTable table, String[] headers) {
        for (String header : headers) {
            PdfPCell headerCell = new PdfPCell();
            headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            headerCell.setBorderWidth(2);
            headerCell.setPhrase(new Phrase(header));
            table.addCell(headerCell);
        }
    }

    private void addTableRow(PdfPTable table, String[] row) {
        for (String cell : row) {
            table.addCell(cell);
        }
    }
}