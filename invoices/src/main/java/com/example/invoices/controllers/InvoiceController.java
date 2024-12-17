package com.example.invoices.controllers;

import com.example.invoices.models.entities.Invoice;
import com.example.invoices.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/summary")
    public Map<String, Long> getSummary() {
        List<Invoice> invoices = invoiceService.getInvoicesByStatus("CONFIRMED");
        return invoices.stream()
                .collect(Collectors.groupingBy(Invoice::getInvoiceStatus, Collectors.counting()));
    }

    @GetMapping("/filter")
    public List<Invoice> filterInvoices(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        if (status != null) {
            return invoiceService.getInvoicesByStatus(status);
        } else if (startDate != null && endDate != null) {
            return invoiceService.getInvoicesByDateRange(
                    LocalDate.parse(startDate), LocalDate.parse(endDate));
        }
        return List.of();
    }
}
