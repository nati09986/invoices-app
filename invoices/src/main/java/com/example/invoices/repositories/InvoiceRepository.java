package com.example.invoices.repositories;

import com.example.invoices.models.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    @Query("SELECT i FROM Invoice i " +
            "WHERE (:invoiceStatus IS NULL OR i.invoiceStatus = :invoiceStatus) " +
            "AND (:startDate IS NULL OR i.invoiceDate >= :startDate) " +
            "AND (:endDate IS NULL OR i.invoiceDate <= :endDate) " +
            "AND (:companyName IS NULL OR i.supplier.companyName = :companyName)")
    List<Invoice> findInvoicesByFilters(
            @Param("invoiceStatus") String invoiceStatus,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("companyName") String companyName);
}
