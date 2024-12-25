import { Supplier } from "./supplier";

export interface Invoice {
    invoiceId: string;
    invoiceDate: string;
    invoiceDueDate: string;
    invoiceCost: number;
    invoiceCurrency: string;
    invoiceStatus: string;
    supplier: Supplier;
}

export type InvoiceData = {
    statusData: { name: string; value: number }[];
    monthlyData: { month: string; total: number }[];
    overdueData: { date: string; overdue: number }[];
    customerData: { customer: string; total: number }[];
};
