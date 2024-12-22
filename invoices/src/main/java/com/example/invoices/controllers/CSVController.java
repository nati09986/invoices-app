package com.example.invoices.controllers;

import com.example.invoices.models.entities.Invoice;
import com.example.invoices.models.entities.Supplier;
import com.example.invoices.repositories.InvoiceRepository;
import com.example.invoices.repositories.SupplierRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/api/csv")
public class CSVController {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @PostMapping("/upload")
    public String uploadCSV(@RequestParam("file") MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim().parse(reader);

            for (CSVRecord record : parser) {
                String invoiceId = record.get("invoice_id");
                LocalDateTime invoiceDate = LocalDateTime.parse(record.get("invoice_date"));
                LocalDateTime invoiceDueDate = LocalDateTime.parse(record.get("invoice_due_date"));
                BigDecimal invoiceCost = new BigDecimal(record.get("invoice_cost"));
                String invoiceCurrency = record.get("invoice_currency");
                String invoiceStatus = record.get("invoice_status");
                String supplierId = record.get("supplier_internal_id");

                Supplier supplier = supplierRepository.findBySupplierId(supplierId);
                if (supplier == null) {
                    supplier = new Supplier();
                    supplier.setSupplierId(supplierId);
                    supplier.setExternalId(record.get("supplier_external_id"));
                    supplier.setCompanyName(record.get("supplier_company_name"));
                    supplierRepository.save(supplier);
                }

                Invoice invoice = new Invoice();
                invoice.setInvoiceId(invoiceId);
                invoice.setInvoiceDate(invoiceDate);
                invoice.setInvoiceDueDate(invoiceDueDate);
                invoice.setInvoiceCost(invoiceCost);
                invoice.setInvoiceCurrency(invoiceCurrency);
                invoice.setInvoiceStatus(invoiceStatus);
                invoice.setSupplier(supplier);
                invoiceRepository.save(invoice);
            }
            return "File uploaded successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error uploading file: " + e.getMessage();
        }
    }
}
