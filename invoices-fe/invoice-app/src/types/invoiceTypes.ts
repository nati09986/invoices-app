// export interface Invoice {
//     id: string;
//     date: string;
//     dueDate: string;
//     amount: number;
//     status: string;
//     customer: string;
//   }
export type InvoiceData = {
    statusData: { name: string; value: number }[];
    monthlyData: { month: string; total: number }[];
    overdueData: { date: string; overdue: number }[];
    customerData: { customer: string; total: number }[];
};
