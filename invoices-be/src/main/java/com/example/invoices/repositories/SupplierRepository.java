package com.example.invoices.repositories;

import com.example.invoices.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Supplier findBySupplierId(String supplierId);
    Supplier findByCompanyName(String companyName);
}
