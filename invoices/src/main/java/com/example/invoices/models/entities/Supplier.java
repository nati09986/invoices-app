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

    private String address;
    private String city;
    private String country;
    private String contactName;
    private String phone;
    private String email;
    private String bankCode;
    private String bankBranchCode;
    private String bankAccountNumber;

    @Column(nullable = false)
    private String status;

    private BigDecimal stockValue;
}
