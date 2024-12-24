package com.example.invoices.services;

import com.example.invoices.models.entities.Invoice;
import com.example.invoices.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<Invoice> getFilteredInvoices(String invoiceStatus, String startDate, String endDate, String companyName) {
        Timestamp start = startDate != null ? Timestamp.valueOf(LocalDateTime.parse(startDate + "T00:00:00")) : null;
        Timestamp end = endDate != null ? Timestamp.valueOf(LocalDateTime.parse(endDate + "T00:00:00")) : null;

        return invoiceRepository.findInvoicesByFilters(invoiceStatus, start, end, companyName);
    }
//
//    public List<Invoice> getInvoicesByStatus(String status) {
//        return invoiceRepository.findByInvoiceStatus(status);
//    }
//
//    public List<Invoice> getInvoicesByDateRange(LocalDate startDate, LocalDate endDate) {
//        return invoiceRepository.findByInvoiceDateBetween(startDate, endDate);
//    }
//
//    public List<Invoice> getInvoicesByStatusAndDateRange(String status, LocalDate startDate, LocalDate endDate) {
//        return invoiceRepository.findByInvoiceStatusAndInvoiceDateBetween(status, startDate, endDate);
//    }
//
//    public List<Invoice> getInvoicesByCustomer(String companyName) {
//        return invoiceRepository.findBySupplier_CompanyName(companyName);
//    }
}
