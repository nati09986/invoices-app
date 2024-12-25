import axios from "axios";
import { Invoice } from "../types/invoice";

class ApiService {
    private baseUrl: string;

    constructor(baseUrl: string) {
        this.baseUrl = baseUrl;
    }

    async fetchInvoices(startDate?: string, endDate?: string, customer?: string, status?: string): Promise<Invoice[]> {
        try {
            const response = await axios.get<Invoice[]>(`${this.baseUrl}/invoices/filter`, {
                params: { startDate: startDate, endDate: endDate, customer: customer, status: status },
            });
            return response.data;
        } catch (error) {
            console.error('Failed to fetch invoices:', error);
            throw error;
        }
    }
}

export default ApiService;
