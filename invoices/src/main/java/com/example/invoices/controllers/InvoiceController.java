package com.example.invoices.controllers;

import com.example.invoices.models.entities.Invoice;
import com.example.invoices.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

//    @GetMapping("/summary")
//    public Map<String, Long> getSummary() {
//        return invoiceService.getInvoicesByStatus("CONFIRMED").stream()
//                .collect(Collectors.groupingBy(Invoice::getInvoiceStatus, Collectors.counting()));
//    }

    @GetMapping("/filter")
    public List<Invoice> filterInvoices(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String customer) {
        return invoiceService.getFilteredInvoices(status, startDate, endDate, customer);
    }
}
