package com.example.invoices.services;

import com.example.invoices.models.Invoice;
import com.example.invoices.repositories.InvoiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    private final Logger logger = LoggerFactory.getLogger(InvoiceService.class);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<Invoice> getFilteredInvoices(String invoiceStatus, String startDate, String endDate, String companyName) {
        logger.error("Started filter Invoices");
        LocalDate start = startDate != null ? LocalDate.parse(startDate, formatter) : null;
        LocalDate end = endDate != null ? LocalDate.parse(endDate, formatter) : null;
        List<Invoice> invoices = invoiceRepository.findInvoicesByFilters(invoiceStatus, start, end, companyName);
        logger.error("Finished filter Invoices");
        return invoices;
    }
}
