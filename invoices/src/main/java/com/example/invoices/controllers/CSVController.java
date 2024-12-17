package com.example.invoices.controllers;

import com.example.invoices.models.entities.Invoice;
import com.example.invoices.models.entities.Supplier;
import com.example.invoices.repositories.InvoiceRepository;
import com.example.invoices.repositories.SupplierRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.math.BigDecimal;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/csv")
public class CSVController {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @PostMapping("/upload")
    public String uploadCSV(@RequestParam("file") MultipartFile file) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            List<String> lines = reader.lines().skip(1).collect(Collectors.toList());

            for (String line : lines) {
                String[] values = line.split(",");
                Supplier supplier = supplierRepository.findBySupplierId(values[6]);
                if (supplier == null) {
                    supplier = new Supplier();
                    supplier.setSupplierId(values[6]);
                    supplier.setExternalId(values[7]);
                    supplier.setCompanyName(values[8]);
                    supplierRepository.save(supplier);
                }

                Invoice invoice = new Invoice();
                invoice.setInvoiceId(values[0]);
                invoice.setInvoiceDate(LocalDate.parse(values[1]));
                invoice.setInvoiceDueDate(LocalDate.parse(values[2]));
                invoice.setInvoiceCost(new BigDecimal(values[3]));
                invoice.setInvoiceCurrency(values[4]);
                invoice.setInvoiceStatus(values[5]);
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
