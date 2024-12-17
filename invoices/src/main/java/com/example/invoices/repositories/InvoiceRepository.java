package com.example.invoices.repositories;

import com.example.invoices.models.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByInvoiceStatus(String status);

    List<Invoice> findByInvoiceDateBetween(LocalDate startDate, LocalDate endDate);

    List<Invoice> findBySupplierId(String supplierId);
}
