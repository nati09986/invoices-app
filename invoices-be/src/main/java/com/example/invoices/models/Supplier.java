package com.example.invoices.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id
    @Column(nullable = false, unique = true)
    private String supplierId;

    @Column(nullable = false)
    private String supplierExternalId;

    @Column(nullable = false)
    private String companyName;

    @Column
    private String address;

    @Column
    private String city;

    @Column
    private String country;

    @Column
    private String contactName;

    @Column
    private String phone;

    @Column
    private String email;

    @Column
    private String bankCode;

    @Column
    private String bankBranchCode;

    @Column
    private String bankAccountNumber;

    @Column
    private String supplierStatus;

    @Column
    private BigDecimal stockValue;
}