package com.example.invoices.controllers;

import com.example.invoices.models.entities.Invoice;
import com.example.invoices.models.entities.Supplier;
import com.example.invoices.repositories.InvoiceRepository;
import com.example.invoices.repositories.SupplierRepository;
import com.example.invoices.services.CsvService;
import com.example.invoices.services.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.math.BigDecimal;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/api/csv")
public class CSVController {
    @Autowired
    private CsvService csvService;

    Logger logger = LoggerFactory.getLogger(CSVController.class);

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCSV(@RequestParam("file") MultipartFile file) {
        try {
            this.csvService.uploadCSV(file);
            return ResponseEntity.ok("File uploaded successfully!");
        } catch (Exception e) {
            logger.error("Error uploading file: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file: " + e.getMessage());
        }
    }
}
