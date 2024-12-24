package com.example.invoices.repositories;

import com.example.invoices.models.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    @Query("SELECT i FROM Invoice i " +
            "WHERE (:invoiceStatus IS NULL OR i.invoiceStatus = :invoiceStatus) " +
            "AND (cast(:startDate as date) is NULL OR i.invoiceDate >= :startDate) " +
            "AND (cast(:endDate as date) is NULL OR i.invoiceDate <= :endDate) " +
            "AND (:contactName IS NULL OR i.supplier.contactName = :contactName)")
    List<Invoice> findInvoicesByFilters(
            @Param("invoiceStatus") String invoiceStatus,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("contactName") String contactName);
}
