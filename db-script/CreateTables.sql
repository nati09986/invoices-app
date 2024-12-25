CREATE TABLE IF NOT EXISTS invoices (
    invoice_id VARCHAR(50) PRIMARY KEY,
    invoice_date DATE NOT NULL,
    invoice_due_date DATE NOT NULL,
    invoice_cost NUMERIC(15, 2) NOT NULL,
    invoice_currency CHAR(5) NOT NULL,
    invoice_status VARCHAR(20) NOT NULL,
    supplier_internal_id VARCHAR(50) NOT NULL,
    FOREIGN KEY (supplier_internal_id) REFERENCES Suppliers(supplier_id)
);

CREATE TABLE IF NOT EXISTS Suppliers (
    supplier_id VARCHAR(50) PRIMARY KEY,
    supplier_external_id VARCHAR(50) UNIQUE NOT NULL,
    company_name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    city VARCHAR(100),
    country CHAR(2),
    contact_name VARCHAR(100),
    phone VARCHAR(20),
    email VARCHAR(255),
    bank_code VARCHAR(10),
    bank_branch_code VARCHAR(10),
    bank_account_number VARCHAR(20),
    supplier_status VARCHAR(20),
    stock_value NUMERIC(15, 2)
);
