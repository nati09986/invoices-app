package com.example.invoices.models.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long invoiceId;

    @Column(nullable = false)
    private LocalDateTime invoiceDate;

    @Column(nullable = false)
    private LocalDateTime invoiceDueDate;

    @Column(nullable = false)
    private int invoiceCost;

    @Column(nullable = false)
    private String invoiceCurrency;

    @Column(length = 1000)
    private String invoiceStatus;

    @Column(length = 1000)
    private String supplier_internal_id;

    @Column(length = 1000)
    private String supplier_external_id;

    @Column(length = 1000)
    private String bio;

    @Column(length = 1000)
    private String bio;

    @Column(length = 1000)
    private String bio;

    @Column(length = 1000)
    private String bio;

    @Column(length = 1000)
    private String bio;

    @Column(length = 1000)
    private String bio;

    @Column(length = 1000)
    private String bio;

    @Column(length = 1000)
    private String bio;

    @Column
    private String distance;
//    invoice_status VARCHAR(20) NOT NULL,
//    supplier_internal_id VARCHAR(20) NOT NULL,
//    supplier_external_id VARCHAR(20),
//    supplier_company_name VARCHAR(100) NOT NULL,
//    supplier_address VARCHAR(255),
//    supplier_city VARCHAR(100),
//    supplier_country VARCHAR(2),
//    supplier_contact_name VARCHAR(100),
//    supplier_phone VARCHAR(20),
//    supplier_email VARCHAR(100),
//    supplier_bank_code VARCHAR(20),
//    supplier_bank_branch_code VARCHAR(20),
//    supplier_bank_account_number VARCHAR(20),
//    supplier_status VARCHAR(20),
//    supplier_stock_value NUMERIC(10, 2),
//    supplier_withholding_tax NUMERIC(5, 2)
}
