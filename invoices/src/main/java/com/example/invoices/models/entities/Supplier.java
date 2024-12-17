package com.example.invoices.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String supplierId;

    @Column(nullable = false, unique = true)
    private String externalId;

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