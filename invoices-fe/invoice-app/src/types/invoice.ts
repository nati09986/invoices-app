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
