package com.example.invoices.models.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;
}
