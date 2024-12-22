package com.example.invoices.services;

import com.example.invoices.controllers.CSVController;
import com.example.invoices.models.entities.Invoice;
import com.example.invoices.models.entities.Supplier;
import com.example.invoices.repositories.InvoiceRepository;
import com.example.invoices.repositories.SupplierRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    Logger logger = LoggerFactory.getLogger(CsvService.class);

    public void uploadCSV(MultipartFile file) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim().parse(reader);

            for (CSVRecord record : parser) {
                String invoiceId = record.get("invoice_id");
                LocalDate invoiceDate = LocalDate.parse(record.get("invoice_date"), formatter);
                LocalDate invoiceDueDate = LocalDate.parse(record.get("invoice_due_date"), formatter);
                BigDecimal invoiceCost = new BigDecimal(record.get("invoice_cost"));
                String invoiceCurrency = record.get("invoice_currency");
                String invoiceStatus = record.get("invoice_status");

                Supplier supplier = new Supplier();
                supplier.setSupplierId(record.get("supplier_internal_id"));
                supplier.setSupplierExternalId(record.get("supplier_external_id"));
                supplier.setCompanyName(record.get("supplier_company_name"));
                supplier.setAddress(record.get("supplier_address"));
                supplier.setCity(record.get("supplier_city"));
                supplier.setCountry(record.get("supplier_country"));
                supplier.setContactName(record.get("supplier_contact_name"));
                supplier.setPhone(record.get("supplier_phone"));
                supplier.setEmail(record.get("supplier_email"));
                supplier.setBankCode(record.get("supplier_bank_code"));
                supplier.setBankBranchCode(record.get("supplier_bank_branch_code"));
                supplier.setBankAccountNumber(record.get("supplier_bank_account_number"));
                supplier.setSupplierStatus(record.get("supplier_status"));
                supplier.setStockValue(new BigDecimal(record.get("supplier_stock_value")));
                try {
                    supplierRepository.save(supplier);
                    logger.info("Successfully saved Supplier with ID: {}", supplier.getSupplierId());
                } catch (Exception e) {
                    logger.error("Failed to save Supplier with ID: {}", supplier.getSupplierId(), e);
                }

                Invoice invoice = new Invoice();
                invoice.setInvoiceId(invoiceId);
                invoice.setInvoiceDate(invoiceDate);
                invoice.setInvoiceDueDate(invoiceDueDate);
                invoice.setInvoiceCost(invoiceCost);
                invoice.setInvoiceCurrency(invoiceCurrency);
                invoice.setInvoiceStatus(invoiceStatus);
                invoice.setSupplier(supplier);
                try {
                    invoiceRepository.save(invoice);
                    logger.info("Successfully saved Invoice with ID: {}", invoice.getInvoiceId());
                } catch (Exception e) {
                    logger.error("Failed to save Invoice with ID: {}", invoice.getInvoiceId(), e);
                }
            }
        }
    }
}
