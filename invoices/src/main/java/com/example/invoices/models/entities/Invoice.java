package com.example.invoices.models.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @Column(name = "invoice_id", length = 50, nullable = false)
    private String invoiceId;

    @Column(name = "invoice_date", nullable = false)
    private LocalDateTime invoiceDate;

    @Column(name = "invoice_due_date", nullable = false)
    private LocalDateTime invoiceDueDate;

    @Column(name = "invoice_cost", precision = 15, scale = 2, nullable = false)
    private Long invoiceCost;

    @Column(name = "invoice_currency", length = 5, nullable = false)
    private String invoiceCurrency;

    @Column(name = "invoice_status", length = 50, nullable = false)
    private String invoiceStatus;

    @Column(name = "supplier_internal_id", length = 50, nullable = false)
    private String supplierInternalId;

    @Column(name = "supplier_external_id", length = 50)
    private String supplierExternalId;

    @Column(name = "supplier_company_name", length = 200, nullable = false)
    private String supplierCompanyName;

    @Column(name = "supplier_address", length = 500)
    private String supplierAddress;

    @Column(name = "supplier_city", length = 100)
    private String supplierCity;

    @Column(name = "supplier_country", length = 5)
    private String supplierCountry;

    @Column(name = "supplier_contact_name", length = 150)
    private String supplierContactName;

    @Column(name = "supplier_phone", length = 20)
    private String supplierPhone;

    @Column(name = "supplier_email", length = 150)
    private String supplierEmail;

    @Column(name = "supplier_bank_code", length = 50)
    private String supplierBankCode;

    @Column(name = "supplier_bank_branch_code", length = 50)
    private String supplierBankBranchCode;

    @Column(name = "supplier_bank_account_number", length = 50)
    private String supplierBankAccountNumber;

    @Column(name = "supplier_status", length = 50)
    private String supplierStatus;

    @Column(name = "supplier_stock_value", precision = 20, scale = 2)
    private Long supplierStockValue;

    @Column(name = "supplier_withholding_tax", precision = 10, scale = 2)
    private Long supplierWithholdingTax;
}
