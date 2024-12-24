package com.example.invoices.repositories;

import com.example.invoices.models.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    @Query("SELECT i FROM invoices i " +
            "WHERE (:invoiceStatus IS NULL OR i.invoiceStatus = :invoiceStatus) " +
            "AND (:startDate IS NULL OR :endDate IS NULL OR i.invoiceDate BETWEEN :startDate AND :endDate) " +
            "AND (:companyName IS NULL OR i.supplier.companyName = :companyName)")
    List<Invoice> findInvoicesByFilters(
            @Param("invoiceStatus") String invoiceStatus,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("companyName") String companyName);

    List<Invoice> findByInvoiceStatus(String invoiceStatus);

    List<Invoice> findByInvoiceDateBetween(LocalDate startDate, LocalDate endDate);

    List<Invoice> findByInvoiceStatusAndInvoiceDateBetween(String invoiceStatus, LocalDate startDate, LocalDate endDate);

    List<Invoice> findBySupplier_CompanyName(String companyName);
}
