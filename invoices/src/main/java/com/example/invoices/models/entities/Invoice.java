package com.example.invoices.models.entities;

import lombok.*;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @Column(nullable = false, unique = true)
    private String invoiceId;

    @Column(nullable = false)
    private LocalDate invoiceDate;

    @Column(nullable = false)
    private LocalDate invoiceDueDate;

    @Column(nullable = false)
    private BigDecimal invoiceCost;

    @Column(nullable = false, length = 3)
    private String invoiceCurrency;

    @Column(nullable = false)
    private String invoiceStatus;

    @ManyToOne
    @JoinColumn(name = "supplier_internal_id", nullable = false)
    private Supplier supplier;
}
