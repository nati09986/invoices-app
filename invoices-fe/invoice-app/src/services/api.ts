// import axios from 'axios';
// const api = axios.create({
//     baseURL: 'http://localhost:8080/api',
// });
// export default api;

import { FilterCriteria } from "../types/filterTypes";
import { InvoiceData } from "../types/invoiceTypes";

export async function checkCsv() {
    const response = await fetch('http://localhost:8080/api/csv/upload', {
        method: 'POST',
        body: "C:\Users\nati0\Downloads\invoices_sample_file.csv",
    });
    console.log(response)
}

export async function fetchInvoices(filters: FilterCriteria): Promise<InvoiceData[]> {
    const response = await fetch('/api/invoices', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(filters),
    });
    return response.json();
}
// return {
//     statusData: [
//         { status: 'Paid', count: 10 },
//         { status: 'Overdue', count: 5 },
//         { status: 'Pending', count: 15 },
//     ],
//     monthlyData: [
//         { month: 'January', total: 500 },
//         { month: 'February', total: 700 },
//     ],
//     overdueData: [
//         { date: '2024-01-01', count: 2 },
//         { date: '2024-01-02', count: 3 },
//     ],
//     customerData: [
//         { customer: 'Customer A', total: 300 },
//         { customer: 'Customer B', total: 400 },
//     ],
// };