package com.example.invoices.controllers;

import com.example.invoices.models.Invoice;
import com.example.invoices.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/filter")
    public List<Invoice> filterInvoices(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String customer) {
        return invoiceService.getFilteredInvoices(status, startDate, endDate, customer);
    }
}
